import java.io.*;
import org.antlr.runtime.*;
import java.util.*;
  
class Interpreter {
  private Map <String, TableData> res;
  private Map <String, String> myFrom;
  private Map <String, ArrayList<Attribute>> tablesInAtts;
  private long totalTime;
  private long startTime;
  private long endTime;
  
  public void start() {
    try {
      tablesInAtts = new HashMap <String, ArrayList<Attribute>>();
      CatalogReader foo = new CatalogReader ("Catalog.xml");
      res = foo.getCatalog ();
      //System.out.println (foo.printCatalog (res));
      for (String tableName: res.keySet()) {
        fillInTablesInAtts(tableName);
      }
      InputStreamReader converter = new InputStreamReader(System.in);
      BufferedReader in = new BufferedReader(converter);
      
      System.out.format ("\nSQL>");
      String soFar = in.readLine () + "\n";
      
      // loop forever, or until someone asks to quit
      while (true) {
        
        // keep on reading from standard in until we hit a ";"
        while (soFar.indexOf (';') == -1) {
          soFar += (in.readLine () + "\n");
        }
        
        // split the string
        String toParse = soFar.substring (0, soFar.indexOf (';') + 1);
        soFar = soFar.substring (soFar.indexOf (';') + 1, soFar.length ());
        toParse = toParse.toLowerCase ();
        
        // parse it
        ANTLRStringStream parserIn = new ANTLRStringStream (toParse);
        SQLLexer lexer = new SQLLexer (parserIn);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        SQLParser parser = new SQLParser (tokens);
        
        // if we got a quit
        if (parser.parse () == false) {
          break; 
        }
        
        // print the results
        // print the results
        System.out.println ("RESULT OF PARSING");
        System.out.println ("Expressions in SELECT:");
        ArrayList <Expression> mySelect = parser.getSELECT ();
        for (Expression e : mySelect)
          System.out.println ("\t" + e.print ());
        
        System.out.println ("Tables in FROM:");
        myFrom = parser.getFROM ();
        System.out.println ("\t" + myFrom);
        
        System.out.println ("WHERE clause:");
        Expression where = parser.getWHERE ();
        if (where != null)
          System.out.println ("\t" + where.evalPrint ());
        
        System.out.println ("GROUPING atts:");
        for (String att : parser.getGROUPBY ()) {
          System.out.println ("\t" + att);
        }
        
        RelationAlgebra ra = new RelationAlgebra(mySelect, myFrom, parser.getWHERE(), parser.getGROUPBY());
        String inFileName = "";
        for (String s : myFrom.values()) {
          inFileName = s;
        }
        startTime = System.currentTimeMillis();  
        ra.buildAndRun(this, tablesInAtts.get(inFileName));
        endTime = System.currentTimeMillis();
        System.out.println("The run took " + (endTime - startTime) + " milliseconds");
        totalTime += endTime - startTime;
        System.out.println("Total time upto now is " + totalTime + " milliseconds");
        System.out.format ("\nSQL>");
              
      } 
    } catch (Exception e) {
      System.out.println("Error! Exception: " + e); 
    } 
  }
 
  
  public String typeIdentifier(String str) {
    //do the searching for the type
    if (checkIdentifier(str)) {
      String[] strs = str.split("\\.");
      String table = strs[0];
      String attr = strs[1];
      //System.out.println(table + "; " + attr);
      if (res.get(myFrom.get(table)).getAttInfo(attr).getDataType().equals("Str")) {
        return "literal string";
      }
      return res.get(myFrom.get(table)).getAttInfo(attr).getDataType();
    } else {
      System.out.println("attr " + str +" does not exist");
      return "no such attribute";
    }
  }
  
  public boolean checkIdentifier(String str) {
    //System.out.println("check identifier "+str);
    String[] strs = str.split("\\.");
    String table = strs[0];
    String attr = strs[1];
    if (res.get(myFrom.get(table)) != null) {
      if (res.get(myFrom.get(table)).getAttInfo(attr) != null) {
        return true;
      } else {
        System.out.println("no " + attr + " attribute in table " + table);
        return false;
       }
    } else {
      System.out.println("no table " + table); 
      return false;
    }
  }
  
  
  private void fillInTablesInAtts(String tableName) {
    ArrayList<Attribute> inAtts = new ArrayList<Attribute>();
    HashMap<Integer, Entry> attsOrder = new HashMap<Integer, Entry>();
    //Put attributes in right order
    TableData td = res.get(tableName);
    Map <String, AttInfo> tdAtts = td.getAttributes();
    for (String attName: tdAtts.keySet()){
      String attType = tdAtts.get(attName).getDataType();
      Integer seqNum = tdAtts.get(attName).getAttSequenceNumber();
      attsOrder.put(seqNum, new Entry(attType, attName));
    }
    //System.out.println("atts with order : "+attsOrder);
    for (int i = 0; i < attsOrder.size(); i++) {
      inAtts.add(new Attribute(attsOrder.get(i).type,attsOrder.get(i).name));
    }
    //System.out.println("inAtts for table " + tableName + " looks like " + inAtts);
    this.tablesInAtts.put(tableName, inAtts);
  }
  
  public static void main (String [] args) throws Exception {
    (new Interpreter()).start();
  }
}
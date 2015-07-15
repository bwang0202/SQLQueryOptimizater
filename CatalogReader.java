
import java.io.*;
import java.util.*;

// This class is responsible for reading in and constructing the catalog
// data structure.  It does this by reading in and processing an XML file
// that encodes the catalog.

// To use this reader, specify the file to be processed in the constructor.
// Then, a call to getCatalog returns a map from table names (as strings) 
// to TableData objects, which contain all of the catalog info on the table 
//
class CatalogReader {
  
  private BufferedReader myReader;
  private String curLine;
  private int lineNo = 1;
  private Map <String, TableData> returnVal = null;
  private int numTables = 0;
  
  private int findInt (String tagBefore, String tagAfter) {
    
    // find the tag before
    int start, end, returnVal, startLine = lineNo;    
    try {
      while ((start = curLine.indexOf (tagBefore)) == -1) {
        curLine = myReader.readLine ();
        lineNo++;
      }
      
      // find the int
      while ((end = curLine.indexOf (tagAfter)) == -1) {
        curLine += myReader.readLine ();
        lineNo++;
      }
      
      String myInt = curLine.substring (start + tagBefore.length (), end).trim ();
      returnVal = Integer.parseInt (myInt);
      curLine = curLine.substring (end + tagAfter.length ());
      
    } catch (Exception e) {
      e.printStackTrace ();
      throw new RuntimeException ("Died on line " + startLine + " trying to find " +
                                  tagBefore + " " + tagAfter);
    }
    
    return returnVal;
  }
  
  private double findDouble (String tagBefore, String tagAfter) {
    // find the tag before
    int start, end, startLine = lineNo;
    double returnVal;
    try {
      while ((start = curLine.indexOf (tagBefore)) == -1) {
        curLine = myReader.readLine ();
        lineNo++;
      }
      
      // find the double
      while ((end = curLine.indexOf (tagAfter)) == -1) {
        curLine += myReader.readLine ();
        lineNo++;
      }        
      
      String myDouble = curLine.substring (start + tagBefore.length (), end).trim ();
      returnVal = Double.parseDouble (myDouble);
      curLine = curLine.substring (end + tagAfter.length ());
  
    } catch (Exception e) {
      e.printStackTrace ();
      throw new RuntimeException ("Died on line " + startLine + " trying to find " +
                                  tagBefore + " " + tagAfter);
    }
    
    return returnVal;
  }
  
  private String findString (String tagBefore, String tagAfter) {
    
    // find the tag before
    int start, end;
    String returnVal;
    int startLine = lineNo;
    
    try {
      while ((start = curLine.indexOf (tagBefore)) == -1) {
        curLine = myReader.readLine ();
        lineNo++;
      }
      
      while ((end = curLine.indexOf (tagAfter)) == -1) {
        curLine += myReader.readLine ();
        lineNo++;
      }
      
      returnVal = curLine.substring (start + tagBefore.length (), end).trim ();
      curLine = curLine.substring (end + tagAfter.length ());
    } catch (Exception e) {
      e.printStackTrace ();
      throw new RuntimeException ("Died on line " + startLine + " trying to find " +
                                  tagBefore + " " + tagAfter);
    }
    
    return returnVal;
  }
  
  private void suckToTag (String tag) {
    // find the tag before
    int start, startLine = lineNo;
    try {
      while ((start = curLine.indexOf (tag)) == -1) {
        curLine = myReader.readLine ();
        lineNo++;
      }    
      curLine = curLine.substring (start + tag.length ());
      
    } catch (Exception e) {
      e.printStackTrace ();
      throw new RuntimeException ("Died on line " + startLine + " trying to find " + tag);
    }
    
  }
  
  // this takes as input the name of the file where the catalog is stored
  public CatalogReader (String parseMe) {
   
    // Get the object of DataInputStream
    try {
      DataInputStream in = new DataInputStream (new FileInputStream (parseMe));
      myReader = new BufferedReader (new InputStreamReader(in));
    
      // prime the parser
      curLine = myReader.readLine ();
     
    } catch (Exception e) {
      e.printStackTrace ();
      throw new RuntimeException ("Could not open the file!");
    }
    // get the header stuff 
    suckToTag ("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>");
    suckToTag ("<catalog>");
    numTables = findInt ("<num_tables>", "</num_tables>");
  }
  
  static String printCatalog (Map <String, TableData> printMe) {
  
    String retVal = "{\n";
    for (Map.Entry<String,TableData> i : printMe.entrySet ()) {
      retVal += "\n(" + i.getKey () + ",\n{" + i.getValue ().print () + ")";
    }
    retVal += "}";
    return retVal;
  }
  
  // this returns the catalog to the caller
  public Map <String, TableData> getCatalog () {
    if (returnVal == null) {
      returnVal = new HashMap <String, TableData> ();
    }
    for (int i = 0; i < numTables; i++) {
      suckToTag ("<table>");
      String tableName = findString ("<name>", "</name>");
      int tupleCount = findInt ("<tuple_count>", "</tuple_count>");
      int attCount = findInt ("<num_atts>", "</num_atts>");
      Map <String, AttInfo> attributes = new HashMap <String, AttInfo> ();
      for (int j = 0; j < attCount; j++) {
        suckToTag ("<att>");
        String attName = findString ("<name>", "</name>");
        String myType = findString ("<type>", "</type>");
        int numVals = findInt ("<value_count>", "</value_count>");
        suckToTag ("</att>");
        attributes.put (attName, new AttInfo (numVals, myType, j));
      }
      returnVal.put (tableName, new TableData (tupleCount, attributes));
      suckToTag ("</table>");
    }
    suckToTag ("</catalog>");
    return returnVal;
  }
}
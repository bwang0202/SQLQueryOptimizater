import java.io.*;
import java.util.*;

public class RelationAlgebra {
  private ArrayList<Expression> _select;
  private Map<String, String> _from;
  private Expression _where;
  private ArrayList<String> _groupby;

  public RelationAlgebra(ArrayList<Expression> select, Map<String, String> from, Expression where, ArrayList<String> groupby) {
    _select = select;
    _from = from;
    _where = where;
    _groupby = groupby;
  }

  public void buildAndRun(Interpreter i, ArrayList <Attribute> inAtts){
    
    if (this.checkGroupby() == false) { // does not include group by
      // make outAtts and maps
      ArrayList <Attribute> outAtts = new ArrayList <Attribute> ();
      HashMap <String, String> exprs = new HashMap <String, String> ();
      int counter = 1;
      for (Expression e: _select) {
        //System.out.println(e.print());
        String s1 = e.getMyType(i);
        Attribute a = new Attribute(e.replace(s1), "outAtt"+counter);
        outAtts.add(a);
        exprs.put("outAtt"+counter, e.evalPrint());
        counter++;
      }
      // make selection 
      String selection = _where.evalPrint();
      String inFileName = "";
      for (String s : _from.values()) {
        inFileName = s + ".tbl";
      }
      // run the structure like in runner
      try {
//        System.out.println("exprs : " + exprs);
//        System.out.println("selection : " + selection);
//        System.out.println("outAtts : " + outAtts);
//        System.out.println("inFileName : " + inFileName);
        Selection foo = new Selection (inAtts, outAtts, selection, exprs, inFileName, "out.tbl", "g++", "cppDir/"); 
      } catch (Exception e) {
        throw new RuntimeException (e);
      }
      GetKRecords result = new GetKRecords ("out.tbl", 30);
      result.print ();
    } else { // does include group by
      //TODO make inAtts from _from
      String inFileName = "";
      for (String s : _from.values()) {
        inFileName = s + ".tbl";
      }
      ArrayList <Attribute> gpInAtts = new ArrayList <Attribute> ();
      //build selection first so it includes all attributes needed (build a map to map att_i to original Expression)
      ArrayList <Attribute> slOutAtts = new ArrayList <Attribute> ();
      ArrayList <Attribute> gpOutAtts = new ArrayList <Attribute> ();
      HashMap <String, AggFunc> gpAggs = new HashMap <String, AggFunc>  ();
      String slSelection = _where.evalPrint();
      HashMap <String, String> slExprs = new HashMap <String, String> ();
      HashSet<String> visited_ids = new HashSet<String>();
      int counter = 1;
      for (Expression e: _select) {
        // add all attributes gonna be needed in each e to slOutAtts and slExprs, including ones for aggregate functions
        List<Entry> ids = e.allIdentifiers(i);
        //System.out.println("ids : " + ids);
        for (Entry entry: ids) {
          if (visited_ids.contains(entry.name) == false) {
           // System.out.println("newing new Attribute "+e.replace(entry.type)+", "+entry.name);
            slOutAtts.add(new Attribute(e.replace(entry.type), "OUT"+entry.name));
            slExprs.put("OUT"+entry.name, entry.name);
            gpInAtts.add(new Attribute(e.replace(entry.type), entry.name));
            visited_ids.add(entry.name);
          }
        }
        // build gpOut and gpAggs
        if (e.containsAggg()) {
        //  System.out.println("new Attribute " + e.replace(e.getMyType(i))+", gpOut"+counter);
          gpOutAtts.add(new Attribute(e.replace(e.getMyType(i)), "gpOut"+counter));
        //  System.out.println("agggs put" + "gpOut"+counter+", new AggFunc " + e.getAgggType() + ", " + e.getAgggEvalPrint());
          gpAggs.put("gpOut"+counter, new AggFunc(e.getAgggType(), e.getAgggEvalPrint()));
        } else {
       //   System.out.println("new Attribute " + e.replace(e.getMyType(i))+", gpOut"+counter);
          gpOutAtts.add(new Attribute(e.replace(e.getMyType(i)), "gpOut"+counter));
       //   System.out.println("agggs put" + "gpOut"+counter+", new AggFunc " + "none" + ", " + e.evalPrint());
          gpAggs.put("gpOut"+counter, new AggFunc("none", e.evalPrint()));
        }
        counter++;
      }
      ArrayList <String> gpGpAtts = new ArrayList <String> ();
      for (String ii :_groupby) {
      //  System.out.println("adding to grouping atts " + ii.substring(2));
        gpGpAtts.add(ii.substring(2));
      }
      // run the structure like in runner
      try {
        Selection foo = new Selection (inAtts, slOutAtts, slSelection, slExprs, inFileName, "temp.tbl", "g++", "cppDir/"); 
        Grouping goo = new Grouping (gpInAtts, gpOutAtts, gpGpAtts, gpAggs, "temp.tbl", "out.tbl", "g++", "cppDir/"); 
      } catch (Exception e) {
        throw new RuntimeException (e);
      }
      GetKRecords result = new GetKRecords ("out.tbl", 30);
      result.print ();
    }
    
  }


  private boolean checkGroupby() {
    if (_groupby != null && _groupby.size() > 0) return true;
    for (Expression e: _select) {
      if (e.containsAggg()) return true;
    }
    return false;
  }


}
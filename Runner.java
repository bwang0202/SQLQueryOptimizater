
import java.util.*;
import java.io.*;

class Runner {
 
  static public void main (String [] args) {

    long startTime = System.currentTimeMillis();  
    System.out.println ("first running a selection...");
    DoSelection ();
    System.out.println ("now running a join...");
    DoJoin ();
    System.out.println ("now running a group by...");
    DoGroupBy ();
    long endTime = System.currentTimeMillis();
  
    GetKRecords result = new GetKRecords ("out.tbl", 30);
    result.print ();

    System.out.println("The run took " + (endTime - startTime) + " milliseconds");
  }
    
  /*****************************************/
  // This code shows how to run a group by //
  /*****************************************/
  
  private static void DoGroupBy () {
      
    ArrayList <Attribute> inAtts = new ArrayList <Attribute> ();
    inAtts.add (new Attribute ("Int", "o_orderkey"));
    inAtts.add (new Attribute ("Int", "o_custkey"));
    inAtts.add (new Attribute ("Str", "o_orderstatus"));
    inAtts.add (new Attribute ("Float", "o_totalprice"));
    inAtts.add (new Attribute ("Str", "o_orderdate"));
    inAtts.add (new Attribute ("Str", "o_orderpriority"));
    inAtts.add (new Attribute ("Str", "o_clerk"));
    inAtts.add (new Attribute ("Int", "o_shippriority"));
    inAtts.add (new Attribute ("Str", "o_comment"));
    
    ArrayList <Attribute> outAtts = new ArrayList <Attribute> ();
    outAtts.add (new Attribute ("Str", "att1"));
    outAtts.add (new Attribute ("Str", "att2"));
    outAtts.add (new Attribute ("Float", "att3"));
    outAtts.add (new Attribute ("Int", "att4"));
    
    ArrayList <String> groupingAtts = new ArrayList <String> ();
    groupingAtts.add ("o_orderdate");
    groupingAtts.add ("o_orderstatus");
    
    HashMap <String, AggFunc> myAggs = new HashMap <String, AggFunc>  ();
    myAggs.put ("att1", new AggFunc ("none", "Str(\"status: \") + o_orderstatus"));
    myAggs.put ("att2", new AggFunc ("none", "Str(\"date: \") + o_orderdate"));
    myAggs.put ("att3", new AggFunc ("avg", "o_totalprice * Int (100)"));
    myAggs.put ("att4", new AggFunc ("sum", "Int (1)"));
    
    // run the selection operation
    try {
      Grouping foo = new Grouping (inAtts, outAtts, groupingAtts, myAggs, "orders.tbl", "out.tbl", "g++", "cppDir/"); 
    } catch (Exception e) {
      throw new RuntimeException (e);
    }
  }
  
  /******************************************/
  // This code shows how to run a selection //
  /******************************************/
  
  private static void DoSelection () {
      
    ArrayList <Attribute> inAtts = new ArrayList <Attribute> ();
    inAtts.add (new Attribute ("Int", "o_orderkey"));
    inAtts.add (new Attribute ("Int", "o_custkey"));
    inAtts.add (new Attribute ("Str", "o_orderstatus"));
    inAtts.add (new Attribute ("Float", "o_totalprice"));
    inAtts.add (new Attribute ("Str", "o_orderdate"));
    inAtts.add (new Attribute ("Str", "o_orderpriority"));
    inAtts.add (new Attribute ("Str", "o_clerk"));
    inAtts.add (new Attribute ("Int", "o_shippriority"));
    inAtts.add (new Attribute ("Str", "o_comment"));
    
    ArrayList <Attribute> outAtts = new ArrayList <Attribute> ();
    outAtts.add (new Attribute ("Int", "att1"));
    outAtts.add (new Attribute ("Float", "att2"));
    outAtts.add (new Attribute ("Str", "att3"));
    outAtts.add (new Attribute ("Int", "att4"));
    
    String selection = "o_orderdate > Str (\"1996-12-19\") && o_custkey < Int (100)";
    
    HashMap <String, String> exprs = new HashMap <String, String> ();
    exprs.put ("att1", "o_orderkey");
    exprs.put ("att2", "(o_totalprice * Float (1.5)) + Int (1)");
    exprs.put ("att3", "o_orderdate + Str (\" this is my string\")");
    exprs.put ("att4", "o_custkey");
    
    // run the selection operation
    try {
      Selection foo = new Selection (inAtts, outAtts, selection, exprs, "orders.tbl", "out.tbl", "g++", "cppDir/"); 
    } catch (Exception e) {
      throw new RuntimeException (e);
    }
  }
  
  /*************************************/
  // This code shows how to run a join //
  /*************************************/
    
  private static void DoJoin () {
      
    ArrayList <Attribute> inAttsRight = new ArrayList <Attribute> ();
    inAttsRight.add (new Attribute ("Int", "o_orderkey"));
    inAttsRight.add (new Attribute ("Int", "o_custkey"));
    inAttsRight.add (new Attribute ("Str", "o_orderstatus"));
    inAttsRight.add (new Attribute ("Float", "o_totalprice"));
    inAttsRight.add (new Attribute ("Str", "o_orderdate"));
    inAttsRight.add (new Attribute ("Str", "o_orderpriority"));
    inAttsRight.add (new Attribute ("Str", "o_clerk"));
    inAttsRight.add (new Attribute ("Int", "o_shippriority"));
    inAttsRight.add (new Attribute ("Str", "o_comment"));
    
    ArrayList <Attribute> inAttsLeft = new ArrayList <Attribute> ();
    inAttsLeft.add (new Attribute ("Int", "c_custkey"));
    inAttsLeft.add (new Attribute ("Str", "c_name"));
    inAttsLeft.add (new Attribute ("Str", "c_address"));
    inAttsLeft.add (new Attribute ("Int", "c_nationkey"));
    inAttsLeft.add (new Attribute ("Str", "c_phone"));
    inAttsLeft.add (new Attribute ("Float", "c_acctbal"));
    inAttsLeft.add (new Attribute ("Str", "c_mktsegment"));
    inAttsLeft.add (new Attribute ("Str", "c_comment"));
    
    ArrayList <Attribute> outAtts = new ArrayList <Attribute> ();
    outAtts.add (new Attribute ("Str", "att1"));
    outAtts.add (new Attribute ("Int", "att2"));
    outAtts.add (new Attribute ("Int", "att3"));
    outAtts.add (new Attribute ("Str", "att4"));
    outAtts.add (new Attribute ("Int", "att5"));
    
    ArrayList <String> leftHash = new ArrayList <String> ();
    leftHash.add ("c_custkey");

    ArrayList <String> rightHash = new ArrayList <String> ();
    rightHash.add ("o_custkey");
    
    String selection = "right.o_custkey == left.c_custkey && right.o_custkey > Int (1000)";
                    
    HashMap <String, String> exprs = new HashMap <String, String> ();
    exprs.put ("att1", "right.o_comment + Str(\" \") + left.c_comment");
    exprs.put ("att2", "right.o_custkey");
    exprs.put ("att3", "left.c_custkey");
    exprs.put ("att4", "left.c_name");
    exprs.put ("att5", "right.o_orderkey");           
    
    // run the join
    try {
      Join foo = new Join (inAttsLeft, inAttsRight, outAtts, leftHash, rightHash, selection, exprs, 
                                "customer.tbl", "orders.tbl", "out.tbl", "g++", "cppDir/"); 
    } catch (Exception e) {
      throw new RuntimeException (e);
    }
    
  }
  
}

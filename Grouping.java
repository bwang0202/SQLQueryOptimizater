


import java.util.*;
import java.io.*;

/**
 * This class runs a selection/projection over an input text file, and writes the result
 * to an output text file.  It does this by "writing" a C++ program that it compiles and
 * executes to do the selection/projection.
 */
class Grouping {
 
  /**
   * attsInInputFile is the list, in order, of all of the attributes (and their types) that are
   * present in the input file.
   * 
   * attsInOutputFile is the list, in order, of all of the attributes (and types) that are going
   * to be written to the output file.  This includes attributes that are the result of an aggregate
   * compuatation.
   * 
   * groupingAtts is a list of all of the attributes that are used to perform the grouping.
   * 
   * exprsToComputeOutputAtts is a map that lists, for each of the attributes in attsInOutputFile, the
   * expression that will be used to produce that attribute.  For example, an entry in the map might
   * be ["outAtt2", ["sum", "o_totalprice * Float (4.0)"]].  This means that outAtt2 is the
   * result of a "sum" agregate over the expression "o_totalprice * Float (4.0)".  In place of "sum"
   * could be a "none" or an "avg".  In the case of "none", no aggregation is performed.  Note that 
   * in the case of "none", the expression should be a function of only the grouping atts.  In the case
   * of "avg", an average is computed.
   * 
   * Then we have the name of the input file that goes to the grouping, the name of the output
   * file that should result from the grouping, the name of the C++ compiler (this will typically
   * be "g++" on a Linux/Mac system), and finally the path of the directory where all of the C++ files
   * are located, and where the created C++ files are supposed to be written to (this might be 
   * something like "cppFiles/").  Make sure to put a slash on there so that we can just append the
   * file name to the end of the directory (if you are using windows, the slash goes the other way!).
   * 
   */
  public Grouping (ArrayList <Attribute> attsInInputFile,
                    ArrayList <Attribute> attsInOutputFile,
                    ArrayList <String> groupingAtts,
                    Map <String, AggFunc> exprsToComputeOutputAtts,
                    String inFile, String outFile, String compiler, String cppDir) throws IOException {
   
    // We start out by writing the code to declare the atts in the input record
    FileWriter fstream = new FileWriter (cppDir + "Atts.cc");
    BufferedWriter out = new BufferedWriter (fstream);
    for (Attribute i: attsInInputFile) {
      out.write (i.getType () + " " + i.getName () + ";\n");
    }
    out.close ();
    
    // next we write the code to read in the input record
    fstream = new FileWriter(cppDir + "ReadIn.cc");
    out = new BufferedWriter (fstream);
    for (Attribute i: attsInInputFile) {
      out.write ("res = " + i.getName () + ".ReadIn (fromMe);\n");
    }
    out.close ();
    
    // next we write code to perform the hashing
    fstream = new FileWriter(cppDir + "Hashing.cc");
    out = new BufferedWriter (fstream);
    for (String i: groupingAtts) {
      out.write ("hash = hash ^ " + i + ".GetHash ();\n");
    }
    out.write ("\n");
    out.close ();
    
    // next we write code to build the agg record
    fstream = new FileWriter(cppDir + "AggAtts.cc");
    out = new BufferedWriter (fstream);
    
    // this writes out the grouping atts
    for (String i: groupingAtts) {
      
      // find the grouping att so we can get its type
      boolean gotOne = false;
      for (Attribute j : attsInInputFile) {
        if (i.equals (j.getName ())) {
          out.write (j.getType () + " ccccccc_" + j.getName () + ";\n");
          gotOne = true;
          break;
        }
      }
      
      if (gotOne == false) 
        throw new RuntimeException ("error: grouping att was not found in the input");
    }

    // this writes out the output atts
    for (Attribute i: attsInOutputFile) {
      out.write (i.getType () + " " + i.getName () + ";\n");
    }
    out.write ("Int ccccccc_count;\n");
    out.close ();

    // now we write code to check if the two records match
    fstream = new FileWriter(cppDir + "CheckSameGroup.cc");
    out = new BufferedWriter (fstream); 
    out.write ("return true");
    for (String i: groupingAtts) {
      out.write (" && ccccccc_" + i + " == aggMe." + i);
    }
    out.write (";");
    out.close ();    
    
    // and we write the code to write the output aggregate record
    fstream = new FileWriter(cppDir + "WriteOut.cc");
    out = new BufferedWriter (fstream);    
    for (Attribute i: attsInOutputFile) {
      if (exprsToComputeOutputAtts.get (i.getName ()) == null) {
        throw new RuntimeException ("could not find an expression for att " + i.getName ());
      }
      if (exprsToComputeOutputAtts.get (i.getName ()).getFuncToRun ().equals ("none")) {
        out.write (i.getName () + ".WriteOut (toMe);\n");
      } else if (exprsToComputeOutputAtts.get (i.getName ()).getFuncToRun ().equals ("sum")) {
        out.write (i.getName () + ".WriteOut (toMe);\n");
      } else if (exprsToComputeOutputAtts.get (i.getName ()).getFuncToRun ().equals ("avg")) {
        out.write ("(" + i.getName () + " / ccccccc_count).WriteOut (toMe);\n");
      } else {
        throw new RuntimeException ("agg type must be none, sum, or avg");
      }
    }
    out.close ();
    
    // and finally we write code to do the aggregation
    fstream = new FileWriter(cppDir + "DoAgg.cc");
    out = new BufferedWriter (fstream);
    
    // this just copies over the grouping atts
    for (String i: groupingAtts) {  
      out.write ("aggMe.ccccccc_" + i + " = " + i + ";\n");
    }
    
    // and now we do the aggregate atts
    for (Attribute i: attsInOutputFile) {
      
      if (exprsToComputeOutputAtts.get (i.getName ()) == null) 
        throw new RuntimeException ("found an output out without a corresponding expression");
      
      if (exprsToComputeOutputAtts.get (i.getName ()).getFuncToRun ().equals ("none")) {
        out.write ("aggMe." + i.getName () + " = " + exprsToComputeOutputAtts.get (i.getName ()).getExpr () + ";\n");      
      } else {
        out.write ("aggMe." + i.getName () + " = aggMe." + i.getName () + " + " + exprsToComputeOutputAtts.get (i.getName ()).getExpr () + ";\n");
      }
    }
    
    // and finally the count
    out.write ("aggMe.ccccccc_count = aggMe.ccccccc_count + Int (1);\n");
    out.close ();
    
    // now we compile the code
    try {            
      Runtime rt = Runtime.getRuntime();
      String cmdarr[] = {compiler, "-O3", "-o", cppDir + "a.out", "-Wno-write-strings", cppDir + "GroupBy.cc"};
      Process proc = rt.exec (cmdarr);
      int exitVal = proc.waitFor();
      if (exitVal != 0) {
        System.out.println ("When I tried to compile the selection operation using " + compiler + ", I got an error. " + 
                                    " Try running the command:\n\n" + compiler + " -o " + cppDir + 
                                    "a.out -Wno-write-strings " + cppDir + "GroupBy.cc\n\nyourself from the command line" +
                                    " to see what happened.\n");
        throw new RuntimeException ("compilation failed");
      }
    } catch (Throwable t) {
      throw new RuntimeException (t);
    }
    
    // and we run it
    try {            
      Runtime rt = Runtime.getRuntime();
      String cmdarr[] = {cppDir + "a.out", inFile, outFile};
      Process proc = rt.exec (cmdarr);
      int exitVal = proc.waitFor();
      if (exitVal != 0) {
        throw new RuntimeException ("I could not manage to run the compiled program"); 
      }
    } catch (Throwable t) {
      throw new RuntimeException (t);
    } 
  }
                 
}

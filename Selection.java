
import java.util.*;
import java.io.*;

/**
 * This class runs a selection/projection over an input text file, and writes the result
 * to an output text file.  It does this by "writing" a C++ program that it compiles and
 * executes to do the selection/projection.
 */
class Selection {
 
  /**
   * attsInInputFile is the list, in order, of all of the attributes (and their types) that are
   * present in the input file.
   * 
   * attsInOutputFile is the list, in order, of all of the attributes (and types) that are going
   * to be written to the output file.
   * 
   * selectionPredOnInAtts is a string that contains the selection predicate to run on the tuples
   * that are read from the input file.  The string needs to contain a Java-style, boolean expression
   * that can be evaluated over the atts listed in attsInInputFile.  For example, here is a valid 
   * string:  (o_orderdate > Str ("1996-12-19") && o_custkey < Int (100)) || o_totalprice > Float(1.10).  
   * Note that any literal values need to be enclosed in parens and labeled with the type.  If you 
   * want a prdicate that always evalutes to true, just use "true" as your string.
   * 
   * exprsToComputeOutputAtts is a map that lists, for each of the attributes in attsInOutputFile, the
   * expression that will be used to produce that attribute.  For example, an entry in the map might
   * be [outAtt2, (o_totalprice * Float (1.5)) + Int (1)].  Note that the type that results from the
   * expression should match the type of the attribute.
   * 
   * Then we have the name of the input file that goes to the selection, the name of the output
   * file that should result from the expression, the name of the C++ compiler (this will typically
   * be "g++" on a Linux/Mac system), and finally the path of the directory where all of the C++ files
   * are located, and where the created C++ files are supposed to be written to (this might be 
   * something like "cppFiles/").  Make sure to put a slash on there so that we can just append the
   * file name to the end of the directory (if you are using windows, the slash goes the other way!).
   */
  public Selection (ArrayList <Attribute> attsInInputFile,
                    ArrayList <Attribute> attsInOutputFile,
                    String selectionPredOnInAtts,
                    Map <String, String> exprsToComputeOutputAtts,
                    String inFile, String outFile, String compiler, String cppDir) throws IOException {
   
    // We start out by writing the code to declare the atts in the input record
    FileWriter fstream = new FileWriter (cppDir + "Atts.cc");
    BufferedWriter out = new BufferedWriter (fstream);
    for (Attribute i: attsInInputFile) {
      out.write (i.getType () + " " + i.getName () + ";\n");
    }
    out.close ();
    
    // next we write the code with the selection predicate
    fstream = new FileWriter(cppDir + "Predicate.cc");
    out = new BufferedWriter (fstream);
    out.write ("return " + selectionPredOnInAtts + ";\n");
    out.close ();
    
    // next we write the code to read in the input record
    fstream = new FileWriter(cppDir + "ReadIn.cc");
    out = new BufferedWriter (fstream);
    for (Attribute i: attsInInputFile) {
      out.write ("res = " + i.getName () + ".ReadIn (fromMe);\n");
    }
    out.close ();
    
    // next we write the code to write the output records
    fstream = new FileWriter(cppDir + "WriteOut.cc");
    out = new BufferedWriter (fstream);
    
    // begin by writing code to declare the output atts
    for (Attribute i: attsInOutputFile) {
      out.write (i.getType () + " " + i.getName () + ";\n");
    }
    out.write ("\n");
    
    // next we write code to assign to the output atts
    for (Attribute i: attsInOutputFile) {
      if (exprsToComputeOutputAtts.get (i.getName ()) == null) {
        throw new RuntimeException ("could not find an expression for att " + i.getName ());
      }
      out.write (i.getName () + " = " + exprsToComputeOutputAtts.get (i.getName ()) + ";\n");
    }
    out.write ("\n");
    
    // and finally we write code to write out the results
    for (Attribute i: attsInOutputFile) {
      out.write (i.getName () + ".WriteOut (toMe);\n");
    }
    out.close ();
    
    // now we compile the selection code
    try {            
      Runtime rt = Runtime.getRuntime();
      String cmdarr[] = {compiler, "-O3", "-o", cppDir + "a.out", "-Wno-write-strings", cppDir + "Selection.cc"};
      Process proc = rt.exec (cmdarr);
      int exitVal = proc.waitFor();
      if (exitVal != 0) {
        System.out.println ("When I tried to compile the selection operation using " + compiler + ", I got an error. " + 
                                    " Try running the command:\n\n" + compiler + " -o " + cppDir + 
                                    "a.out -Wno-write-strings " + cppDir + "Selection.cc\n\nyourself from the command line" +
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

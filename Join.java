
import java.util.*;
import java.io.*;

/**
 * This class runs a join plus a selection/project over two text input files, and writes the result
 * to an output text file.  It does this by "writing" a C++ program that it compiles and executes 
 * to do the join.    
 */
class Join {
 
  /**
   * attsInLeftInputFile is the list, in order, of all of the attributes (and their types) that are
   * present in the "left" input file.  Since the left one is stored in memory in its entirity,
   * this should be the smaller of the two.
   * 
   * attsInRightInputFile is the list, in order, of all of the attributes (and types) that are present
   * in the right input file.
   * 
   * attsInOutputFile is the list, in order, of all of the attributes (and types) that are to be
   * written to the output file.
   * 
   * attsInLeftToCheckForEquality lists all of the attributes on the left that are involved in equality
   * checks in the join.  attsInRightToCheckForEquality is the corresponding list for the right hand side.
   * These lists are used by the join to hash each record to the correct bucket.  Note that it is assumed
   * that the join can work correctly by hashing left records on everything in attsInLeftToCheckForEquality,
   * hashing right records on everything in attsInRightToCheckForEquality, then (as a filter) checking 
   * all of the matching records to see if they are part of the output.  That is, we assume that if two 
   * records don't have the same hash values, they can't contributed to the output.  Note that everything should 
   * work fine if these two lists are empty; in this case, a nested-loop is run.
   * 
   * selectionPredOnInAtts is a string that contains the selection predicate to run on pairs of tuples
   * before they are output into the output file.  The string needs to contain a Java-style, booean expression
   * that can be evaluated over the atts listed in attsInLeftInputFile and attsInRightInputFile.  Any atts in
   * the left input file should be referred to using "dot" notation and the name "left.  Likewise anything coming
   * from the right input file shoudl be referred to using "dot" notation and the name "right".  For example, 
   * here is a valid string:  left.o_custkey == right.c_custkey && left.o_custkey > Int (1000).  
   * Note that any literal values need to be enclosed in parens and labeled with the type.  If you want a
   * predicate that always evaluates to true, just use "true" as your string.
   * 
   * exprsToComputeOutputAtts is a map that lists, for each of the attributes in attsInOutputFile, the
   * expression that will be used to produce that attribute.  Attributes from the left input record
   * should be prefixed with "left." and those from the right with "right.".  For example, an entry
   * in this map might be: [outAtt1, left.o_comment + Str (" this is a string")].  Note that the type 
   * that results from the expression should match the type of the attribute.
   * 
   * Then we have the name of the left input file the name of the right input file, the name of the output
   * file that should result from the expression, the name of the C++ compiler (this will typically
   * be "g++" on a Linux/Mac system), and finally the path of the directory where all of the C++ files
   * are located, and where the created C++ files are supposed to be written to (this might be 
   * something like "cppFiles/").  Make sure to put a slash on there so that we can just append the
   * file name to the end of the directory (if you are using windows, the slash goes the other way!).
   */
  public Join (ArrayList <Attribute> attsInLeftInputFile,
                    ArrayList <Attribute> attsInRightInputFile,
                    ArrayList <Attribute> attsInOutputFile,
                    ArrayList <String> attsInLeftToCheckForEquality,
                    ArrayList <String> attsInRightToCheckForEquality,
                    String selectionPredOnInAtts,
                    Map <String, String> exprsToComputeOutputAtts,
                    String inFileLeft, String inFileRight, String outFile, 
                    String compiler, String cppDir) throws IOException {
   
    // We start out by writing the code to declare the atts in the left input record
    FileWriter fstream = new FileWriter (cppDir + "AttsLeft.cc");
    BufferedWriter out = new BufferedWriter (fstream);
    for (Attribute i: attsInLeftInputFile) {
      out.write (i.getType () + " " + i.getName () + ";\n");
    }
    out.close ();
    
    // We now write the code to declare the atts in the right input record
    fstream = new FileWriter (cppDir + "AttsRight.cc");
    out = new BufferedWriter (fstream);
    for (Attribute i: attsInRightInputFile) {
      out.write (i.getType () + " " + i.getName () + ";\n");
    }
    out.close ();
    
    // next we write the code with the selection predicate
    fstream = new FileWriter(cppDir + "Predicate.cc");
    out = new BufferedWriter (fstream);
    out.write ("return " + selectionPredOnInAtts + ";\n");
    out.close ();
    
    // next we write the code to read in the left input record
    fstream = new FileWriter(cppDir + "ReadInLeft.cc");
    out = new BufferedWriter (fstream);
    for (Attribute i: attsInLeftInputFile) {
      out.write ("res = " + i.getName () + ".ReadIn (fromMe);\n");
    }
    out.close ();
    
    // and the code to read in the right input record
    fstream = new FileWriter(cppDir + "ReadInRight.cc");
    out = new BufferedWriter (fstream);
    for (Attribute i: attsInRightInputFile) {
      out.write ("res = " + i.getName () + ".ReadIn (fromMe);\n");
    }
    out.close ();
    
    // and we write the code to hash the right record
    fstream = new FileWriter(cppDir + "HashRight.cc");
    out = new BufferedWriter (fstream);
    for (String i: attsInRightToCheckForEquality) {
      out.write ("hash = hash ^ " + i + ".GetHash ();\n");
    }
    out.close ();
    
    // and we write the code to hash the left record
    fstream = new FileWriter(cppDir + "HashLeft.cc");
    out = new BufferedWriter (fstream);
    for (String i: attsInLeftToCheckForEquality) {
      out.write ("hash = hash ^ " + i + ".GetHash ();\n");
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
      String cmdarr[] = {compiler, "-O3", "-o", cppDir + "a.out", "-Wno-write-strings", cppDir + "Join.cc"};
      Process proc = rt.exec (cmdarr);
      int exitVal = proc.waitFor();
      if (exitVal != 0) {
        System.out.println ("When I tried to compile the join operation using " + compiler + ", I got an error. " + 
                                    " Try running the command:\n\n" + compiler + " -o " + cppDir + 
                                    "a.out -Wno-write-strings " + cppDir + "Join.cc\n\nyourself from the command line" +
                                    " to see what happened.\n");
        throw new RuntimeException ("compilation failed");
      }
    } catch (Throwable t) {
      throw new RuntimeException (t);
    }
    
    // and we run it
    try {            
      Runtime rt = Runtime.getRuntime();
      String cmdarr[] = {cppDir + "a.out", inFileLeft, inFileRight, outFile};
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

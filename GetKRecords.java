
import java.io.*;
import java.util.*;

class GetKRecords {

	private class MyComparator implements Comparator <String> {

		public int compare (String one, String two) {
			int first = one.hashCode ();
			int second = two.hashCode ();
			if (first > second)
				return 1;
			else if (first == second)
				return 0;
			else
				return -1;
		}

		public boolean equals (String one, String two) {
			return (one.hashCode () == two.hashCode ());
		}
	}

	private PriorityQueue <String> myQ;

	private int count = 0;

	// create a new objet that holds the top numToKeep records in the file indicated by fName
	public GetKRecords (String fName, int numToKeep) {
		myQ = new PriorityQueue <String> (numToKeep + 1, new MyComparator ());

		String currentLine;
		try {
			BufferedReader br = new BufferedReader (new FileReader (fName));
			while ((currentLine = br.readLine()) != null) {
				count++;
				myQ.add (currentLine);
				if (myQ.size () > numToKeep) {
					myQ.poll ();
				}
			}
			br.close ();
		} catch (Exception e) {
			System.out.println ("Error when trying to process the file for printing.");
		}
	}

	public void print () {
		System.out.println (count + " records found in total.  Printing " + myQ.size () + ".");
		ArrayList <String> myList = new ArrayList <String> ();
		for (String s : myQ) {
			myList.add (s);
		}
		Collections.sort (myList);

		for (String s : myList) {
			String [] res = s.split ("\\|");
			for (String ss : res) {
				System.out.print (ss + "\t");
			}
			System.out.println ();
		}
	}
	

}

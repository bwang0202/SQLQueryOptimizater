
#include <stdlib.h>
#include <strings.h>
#include <ctype.h>
#include <string.h>
#include <stdio.h>
#include "EfficientMap.cc"
#include "TwoWayList.cc"
#include "Types.cc"

class AggRecord;

class InRecord {

private:

	#include "Atts.cc"

	friend class AggRecord;

public:

	InRecord  () {}
	~InRecord  () {}

	bool ReadIn (FILE *fromMe) {
		bool res;
		#include "ReadIn.cc"
		fgetc (fromMe);
		return res;
	}

	long GetHash () {
		long hash = 0;
		#include "Hashing.cc"
		return hash;
	}

	void Aggregate	(AggRecord &aggMe);
};


class AggRecord {

private:

	#include "AggAtts.cc"
	friend class InRecord;
public:


	bool Matches (InRecord &aggMe) {
		#include "CheckSameGroup.cc"
	}

	void Swap (AggRecord &withMe) {
		char temp[sizeof (AggRecord)];
		memmove (temp, this, sizeof (AggRecord));
		memmove (this, &withMe, sizeof (AggRecord));
		memmove (&withMe, temp, sizeof (AggRecord));
	}
	
	void WriteOut (FILE *toMe) {
		#include "WriteOut.cc"
		fprintf (toMe, "\n");
	}

	AggRecord () {};
	~AggRecord () {};
};

void InRecord :: Aggregate (AggRecord &aggMe) {
	#include "DoAgg.cc"
}


class Hash {

private:

	long value;

public:

	Hash (long inVal) {
		value = inVal;
	}

	~Hash () {}
	Hash () {}

	void Swap (Hash &withMe) {
		long temp = value;
		value = withMe.value;
		withMe.value = temp;
	}

	int IsEqual (Hash &withMe) {
		return withMe.value == value;
	}

	int LessThan (Hash &withMe) {
		return withMe.value < value;
	}
};

int main (int numArgs, char **args) {

	// this is the main lookup table for the join
	EfficientMap <Hash, TwoWayList <AggRecord> > lookupTable;

	// first, we read in the small input table
	FILE *inFile = fopen (args[1], "r");

	// now go through the small table and hash everything
	InRecord myRec;
	while (myRec.ReadIn (inFile)) {
		Hash myHash (myRec.GetHash ());

		// if that hash value is already there, then see if there is a match
		bool gotOne = false;
		if (lookupTable.IsThere (myHash)) {
			TwoWayList <AggRecord> &values = lookupTable.Find (myHash);

			// look thru every potential match
			for (values.MoveToStart (); values.RightLength (); values.Advance ()) {
				
				if (values.Current ().Matches (myRec)) {
					myRec.Aggregate (values.Current ());
					gotOne = true;
				}
			}

			// if we got no match, then add to the table
			if (!gotOne) {
				AggRecord temp;
				myRec.Aggregate (temp);
				values.Insert (temp);
				values.MoveToStart ();
			}

			continue;
		}

		// if we got no match, then add to the table
		AggRecord temp;
		myRec.Aggregate (temp);
		TwoWayList <AggRecord> newList;
		newList.Insert (temp);
		lookupTable.Insert (myHash, newList);

	}

	// now, at this point, we've aggregated everything, so we can write it out
	fclose (inFile);
	FILE *outFile = fopen (args[2], "w");
	for (lookupTable.MoveToStart (); !lookupTable.AtEnd (); lookupTable.Advance ()) {
		TwoWayList <AggRecord> &current = lookupTable.CurrentData ();
		for (current.MoveToStart (); current.RightLength (); current.Advance ()) {
			current.Current ().WriteOut (outFile);
		}
	}
	fclose (outFile);
}

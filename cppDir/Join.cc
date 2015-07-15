
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <strings.h>
#include <stdio.h>
#include "EfficientMap.cc"
#include "TwoWayList.cc"
#include "Types.cc"

class LeftRecord;

class RightRecord {

private:

	#include "AttsRight.cc"

	friend bool Matches (LeftRecord &left, RightRecord &right);
	friend void WriteOut (LeftRecord &left, RightRecord &right, FILE *outFile);
public:

	RightRecord  () {}
	~RightRecord  () {}

	void Swap (RightRecord &withMe) {
		char temp[sizeof (RightRecord)];
		memmove (temp, this, sizeof (RightRecord));
		memmove (this, &withMe, sizeof (RightRecord));
		memmove (&withMe, temp, sizeof (RightRecord));
	}

	bool ReadIn (FILE *fromMe) {
		bool res;
		#include "ReadInRight.cc"
		fgetc (fromMe);
		return res;
	}

	long GetHash () {
		long hash = 0;
		#include "HashRight.cc"
		return hash;
	}
	
};

class LeftRecord {

private:

	#include "AttsLeft.cc"

	friend bool Matches (LeftRecord &left, RightRecord &right);
	friend void WriteOut (LeftRecord &left, RightRecord &right, FILE *outFile);
	
public:

	LeftRecord  () {}
	~LeftRecord  () {}

	void Swap (LeftRecord &withMe) {
		char temp[sizeof (LeftRecord)];
		memmove (temp, this, sizeof (LeftRecord));
		memmove (this, &withMe, sizeof (LeftRecord));
		memmove (&withMe, temp, sizeof (LeftRecord));
	}

	bool ReadIn (FILE *fromMe) {
		bool res;
		#include "ReadInLeft.cc"
		fgetc (fromMe);
		return res;
	}

	long GetHash () {
		long hash = 0;
		#include "HashLeft.cc"
		return hash;
	}

};

bool Matches (LeftRecord &left, RightRecord &right) {
	#include "Predicate.cc"
}

void WriteOut (LeftRecord &left, RightRecord &right, FILE *toMe) {
	#include "WriteOut.cc"
	fprintf (toMe, "\n");
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
	EfficientMap <Hash, TwoWayList <LeftRecord> > lookupTable;

	// first, we read in the left input table
	FILE *inFile = fopen (args[1], "r");

	// now go through the left table and hash everything
	LeftRecord myRec;
	while (myRec.ReadIn (inFile)) {
		Hash myHash (myRec.GetHash ());

		// if that hash value is already there, then just add the record in
		if (lookupTable.IsThere (myHash)) {
			TwoWayList <LeftRecord> &values = lookupTable.Find (myHash);
			values.Insert (myRec);

		// otherwise, create a new pair and add it in
		} else {
			TwoWayList <LeftRecord> values;
			values.Insert (myRec);
			lookupTable.Insert (myHash, values);		
		}
	}

	// this is where the output goes
	FILE *outFile = fopen (args[3], "w");

	// and go through the right table and try to find all of the matches
	fclose (inFile);
	inFile = fopen (args[2], "r");
	RightRecord lookupRec;
	while (lookupRec.ReadIn (inFile)) {

		Hash myHash (lookupRec.GetHash ());

		// if that hash value is there
		if (lookupTable.IsThere (myHash)) {
			TwoWayList <LeftRecord> &values = lookupTable.Find (myHash);

			// join with every potential match
			for (values.MoveToStart (); values.RightLength (); values.Advance ()) {
				
				if (Matches (values.Current (), lookupRec)) {
					WriteOut (values.Current (), lookupRec, outFile);
				}
			}
		}
	}
	fclose (inFile);
	fclose (outFile);

	return 0;
}

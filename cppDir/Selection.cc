
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <strings.h>
#include <stdio.h>
#include "Types.cc"

class Record {

private:

	#include "Atts.cc"
	
	/*Int o_orderkey;
	Int o_custkey;
	Int o_orderstatus;
	Float o_totalprice;
	Str o_orderdate;
	Str o_orderpriority;
	Str o_clerk;
	Int o_shippriority;
	Str o_comment;*/

public:

	bool ReadIn (FILE *fromMe) {
		bool res;

		#include "ReadIn.cc"
		
		/*res = o_orderkey.ReadIn (fromMe);
		res = o_custkey.ReadIn (fromMe);
		res = o_orderstatus.ReadIn (fromMe);
		res = o_totalprice.ReadIn (fromMe);
		res = o_orderdate.ReadIn (fromMe);
		res = o_orderpriority.ReadIn (fromMe);
		res = o_clerk.ReadIn (fromMe);
		res = o_shippriority.ReadIn (fromMe);
		res = o_comment.ReadIn (fromMe);*/

		fgetc (fromMe);
		return res;
	}

	void WriteOut (FILE *toMe) {

		#include "WriteOut.cc"

		/*Int att1;
		Float att2;
		Str att3;
		Int att4;

		att1 = o_orderkey;
		att2 = (o_totalprice * Float (1.5)) + Int (1);
		att3 = o_orderdate + Str (" this is my string");
		att4 = o_custkey;

		att1.WriteOut (toMe);
		att2.WriteOut (toMe);
		att3.WriteOut (toMe);
		att4.WriteOut (toMe);*/
	
		fprintf (toMe, "\n");	
	}

	bool RunSelection () {

		#include "Predicate.cc"

		//return o_orderdate > Str ("1996-12-19") && o_custkey < Int (100);
	}
};

int main (int numArgs, char **args) {

	FILE *inFile = fopen (args[1], "r");
	FILE *outFile = fopen (args[2], "w");

	Record myRec;
	int recNum = 0;
	while (myRec.ReadIn (inFile)) {
		if (myRec.RunSelection ()) {
			myRec.WriteOut (outFile);
		}
		recNum++;
	}

	fclose (inFile);
	fclose (outFile);
	
	return 0;
}

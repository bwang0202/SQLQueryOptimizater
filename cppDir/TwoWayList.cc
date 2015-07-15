
#ifndef _TWO_WAY_LIST_C
#define _TWO_WAY_LIST_C

#include "TwoWayList.h"
//#include "EfficientMap.cc"
//#include "InefficientMap.cc"
//#include "DistributedQueue.cc"

#include <stdlib.h>
#include <iostream>

// basic constructor function
template <class Type>
TwoWayList <Type> :: TwoWayList ()
{

	// allocate space for the header
	list = new Header;

	// set up the initial values for an empty list
	list->first = new Node;
	list->last = new Node;
	list->current = list->first;
	list->leftSize = 0;
	list->rightSize = 0;
	list->first->next = list->last;
	list->last->previous = list->first;

}

template <class Type>
void TwoWayList <Type> :: Subtract (TwoWayList &me)
{

	for (MoveToStart (); RightLength (); ) {
		int gotIt = 0;
		for (me.MoveToStart (); me.RightLength (); me.Advance ()) {
			if (Current ().IsEqual (me.Current ())) {
				Type temp;
				Remove (temp);
				gotIt = 1;
				break;
			}
		}
		if (!gotIt)
			Advance ();
	}
}

template <class Type>
void TwoWayList <Type> :: Copy (TwoWayList &me)
{

	TwoWayList <Type> empty;
	empty.Swap (*this);
	for (me.MoveToStart (); me.RightLength (); me.Advance ()) {
		Type temp;
		temp.Copy (me.Current ());
		Insert (temp);
		Advance ();
	}
}

// basic deconstructor function
template <class Type>
TwoWayList <Type> :: ~TwoWayList ()
{

	Clear(); // get rid of the content

	// kill all the nodes
	for (int i = 0; i <= list->leftSize + list->rightSize; i++) {
		list->first = list->first->next;
		delete list->first->previous;
	}
	delete list->first;

	// kill the header
	delete list;

}

// swap operator
template <class Type> void
TwoWayList <Type> :: Swap (TwoWayList & List)
{

	Header *temp = List.list;
	List.list = list;
	list = temp;

}

template <class Type> void
TwoWayList <Type> :: CopyFrom (TwoWayList & other)
{
	TwoWayList empty;
	Swap(empty);
	
	for (other.MoveToStart(); !other.AtEnd(); other.Advance()){
		Type data;
		data.CopyFrom(other.Current());
		Insert(data);
	}

	
}

template <class Type> void
TwoWayList <Type> :: Clear(void){
	MoveToStart();
	while (!AtEnd()){
		Type temp;
		Remove(temp);
	}
}



// make the first node the current node
template <class Type> void
TwoWayList <Type> :: MoveToStart ()
{

	list->current = list->first;
	list->rightSize += list->leftSize;
	list->leftSize = 0;

}

// make the first node the current node
template <class Type> void
TwoWayList <Type> :: MoveToFinish ()
{

	list->current = list->last->previous;
	list->leftSize += list->rightSize;
	list->rightSize = 0;

}

// determine the number of items to the left of the current node
template <class Type> int
TwoWayList <Type> :: LeftLength ()
{
	return (list->leftSize);
}

// determine the number of items to the right of the current node
template <class Type> int
TwoWayList <Type> :: RightLength ()
{
	return (list->rightSize);
}


template <class Type> int
TwoWayList <Type> :: Length ()
{
	return (list->leftSize+list->rightSize);
}

template <class Type> bool
TwoWayList <Type> :: IsEmpty()
{
	return (Length() == 0);
}


template <class Type> bool
TwoWayList <Type> :: AtStart ()
{
	return (list->leftSize == 0);
}


template <class Type> bool
TwoWayList <Type> :: AtEnd ()
{
	return (list->rightSize == 0);
}

// swap the right sides of two lists
template <class Type> void
TwoWayList <Type> :: SwapRights (TwoWayList & List)
{

	// swap out everything after the current nodes
	Node *left_1 = list->current;
	Node *right_1 = list->current->next;
	Node *left_2 = List.list->current;
	Node *right_2 = List.list->current->next;

	left_1->next = right_2;
	right_2->previous = left_1;
	left_2->next = right_1;
	right_1->previous = left_2;

	// set the new endpoints
	Node *temp = list->last;
	list->last = List.list->last;
	List.list->last = temp;

	int tempint = List.list->rightSize;
	List.list->rightSize = list->rightSize;
	list->rightSize = tempint;

}

// swap the leftt sides of the two lists
template <class Type> void
TwoWayList <Type> :: SwapLefts (TwoWayList & List)
{

	// swap out everything after the current nodes
	Node *left_1 = list->current;
	Node *right_1 = list->current->next;
	Node *left_2 = List.list->current;
	Node *right_2 = List.list->current->next;

	left_1->next = right_2;
	right_2->previous = left_1;
	left_2->next = right_1;
	right_1->previous = left_2;

	// set the new frontpoints
	Node *temp = list->first;
	list->first = List.list->first;
	List.list->first = temp;

	// set the new current nodes
	temp = list->current;
	list->current = List.list->current;
	List.list->current = temp;

	int tempint = List.list->leftSize;
	List.list->leftSize = list->leftSize;
	list->leftSize = tempint;
}

// move forwards through the list
template <class Type> void
TwoWayList <Type> :: Advance ()
{

	(list->rightSize)--;
	(list->leftSize)++;
	list->current = list->current->next;

}

// move backwards through the list
template <class Type> void
TwoWayList <Type> :: Retreat ()
{

	(list->rightSize)++;
	(list->leftSize)--;
	list->current = list->current->previous;

}

// insert an item at the current poition
template <class Type> void
TwoWayList <Type> :: Insert (Type & Item)
{

	Node *temp = new Node;
	Node *left = list->current;
	Node *right = list->current->next;

	left->next = temp;
	temp->previous = left;
	temp->next = right;
	right->previous = temp;

	temp->data.Swap (Item);

	list->rightSize += 1;

}

template <class Type> void
TwoWayList <Type> :: Append (Type & Item)
{
	MoveToFinish();
	Insert(Item);
}

template <class Type> Type &
TwoWayList <Type> :: Current ()
{
	return list->current->next->data;
}

// remove an item from the current poition
template <class Type> void
TwoWayList <Type> :: Remove (Type & Item)
{

	Node *temp = list->current->next;
	list->current->next = temp->next;
	temp->next->previous = list->current;

	Item.Swap (temp->data);

	delete temp;

	(list->rightSize)--;
}

#endif

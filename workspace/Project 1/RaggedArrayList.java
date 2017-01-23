import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

/*
 * RaggedArrayList.java
 * Ryan Turner 
 * 
 * Initial starting code by Prof. Boothe Sep 2014
 *
 * The RaggedArrayList is a 2 level data structure that is an array of arrays.
 *  
 * It keeps the items in sorted order according to the comparator.
 * Duplicates are allowed.
 * New items are added after any equivalent items.
 */
public class RaggedArrayList<E> implements Iterable<E> {
	private static final int MINIMUM_SIZE = 4;    // must be even so when split get two equal pieces
	private int size;
	private Object[] l1Array;     // really is an array of L2Array, but compiler won't let me cast to that
	private int l1NumUsed;
	private Comparator<E> comp;

	// create an empty list
	// always have at least 1 second level array even if empty, makes code easier 
	// (DONE)
	RaggedArrayList(Comparator<E> c){
		size = 0;
		l1Array = new Object[MINIMUM_SIZE];                // you can't create an array of a generic type
		l1Array[0] = new L2Array(MINIMUM_SIZE);  // first 2nd level array
		l1NumUsed = 1;
		comp = c;
	}

	// nested class for 2nd level arrays
	// (DONE)
	private class L2Array {
		public E[] items;  
		public int numUsed;

		L2Array(int capacity) {
			items = (E[])new Object[capacity];  // you can't create an array of a generic type
			numUsed = 0;
		}
	}

	//total size (number of entries) in the entire data structure
	// (DONE)
	public int size(){
		return size;
	}

	// null out all references so garbage collector can grab them
	// but keep otherwise empty l1Array and 1st L2Array
	// (DONE)
	public void clear(){
		size = 0;
		Arrays.fill(l1Array, 1, l1Array.length, null);  // clear all but first l2 array
		l1NumUsed = 1;
		L2Array l2Array = (L2Array)l1Array[0];
		Arrays.fill(l2Array.items, 0, l2Array.numUsed, null);  // clear out l2array
		l2Array.numUsed = 0;
	}

	// nested class for a list position
	// used only internally
	// 2 parts: level 1 index and level 2 index
	private class ListLoc {
		public int level1Index;
		public int level2Index;

		ListLoc(int level1Index, int level2Index) {
			this.level1Index = level1Index;
			this.level2Index = level2Index;
		}

		// test if two ListLoc's are to the same location
		public boolean equals(Object otherObj) {
			if (getClass() != otherObj.getClass())  // not needed since trust will be ListLoc
				return false;
			ListLoc other = (ListLoc)otherObj;

			return level1Index == other.level1Index && level2Index == other.level2Index;
		}

		// move ListLoc to next entry
		// when it moves past the very last entry it will be 1 index past the last value in the used level 2 array 
		// used internally to scan through the array for sublist
		// also used to implement the iterator
		public void moveToNext() {
			L2Array cursecondlevel = (L2Array)l1Array[level1Index];
			if(cursecondlevel.numUsed == level2Index + 1){ //Test if the 2nd level array is full
				level1Index++;
				level2Index = 0;
			}
			else{
				level2Index++;
			}
		}
	}

	/**
	 * find 1st matching entry
	 * returns ListLoc of 1st matching item
	 * or of 1st item greater than the item if no match, this could be a used slot at the end of a level 2 array
	 * searches forwards
	 */
	private ListLoc findFront(E item){

		if(size == 0){
			return new ListLoc(0,0);
		}
		
		int x = 0; //current Array 1 index
		int y = 0; //current Array 2 index
		
		while(x <= l1NumUsed){
			L2Array curtl2 = (L2Array)l1Array[x];
			if(comp.compare(item, curtl2.items[0]) < 0){
				x--;
				break;
			}
			x++;
		}
		
		if(x==-1){
			return new ListLoc(0,0);
		}
		
		L2Array l2 = (L2Array) l1Array[x];
		while(l2.items[y] != null && y < l2.numUsed){
			int cmp = comp.compare(item, l2.items[y]);
			if(cmp <=0){
				return new ListLoc(x,y);
			}
			y++;
		}
		return new ListLoc(x,y);
}
// find location after the last matching entry
// or if no match, it finds the index of the next larger item 
// this is the position to add a new entry
// this could be an unused slot at the end of a level 2 array
private ListLoc findEnd(E item){
	// TO DO
	  E itemAtIndex;

	  for (int i = l1Array.length -1; i >= 0; i--) {
	     L2Array nextArray = (L2Array)l1Array[i];

	     if (nextArray == null) {
	        continue;

	     } else {

	        for (int j = nextArray.items.length -1; j >= 0; j--) {
	           itemAtIndex = nextArray.items[j];
	           if (itemAtIndex.equals(item)) {
	              return new ListLoc(i, j+1);
	           }
	        }
	     }
	  }

	  return null;
	//return new ListLoc(0,0);
}

/** 
 * add object after any other matching values
 * findEnd will give the insertion position
 */
boolean add(E item){
	  ListLoc insertLoc = findEnd(item);

	  int index1 = insertLoc.level1Index;
	  int index2 = insertLoc.level2Index;
	  L2Array insertArray = (L2Array)l1Array[index1];
	  
	  
	  if(insertArray.items.length < size){
		  //double the level 2 array
	  }
	  else if(insertArray.items.length >= size){
		  //split the level 2 array
	  }
	  
	  
	  insertArray.items[index2] = item;

	  return true;
}

/**
 * check if list contains a match
 */
boolean contains(E item){
	// TO DO

	return false;
}

/**
 * copy the contents of the RaggedArrayList into the given array
 * @param a - an array of the actual type and of the correct size
 * @return the filled in array
 */
public E[] toArray(E[] a){
	// TO DO
	Itr iter = new Itr();
	E[] temp = Arrays.copyOf(a, a.length);
	int index = 0;
	
	while(iter.hasNext()){
		temp[index] = iter.next();
		index++;
	}
	a = temp;
	
	return a;
}

/**
 * returns a new independent RaggedArrayList 
 * whose elements range from fromElemnt, inclusive, to toElement, exclusive
 * the original list is unaffected
 * @param fromElement
 * @param toElement
 * @return the sublist
 */
public RaggedArrayList<E> subList(E fromElement, E toElement){
	// TO DO

	RaggedArrayList<E> result = new RaggedArrayList<E>(comp);
	return result;
}

/**
 * returns an iterator for this list
 * this method just creates an instance of the inner Itr() class
 * (DONE)   
 */
public Iterator<E> iterator() {
	return new Itr();
}

/**
 * Iterator is just a list loc
 * it starts at (0,0) and finishes with index2 1 past the last item in the last block
 */
private class Itr implements Iterator<E> {
	private ListLoc loc;

	/*
	 * create iterator at start of list
	 * (DONE)
	 */
	Itr(){
		loc = new ListLoc(0, 0);
	}

	/**
	 * check if more items
	 */
	public boolean hasNext() {
		

		return false;
	}

	/**
	 * return item and move to next
	 * throws NoSuchElementException if off end of list
	 */
	public E next() {
		return null;
		
		
	}

	/**
	 * Remove is not implemented. Just use this code.
	 * (DONE)
	 */
	public void remove() {
		throw new UnsupportedOperationException();	
	}

}


/**
 * Main routine for testing the RaggedArrayList by itself.
 * There is a default test case of a-g.
 * You can also specify arguments on the command line that will be
 * processed as a sequence of characters to insert into the list.
 * 
 * DO NOT MODIFY I WILL BE USING THIS FOR MY TESTING
 * @throws FileNotFoundException 
 */
public static void main(String[] args) throws FileNotFoundException { 
	System.out.println("testing routine for RaggedArrayList");
	System.out.println("usage: any command line arguments are added by character to the list");
	System.out.println("       if no arguments, then a default test case is used");

	// setup the input string
	String order = "";
	if (args.length == 0)
		order = "abcdefg";  // default test
	else
		for (int i=0; i < args.length; i++)  // concatenate all args
			order += args[i];

	// insert them character by character into the list
	System.out.println("insertion order: "+order);
	Comparator<String> comp = new StringCmp();
	((CmpCnt)comp).resetCmpCnt();            // reset the counter inside the comparator
	RaggedArrayList<String> ralist = new RaggedArrayList<String>(comp);
	for (int i = 0; i < order.length(); i++){
		String s = order.substring(i, i+1);
		ralist.add(s);
	}
	System.out.println("The number of comparison to build the RaggedArrayList = "+
			((CmpCnt)comp).getCmpCnt());

	System.out.println("TEST: after adds - data structure dump");
	ralist.dump();
	ralist.stats();     

	System.out.println("TEST: contains(\"c\") ->" + ralist.contains("c"));
	System.out.println("TEST: contains(\"7\") ->" + ralist.contains("7"));

	System.out.println("TEST: toArray");
	String[] a = new String[ralist.size()];
	ralist.toArray(a);
	for (int i=0; i<a.length; i++)
		System.out.print("["+a[i]+"]");
	System.out.println();

	System.out.println("TEST: iterator");
	Iterator<String> itr = ralist.iterator();
	while (itr.hasNext())
		System.out.print("["+itr.next()+"]");
	System.out.println();

	System.out.println("TEST: sublist(b,k)");
	RaggedArrayList<String> sublist = ralist.subList("b", "k");
	sublist.dump();	
}

/**
 * string comparator with cmpCnt for testing
 *
 * DO NOT MODIFY I WILL BE USING THIS FOR MY TESTING
 */
public static class StringCmp implements Comparator<String>, CmpCnt {
	int cmpCnt;

	StringCmp(){
		cmpCnt=0;
	}

	public int getCmpCnt() {
		return cmpCnt;
	}
	public void resetCmpCnt() {
		this.cmpCnt = 0;
	}

	public int compare(String s1, String s2) {
		cmpCnt++;
		return s1.compareTo(s2);
	}
}

/**
 * print out an organized display of the list
 * intended for testing purposes on small examples
 * it looks nice for the test case where the objects are characters
 *
 * DO NOT MODIFY I WILL BE USING THIS FOR MY TESTING
 */
public void dump(){
	System.out.println("DUMP: Display of the raggedArrayList");
	for (int i1 = 0; i1 < l1Array.length; i1++) {
		L2Array l2array = (L2Array)l1Array[i1];
		System.out.print("[" + i1 + "] -> ");
		if (l2array == null)
			System.out.println("null");
		else {
			for (int i2 = 0; i2 < l2array.items.length; i2++) {
				E item = l2array.items[i2];
				if (item == null)
					System.out.print("[ ]");
				else
					System.out.print("["+item+"]");
			}
			System.out.println("  ("+l2array.numUsed+" of "+l2array.items.length+") used");
		}
	}
}

/**
 * calculate and display statistics
 * 
 * It use a comparator that implements the given CmpCnt interface.
 * It then runs through the list searching for every item and calculating
 * search statistics.
 * 
 * DO NOT MODIFY I WILL BE USING THIS FOR MY TESTING
 */
public void stats(){
	System.out.println("STATS:");
	System.out.println("list size N = "+ size);

	// level 1 array
	System.out.println("level 1 array " + l1NumUsed + " of " + l1Array.length + " used.");

	// level 2 arrays
	int minL2size = Integer.MAX_VALUE, maxL2size = 0;
	for (int i1 = 0; i1 < l1NumUsed; i1++) {
		L2Array l2Array = (L2Array)l1Array[i1];
		minL2size = Math.min(minL2size, l2Array.numUsed);
		maxL2size = Math.max(maxL2size, l2Array.numUsed);
	}
	System.out.println("level 2 array sizes: min = "+minL2size + " used, avg = " + (double)size/l1NumUsed +
			" used, max = " + maxL2size + " used");

	// search stats, search for every item
	int totalCmps = 0, minCmps = Integer.MAX_VALUE, maxCmps = 0;
	Iterator<E> itr = iterator();
	while (itr.hasNext()) {
		E obj = itr.next();
		((CmpCnt)comp).resetCmpCnt();
		if (!contains(obj))
			System.err.println("Did not expect an unsuccesful search in stats");
		int cnt = ((CmpCnt)comp).getCmpCnt();
		totalCmps += cnt;
		if (cnt > maxCmps)
			maxCmps = cnt;
		if (cnt < minCmps)
			minCmps = cnt;
	}
	System.out.println("Successful search: min cmps = " + minCmps + " avg cmps = " + (double)totalCmps/size +
			" max cmps = " + maxCmps);
}
}
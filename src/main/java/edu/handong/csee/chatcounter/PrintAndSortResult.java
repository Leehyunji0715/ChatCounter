package edu.handong.csee.chatcounter;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class PrintAndSortResult {
	public void printAndSortNameAndNumber(HashMap<String, Message> hash, Message m) {
		//
		
		//
		
		//
		
		//
		
	}

class ValueComparator implements Comparator<String> {

	HashMap<String, Integer> base;

	public ValueComparator(HashMap<String, Integer> base) {
		this.base = base;
	}

	// Note: this comparator imposes orderings that are inconsistent with equals.    
	public int compare(String a, String b) {
		if (base.get(a) >= base.get(b)) { //반대로 하면 오름차순 <=
			return -1;
		} else {
			return 1;
		} // returning 0 would merge keys
	}
}
}

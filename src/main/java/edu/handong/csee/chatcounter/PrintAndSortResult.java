package edu.handong.csee.chatcounter;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class PrintAndSortResult {
	public void printAndSortNameAndNumber(Message messageInfo) {
		ValueComparator bvc =  new ValueComparator(messageInfo.nameToNumber);
		TreeMap<String,Integer> sorted_map = new TreeMap<String,Integer>(bvc);
		sorted_map.putAll(messageInfo.nameToNumber);
		for (Map.Entry<String,Integer> entry : sorted_map.entrySet()) {
			//정렬한 리스트에서 순번을 배열번호로 변경하여 원본 리스트에서 추출
			System.out.println(entry.getKey()+" : "+messageInfo.nameToNumber.get(entry.getKey()));
		}
	}

}// class end

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

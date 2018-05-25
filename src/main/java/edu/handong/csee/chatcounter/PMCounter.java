package edu.handong.csee.chatcounter;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class PMCounter {
	//String date, name, message;
	int count=0;
	
//	public PMCounter(String date, String name, String message) { // 생성자 
//		this.date = date;
//		this.name = name;
//		this.message = message;
//	}
	public PMCounter() { // 생성자 
		
	}
	
	MessageParser mp = new MessageParser(); // line을 parsing 하기 위한 클래스 초기화 
	
//	void countHowManySay(String fileName, Message m) {
//		count = m.nameToNumber.get(name);
//		mp.parseMessage(fileName, m,line);
//		if(mp.ge)
//		// 만약 기존에 중복되게 있으면 그냥 끝냄 
//			
//			
//			
//		count = ~~; 형식으로 저장한다.
//	}
	
	
	
	boolean isSame(String date, String message, Message e) {
		String key = null;
		Set set =e.dateToMessage.keySet();
		Iterator iterator = set.iterator();
		while(iterator.hasNext()){ // 해쉬맵을 통해 키값(데이트) 를 구한다) 
			  key = (String)iterator.next();
			  if(key==date)	break;
			  key = null;
			}
		if(message==e.dateToMessage.get(date)&&date == key) {
			return false;
		}
		else {
			return true;
		}
	}
	
}

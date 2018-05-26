package edu.handong.csee.chatcounter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.text.html.HTMLDocument.Iterator;

public class Controller {
	//static int lineNum=0; //다른 클래스에서 라인 넘을 그냥 바로 인식하도록....
	public static void main(String[] args) {
		String name, date, message,line; // for parsing message class
		ArrayList<String> aFileLines = new ArrayList<String>();
		FileLoader fl = new FileLoader();//file load
		Message messageInfo = new Message();
		MessageParser parser = new MessageParser();
		Map <String, String> DateAndName = new HashMap<String,String>();
		Controller n = new Controller();

		fl.path = "/Users/leehyunji0715/Desktop/ChatCounter/"; // fl 클래스 path에 경로 넣어준다. 실제로는 args[0]넣어줘야할듯 
		
		ArrayList<String> fNames = fl.getFileNames();
		System.out.println(fNames); // 파일 이름을 받아온다. 

		for(int i=0;i<fNames.size();i++) {
			//fl.printFileContents(fNames.get(i),fl.lineNum);//~~~~~~~~~~~~~파일 내용 프린트 하기~~~~~~~~~~~~~~~~
			aFileLines = fl.getFileContents(fNames.get(i));
			//System.out.println(fl.lineNum); --> for checking line Number
			
			for(int j=0;j<fl.lineNum;j++) {
				line = aFileLines.get(j); // 한라인을 불러들인다. 
				//---------------------------------
				parser.parseMessage(fNames.get(i),line);// 각 라인을 파싱한다.	
				
				//System.out.println(parser.getmDate());
				//System.out.println(parser.getmName());
				System.out.println(parser.getmMessage());
				
				if(parser.getmDate()==null && parser.getmName()==null && parser.getmMessage()==null) {
					continue;
				}

				String key;
				Set set = messageInfo.nameToMessage.keySet(); //for get key in messageinfo.!!name!!
				java.util.Iterator iterator = set.iterator();
				while(iterator.hasNext()){// 해시맵 키: 네임 와 벨류: 메세지 비교하기 
					  key = (String)iterator.next();
					  if(parser.getmName()==key && parser.getmMessage()==messageInfo.nameToMessage.get(key)) {
							//만약 중복이면, 그냥 빠져나옴 저장 ㄴㄴ ㅇㅋ 
						  continue; // break문이 아닌 이유 : 다음 해시맵도 검사해야하기 때문이다. 
						}
					  else {// 만약 중복 아니면, 저장하고 빠져나온다. ㅇㅋ 
						  messageInfo.nameToMessage.put(parser.getmName(), parser.getmMessage());
						  messageInfo.dateToMessage.put(parser.getmDate(), parser.getmMessage());
						  int num = messageInfo.nameToNumber.get(parser.getmName())+1;
						  messageInfo.nameToNumber.put(parser.getmName(), num);
					  }
				}		
			}
			fl.lineNum = 0;
		}
		n.printAndSortNameAndNumber(messageInfo);
	}
	
	public void printAndSortNameAndNumber(Message messageInfo) {
		ValueComparator bvc =  new ValueComparator(messageInfo.nameToNumber);
		TreeMap<String,Integer> sorted_map = new TreeMap<String,Integer>(bvc);
		sorted_map.putAll(messageInfo.nameToNumber);
		 for (Map.Entry<String,Integer> entry : sorted_map.entrySet()) {
	            //정렬한 리스트에서 순번을 배열번호로 변경하여 원본 리스트에서 추출
	            System.out.println(entry.getKey()+" : "+messageInfo.nameToNumber.get(entry.getKey()));
	        }
//		java.util.Iterator it = sortByValue(messageInfo.nameToNumber).iterator();
//		while(it.hasNext()) {
//            String temp = (String) it.next();
//            System.out.println(temp + " = " + messageInfo.nameToNumber.get(temp));
//            System.out.println(1);
//        }
	}
	
	
	
	
	
	
	
//	public static List sortByValue(final Map map) {
//
//        List<String> list = new ArrayList();
//        list.addAll(map.keySet());
//        Collections.sort(list,new Comparator() {
//        	public int compare(Object o1,Object o2) {
//                Object v1 = map.get(o1);
//                Object v2 = map.get(o2);
//                return ((Comparable) v2).compareTo(v1);
//            }
//        });
//        Collections.reverse(list); // 주석시 오름차순
//        return list;
//    }

}// class end

class ValueComparator implements Comparator<String> {
	 
    Map<String, Integer> base;
     
    public ValueComparator(Map<String, Integer> base) {
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













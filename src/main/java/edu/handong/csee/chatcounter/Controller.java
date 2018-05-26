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
import java.util.Iterator;


public class Controller {

	public static void main(String[] args) {
		
		List<String> forSet = new ArrayList<String>();
		String key;//for hash map
		Set set;// for hashmap
		Iterator iterator = null;// for hashmap
		
		int num=0;
		boolean right = false;
		String name, date, message,line; // for parsing message class
		ArrayList<String> aFileLines = new ArrayList<String>();
		FileLoader fl = new FileLoader();//file load
		Message messageInfo = new Message();
		MessageParser parser = new MessageParser();
		PrintAndSortResult n = new PrintAndSortResult();

		fl.path = "/Users/leehyunji0715/Desktop/ChatCounter/"; // fl 클래스 path에 경로 넣어준다. 실제로는 args[0]넣어줘야할듯 

		ArrayList<String> fNames = fl.getFileNames();
		System.out.println(fNames); // 파일 이름을 받아온다. 

		for(int i=0;i<fNames.size();i++) { // 파일 하나 불러들여오기 
			//fl.printFileContents(fNames.get(i),fl.lineNum);//~~~~~~~~~~~~~파일 내용 프린트 하기~~~~~~~~~~~~~~~~
			aFileLines = fl.getFileContents(fNames.get(i));

			for(int j=0;j<fl.lineNum;j++) {
				line = aFileLines.get(j);
				//-------------------------------------------------------------//
				parser.parseMessage(fNames.get(i),line,right);// 각 라인을 파싱한다.	

				//System.out.println(parser.getmName()+" "+parser.getmDate()+" "+parser.getmMessage());
				
				if(parser.getmDate()==null || parser.getmName()==null || parser.getmMessage()==null) {
					continue;
				}

				set = messageInfo.nameToMessage.keySet(); //for get key in messageinfo.!!name!!
				iterator = set.iterator();

				
				//if(messageInfo.nameToNumber.get(parser.getmName())==0) {// 처음 넣는 해쉬맵 값이라면....{
					//forSet = messageInfo.nameToString.get(parser.getmName());
					forSet.add(parser.getmMessage());
					messageInfo.nameToString.put(parser.getmName(),forSet);
					num = messageInfo.nameToNumber.get(parser.getmName())+1;
					messageInfo.nameToNumber.put(parser.getmName(), num);
					continue;
				}

				while(iterator.hasNext()){// 해시맵 키: 네임 와 벨류: 메세지 비교하기 
					key = (String)iterator.next();
					if(parser.getmName().equals(key) && parser.getmMessage().equals(messageInfo.nameToMessage.get(key))) {
						//만약 중복이면, 그냥 빠져나옴 저장 ㄴㄴ ㅇㅋ 
						continue; // break문이 아닌 이유 : 다음 해시맵도 검사해야하기 때문이다. 
					}
					else {
						// 만약 중복 아니면, 저장하고 빠져나온다. Not ok
						forSet = messageInfo.nameToString.get(parser.getmName());
						forSet.add(parser.getmMessage());
						messageInfo.nameToString.put(parser.getmName(),forSet);
						num = messageInfo.nameToNumber.get(parser.getmName())+1;
						messageInfo.nameToNumber.put(parser.getmName(), num);
						
//						messageInfo.nameToMessage.put(parser.getmName(), parser.getmMessage());
//						num = messageInfo.nameToNumber.get(parser.getmName())+1;
//						messageInfo.nameToNumber.put(parser.getmName(), num);
//						System.out.println("~~~~~~제발 되어~~~~~");
					}
				}
				num -= parser.redundancy;
				messageInfo.nameToNumber.put(parser.getmName(), num);
			}
			fl.lineNum = 0;
		}
	}





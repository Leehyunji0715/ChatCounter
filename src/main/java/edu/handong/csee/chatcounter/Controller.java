package edu.handong.csee.chatcounter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javax.swing.text.html.HTMLDocument.Iterator;

public class Controller {

	public static void main(String[] args) {
		String name, date, message,line; // for parsing message class
		int lineNum=0;
		ArrayList<String> aFileLines = new ArrayList<String>();
		FileLoader fl = new FileLoader();//file load
		MessageParser parser = new MessageParser();
		PMCounter counter = new PMCounter();
		Message messageInfo = new Message();
		
		Map <String, String> DateAndName = new HashMap<String,String>();

		fl.path = "/Users/leehyunji0715/Desktop/ChatCounter/";
		
		ArrayList<String> fNames = fl.getFileNames();
		System.out.println(fNames); // 파일 이름을 받아온다. 

		for(int i=0;i<fNames.size();i++) {
			aFileLines = fl.getFileContents(fNames.get(i), lineNum);
			for(int j=0;j<lineNum;j++) {
				line = aFileLines.get(j); // 한라인을 불러들인다. 
				//---------------------------------
//				parser.parseMessage(fNames.get(i),line);// 각 라인을 파싱한다.
//				
//				Set set = messageInfo.dateToMessage.keySet(); //for get key
//				java.util.Iterator iterator = set.iterator();
//				while(iterator.hasNext()){
//					  String key = (String)iterator.next();
//					  String valueName = (String) messageInfo.dateToMessage.get(key);
//					  System.out.println("hashMap Key : " + key);
//
//					}
				//---------------------------------------		
//				if(messageInfo.dateToMessage      parser.getmDate() parser.getmName() parser.getmMessage()) {
//					
//				}
//				
				//비교 할때는 날짜로 하지말것!!! 어떤자료는 안나와있다.. 그래서 이름이랑 메세지를 이용해서 중복검사하는 것이 나을 것 같다. 
//현재 : 				
				//중복체크 : 현재라인의 데이트, 이름, 내용이 모두 기존의 해쉬맵 값과 동일한지 체크한다.
				//중복체크 하고, 중복시 그냥 뛰어넘기, 중복아니면 MessageInfo에 저장하고, 넘버++ 하기....
				
				
				
				//**** 어쩌면 아래 클래스는 필요없을지도.....*****
				//counter.countHowManySay(fNames.get(i), messageInfo);// 중복있는지 확인한다. 카운트 해서 저장한다. 
				
			}
		}
		//System.out.println(fl.getFileContents(fNames.get(0)));
		
		// 경로 입력받기 
		// 파일 불러들어오기
		// 파일 라인을 각 뭐인지 분석하기  name, date, message (Message Parser)
		// 분석하고 중복일시 그냥 안셈		name, date, message가 기존것과 중복인지 체크한다.
		// 중복이 아닌다면 그 해당하는 사람의 말한 카운트를 올린다
		
	}

}

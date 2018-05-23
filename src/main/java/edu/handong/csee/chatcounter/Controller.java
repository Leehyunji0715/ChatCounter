package edu.handong.csee.chatcounter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

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
				parser.parseMessage(fNames.get(i),line);// 각 라인을 파싱한다.
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

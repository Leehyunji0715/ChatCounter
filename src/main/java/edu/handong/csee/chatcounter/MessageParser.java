package edu.handong.csee.chatcounter;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

public class MessageParser {
	FileLoader fl = new FileLoader();
	//private ArrayList<String> mUser;
	//HashSet mUser = new HashSet();
	private String inputFileName;
	private String mName;
	private String mDate;
	private String mMessage;
	public MessageParser() {
		 
	}
	public MessageParser(String fileName) {
		this.inputFileName = fileName;
	}
	
	//public void parseMessage(Message m, String line) { //메세지를 구분해서 해쉬맵에 집어넣어야 한다. 한라인씩 메세지 분석할꺼임 
	//public void parseMessage(String fileName, Message m) {
	public void parseMessage(String fileName, Message m, String mDate, String mName, String mMessage) {
		if(inputFileName.contains(".csv")) {// 파일에 따라 데이타 저장방식이 다르므로!! 
			mMessage = ""; //mac 뛰어쓰기
			mDate = "";
			mName = "";
			//만약에 기존의 값과 다 중복된다면 그냥 끝냄
			// 만약 일정이 키값과 동일하고, 컨텐츠도 벨류랑 동일하면 끝낸
			
		}
		else if(inputFileName.contains(".txt")) {
			mMessage = "";//wimdow 띄어쓰기 ㄴ
			mDate = "";
			mName = "";
		}
			m.DateToMessage.put(mDate, mMessage);
			m.mUser.add(mName); // 인스턴스 이름  
		}
	}


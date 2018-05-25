package edu.handong.csee.chatcounter;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.regex.Pattern;

public class MessageParser {
	FileLoader fl = new FileLoader();
	private String mName;
	private String mDate;
	private String mMessage;
	
	public MessageParser() {
		 
	}
	public String getmName() {
		return mName;
	}
	public String getmDate() {
		return mDate;
	}
	public String getmMessage() {
		return mMessage;
	}
	
	//public void parseMessage(Message m, String line) { //메세지를 구분해서 해쉬맵에 집어넣어야 한다. 한라인씩 메세지 분석할꺼임 
	//public void parseMessage(String fileName, Message m) {
	public void parseMessage(String fileName, String line) {
		//Pattern p = Pattern.compile("(^[0-9]*$)"); // ********************************정규식 패턴 짜기 ~~~~~~~

		if(fileName.contains(".csv")) {// 파일에 따라 데이타 저장방식이 다르므로!! 
			
			//케이스 1 : 날짜로 시작하는..라인
			//케이스 2 : 데이트 , 네임, 메세지  
			//케이스 3 : 다음줄에 있던 메세지가 이어서 출력됨, 그냥 메세지만 있는 경우 	
			String regPattern = "";
			if(line.contains("20")) {
				String[] strsplit = line.split(", ");
				mMessage = strsplit[2]; //mac 뛰어쓰기
				mDate = strsplit[0];
				mName = strsplit[1];
			}
		}
		else if(fileName.contains(".txt")) {
			// 라인 케이스 1 : ---------날짜 ------------ 한글 
			// 라인 케이스 2 : ----------날짜 ----------- 영어 
			// 라인 케이스 3 : [이름] [시간] 내용
			// 라인 케이스 8 : 그냥 다음 메시지 이어서 나올때 
			
			// 라인 케이스 4 : JC's 한동 자바프로그램 님과 카카오톡 대화 ---- 무시하기 모두 null 값넣어서 메인에서 null이면 컨티뉴하는 것을 한다!
			// 라인 케이스 5 : 저장한 날짜 : 2018-04-30 11:19:32 ---- 무시하기
			// 라인 케이스 6 :WARNING: An illegal reflective access operation has occurred---- 무시하기
			// 라인 케이스 7 : 공백---- 무시하기

			
			
			
			mMessage = "";//wimdow 띄어쓰기 ㄴ
			mDate = "";
			mName = "";
		}
		}
	}





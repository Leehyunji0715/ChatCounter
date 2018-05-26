package edu.handong.csee.chatcounter;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageParser {
	FileLoader fl = new FileLoader();
	int redundancy = 0;
	private String mName;
	private String mDate;
	private String mMessage;
	
	private String keyName;
	private String keyDate;
	private String keyMessage;
	private String temp;
	
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
	
	//public void parseMessage(String fileName, String line,) {
	public void parseMessage(String fileName, String line,Boolean Check) {
		Check = false;
		if(fileName.contains(".csv")) {// 파일에 따라 데이타 저장방식이 다르므로!! 
			
			//케이스 1 : 날짜로 시작하는..1라인
			String regPattern1 = "([0-9]+-[0-9]+-[0-9]+ [0-9]+:[0-9]+:[0-9]+),\"([^\"]+)\",\"([^\"]+)\"";
			Pattern p1 = Pattern.compile(regPattern1);
			// 케이스 2 : """" 2라인...
			String regPattern2 = "([0-9]+-[0-9]+-[0-9]+ [0-9]+:[0-9]+:[0-9]+),\"([^\"]+)\",\"([^\"]+)";
			Pattern p2 = Pattern.compile(regPattern2);
			//케이스 2-1 : 이어서 2번째 라인...
			String regPattern2_1 = "([^\"]+)\"";
			Pattern p2_1 = Pattern.compile(regPattern2_1);
			
			//****else 취급 ****
			//케이스 3 : 데이트 , 네임, 메세지  
			String regPattern3 = "(Date).(User).(Message)";
			
			//무시 ----------------------
			String exceptPattern1 = "([^ ]+) joined this chatroom.";// 채팅방 입장 
			Pattern e1 = Pattern.compile(exceptPattern1);
			
			String exceptPattern2 = "\\([^ ]+\\)"; //이모티콘 
			Pattern e2 = Pattern.compile(exceptPattern2);
			
			String exceptPattern3 = "Photo";// 사진 올릴 때 
			Pattern e3 = Pattern.compile(exceptPattern3);
			
			String exceptPattern4 = "JC's 한동 자바프로그램 님과 카카오톡 대화";// 그냥 나오는 거 
			Pattern e4 = Pattern.compile(exceptPattern4);
			
			String exceptPattern5 = "저장한 날짜 : [^ ]+ [^ ]+"; // 그냥 나오는 거 2
			Pattern e5 = Pattern.compile(exceptPattern5);
			
			String exceptPattern6 = "WARNING: An illegal reflective access operation has occurred"; //워닝 
			Pattern e6 = Pattern.compile(exceptPattern6);
			

			if(Pattern.matches(regPattern1, line)) {
				Matcher matcher = p1.matcher(line);
				if(matcher.find()) {
					mDate = matcher.group(1);
					mName = matcher.group(2);
					mMessage = matcher.group(3);
				}
			}
			else if(Pattern.matches(regPattern2, line)) {
				Check = true; // start of sentences
				Matcher matcher = p2.matcher(line);
				if(matcher.find()) {
					mDate = matcher.group(1);
					keyDate = matcher.group(1);
					//
					mName = matcher.group(2);
					keyName = matcher.group(2);
					//
					mMessage = matcher.group(3);
					keyMessage = matcher.group(3);
				}
			}
			else if(Pattern.matches(regPattern2_1, line)) { // ~~~~~ " 일때 
				Matcher matcher = p2_1.matcher(line);
				if(matcher.find()) {
					mName = keyName;
					mDate = keyDate;
					mMessage = keyMessage + "\n" +matcher.group(1);//중간에 띄어쓰기...맞나?
					
					keyName = null;
					keyDate = null;
					keyMessage = null;
				}
					Check = false; // end of sentences
					redundancy = 0;
			}
			else if(Check == true) { // middle of sentences
				mName = keyName;
				mDate = keyDate;
				mMessage = keyMessage + " " + line;	
				redundancy--;
			}
			
			else if(line.contains("저장한 날짜 : ")||line.contains(exceptPattern1)||line.contains(exceptPattern2)||
					line.equals(exceptPattern3)||line.equals(exceptPattern4)||
					line.equals(e5)||line.equals(e6)||
					line.equals("(이모티콘)")||line.contains(" joined this chatroom.")||
					line.contains("WARNIN: An")		)
					
			{ // 모든 에러의 특수 경우를 한곳에 모아~
				keyName = null;
				keyDate = null;
				keyMessage = null;
			}
			
			else if(Pattern.matches(exceptPattern1, line)||Pattern.matches(exceptPattern2, line)||
					Pattern.matches(exceptPattern3, line)||Pattern.matches(exceptPattern4, line)||
					Pattern.matches(exceptPattern5, line)||Pattern.matches(exceptPattern6, line)) { // 모든 에러의 특수 경우를 한곳에 모아~
				keyName = null;
				keyDate = null;
				keyMessage = null;
			}

			else {
				mName = null;
				mDate = null;
				mMessage = null;
			}
		}
		else if(fileName.contains(".txt")) {
			// 라인 케이스 1 : ---------날짜 ------------ 한글 
			String regPattern1 = "-+ ([0-9]{4}. [0-9]+. [0-9]+. .+) -+";
			Pattern p1 = Pattern.compile(regPattern1);
			
			// 라인 케이스 1 : ----------날짜 ----------- 영어 
			String regPattern1_1 = "-+ ([A-Za-z]+, [A-Za-z]+ [0-9]+. [0-9]+) -+";
			Pattern p1_1 = Pattern.compile(regPattern1_1);

			// 라인 케이스 2 : [이름] [시간] 내용// 키에다가 이름 저장한다. ( 한글 + 영어)
			String regPattern2 = "\\[(.+)\\] \\[(.+)\\] (.+)";
			Pattern p2 = Pattern.compile(regPattern2);
			
			// 라인 케이스 3 : 그냥 다음 메시지 이어서 나올때 //인스턴스 키를 불러와그 다음메세지 내용을 이어붙인다. 
			//String regPattern3 = ".+";
			//Pattern p3 = Pattern.compile(".+");
			//그냥 다음메세지가 아니다. 동일시간, 동일한 사람이 연달아서 메세지를 달면.. 그러면 \n을 덧임 되려나..??
			
			
			//*************
			String exceptPattern1 = " joined this chatroom.";
			Pattern e1 = Pattern.compile("([^ ]+) joined this chatroom.");
			
			String exceptPattern2 = "\\([^ ]+\\)";
			Pattern e2 = Pattern.compile(exceptPattern2);
			
			String exceptPattern3 = "Photo";
			Pattern e3 = Pattern.compile(exceptPattern3);
			
			String exceptPattern4 = "JC's 한동 자바프로그램 님과 카카오톡 대화";
			Pattern e4 = Pattern.compile(exceptPattern4);
			
			String exceptPattern5 = "저장한 날짜 : [^ ]+ [^ ]+";
			Pattern e5 = Pattern.compile(exceptPattern5);
			
			String exceptPattern6 = "WARNING: An illegal reflective access operation has occurred";
			Pattern e6 = Pattern.compile(exceptPattern6);
			
			String exceptPattern7 = "사진";
			Pattern e7 = Pattern.compile(exceptPattern7);
	
			if(Pattern.matches(regPattern1, line)) { // --------- 날짜 ------- (한글)
				Matcher matcher = p1.matcher(line);
				if(matcher.find()) {
					mDate = matcher.group(1);
					mName = null;
					mMessage = null;
					keyDate = matcher.group(1);
					Check = false;
				}
			}
			else if(Pattern.matches(regPattern1_1, line)) { // --------- 날짜 ------- (영어)
				Matcher matcher = p1_1.matcher(line);
				if(matcher.find()) {
					mDate = matcher.group(1);
					mName = null;
					mMessage = null;
					keyDate = matcher.group(1);
					Check = false;
				}
			}
			else if(Pattern.matches(regPattern2, line)) { //------ 전체 [ ] [ ] -----
				Matcher matcher = p2.matcher(line);
				if(matcher.find()) {
					mDate = keyDate +" "+ matcher.group(2);// full 날짜 
					//
					mName = matcher.group(1);
					//
					mMessage = matcher.group(3);
				}
			}
			else if(line.contains("저장한 날짜 : ")||line.equals(e1)||
					line.equals(e2)||line.equals(e3)||
					line.equals(exceptPattern4)||line.equals(e5)||
					line.equals(e6)||line.equals(e7)||
					line.contains(exceptPattern1)) 
			{ // 모든 에러의 특수 경우를 한곳에 모아~
				keyName = null;
				keyDate = null;
				keyMessage = null;
			}
			else {
				mName = null;
				mDate = null;
				mMessage = null;
			}
			}
		if(getmName()==null||getmDate()==null||getmMessage()==null) {
			mName = null;
			mDate = null;
			mMessage = null;
		}
		}
		}





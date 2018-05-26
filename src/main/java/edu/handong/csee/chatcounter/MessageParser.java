package edu.handong.csee.chatcounter;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageParser {
	FileLoader fl = new FileLoader();
	private String mName;
	private String mDate;
	private String mMessage;
	
	private String keyName;
	private String keyDate;
	private String keyMessage;
	
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
	
	public void parseMessage(String fileName, String line) {

		if(fileName.contains(".csv")) {// 파일에 따라 데이타 저장방식이 다르므로!! 
			
			//케이스 1 : 날짜로 시작하는..1라인
			String regPattern1 = "([0-9]+-[0-9]+-[0-9]+ [0-9]+:[0-9]+:[0-9]+),\"([^\"]+)\",\"([^\"]+)\"";
			Pattern p1 = Pattern.compile("([0-9]+-[0-9]+-[0-9]+ [0-9]+:[0-9]+:[0-9]+),\"([^\"]+)\",\"([^\"]+)\"");
			// 케이스 2 : """" 2라인...
			String regPattern2 = "([0-9]+-[0-9]+-[0-9]+ [0-9]+:[0-9]+:[0-9]+),\"([^\"]+)\",\"([^\"]+)";
			Pattern p2 = Pattern.compile("([0-9]+-[0-9]+-[0-9]+ [0-9]+:[0-9]+:[0-9]+),\"([^\"]+)\",\"([^\"]+)");
			//케이스 2-1 : 이어서 2번째 라인...
			String regPattern2_1 = "([^\"]+)\"";
			Pattern p2_1 = Pattern.compile("([^\"]+)\"");
			
			//****else 취급 ****
			//케이스 3 : 데이트 , 네임, 메세지  
			String regPattern3 = "(Date).(User).(Message)";
			
			//무시 ----------------------
			String exceptPattern1 = "([^ ]+) joined this chatroom.";
			Pattern e1 = Pattern.compile("([^ ]+) joined this chatroom.");
			String exceptPattern2 = "\\([^ ]+\\)";
			Pattern e2 = Pattern.compile("\\([^ ]+\\)");
			String exceptPattern3 = "Photo";
			Pattern e3 = Pattern.compile("Photo");
			String exceptPattern4 = "JC's 한동 자바프로그램 님과 카카오톡 대화";
			Pattern e4 = Pattern.compile("JC's 한동 자바프로그램 님과 카카오톡 대화");
			String exceptPattern5 = "저장한 날짜 : [^ ]+ [^ ]+";
			Pattern e5 = Pattern.compile("저장한 날짜 : [^ ]+ [^ ]+");
			String exceptPattern6 = "WARNING: An illegal reflective access operation has occurred";
			Pattern e6 = Pattern.compile("WARNING: An illegal reflective access operation has occurred");
			
			//JC's 한동 자바프로그램 님과 카카오톡 대화 ---- 무시하기
			//저장한 날짜 : 2018-05-14 12:04:12 ---- 무시하기
			// 라인 케이스 4 : WARNING: An illegal reflective access operation has occurred---- 무시하기
			if(Pattern.matches(regPattern1, line)) {
				Matcher matcher = p1.matcher(line);
				if(matcher.find()) {
					mDate = matcher.group(1);
					mName = matcher.group(2);
					mMessage = matcher.group(3);
				}
			}
			else if(Pattern.matches(regPattern2, line)) {
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
			else if(Pattern.matches(regPattern3, line)) {
				Matcher matcher = p2_1.matcher(line);
				if(matcher.find()) {
					mName = keyName;
					mDate = keyDate;
					mMessage = keyMessage + "keyMac\nnextMac" +matcher.group(1); //중간에 띄어쓰기...맞나?
					keyName = null;
					keyDate = null;
					keyMessage = null;
				}
			}
			else if(Pattern.matches(exceptPattern1, line)||Pattern.matches(exceptPattern2, line)||Pattern.matches(exceptPattern3, line)||Pattern.matches(exceptPattern4, line)||Pattern.matches(exceptPattern5, line)||Pattern.matches(exceptPattern6, line)) { // 모든 에러의 특수 경우를 한곳에 모아~
				keyName = null;
				keyDate = null;
				keyMessage = null;
			}
			else {//-------------- 나머지 무시 케이스----------
				mName = null;
				mDate = null;
				mMessage = null;
			}
		}
		else if(fileName.contains(".txt")) {
			// 라인 케이스 1 : ---------날짜 ------------ 한글 
			String regPattern1 = "-+ ([0-9]{4}. [0-9]+. [0-9]+. .+) -+";
			Pattern p1 = Pattern.compile("-+ ([0-9]{4}. [0-9]+. [0-9]+. .+) -+");
			// 라인 케이스 1 : ----------날짜 ----------- 영어 
			String regPattern1_1 = "-+ ([A-Za-z]+, [A-Za-z]+ [0-9]+. [0-9]+) -+";
			Pattern p1_1 = Pattern.compile("-+ ([A-Za-z]+, [A-Za-z]+ [0-9]+. [0-9]+) -+");

			//String regPattern2 = "\"[^-]+\""; // 한글이랑 영어 정규식은 동일하나, date를 다른 날로 인식함, 이건 메인에서 이름과 메세지로 중복으로 세지 말아야 
			// 라인 케이스 2 : [이름] [시간] 내용// 키에다가 이름 저장한다.
			String regPattern2 = "\\[(.+)\\] \\[(.+)\\] (.+)";
			Pattern p2 = Pattern.compile("\\[(.+)\\] \\[(.+)\\] (.+)");
			//라인 케이스 2 : [이름] [시간] 내용 + 영어버전 시간.. AM, PM // String pattern2 와 동일한 정규식 가진다 
			
			// 라인 케이스 3 : 그냥 다음 메시지 이어서 나올때 //인스턴스 키를 불러와그 다음메세지 내용을 이어붙인다. 
			String regPattern3 = ".+";
			Pattern p3 = Pattern.compile(".+");
			//그냥 다음메세지가 아니다. 동일시간, 동일한 사람이 연달아서 메세지를 달면.. 그러면 \n을 덧임 되려나..??
			//*************
			String exceptPattern1 = "([^ ]+) joined this chatroom.";
			Pattern e1 = Pattern.compile("([^ ]+) joined this chatroom.");
			String exceptPattern2 = "\\([^ ]+\\)";
			Pattern e2 = Pattern.compile("\\([^ ]+\\)");
			String exceptPattern3 = "Photo";
			Pattern e3 = Pattern.compile("Photo");
			String exceptPattern4 = "JC's 한동 자바프로그램 님과 카카오톡 대화";
			Pattern e4 = Pattern.compile("JC's 한동 자바프로그램 님과 카카오톡 대화");
			String exceptPattern5 = "저장한 날짜 : [^ ]+ [^ ]+";
			Pattern e5 = Pattern.compile("저장한 날짜 : [^ ]+ [^ ]+");
			String exceptPattern6 = "WARNING: An illegal reflective access operation has occurred";
			Pattern e6 = Pattern.compile("WARNING: An illegal reflective access operation has occurred");
	
			if(Pattern.matches(regPattern1, line)) { // --------- 날짜 ------- (한글)
				Matcher matcher = p1.matcher(line);
				if(matcher.find()) {
//					mDate = matcher.group(1);
//					mName = null;
//					mMessage = null;
					keyDate = matcher.group(1);
				}
			}
			else if(Pattern.matches(regPattern1_1, line)) { // --------- 날짜 ------- (영어)
				Matcher matcher = p1_1.matcher(line);
				if(matcher.find()) {
					keyDate = matcher.group(1);
				}
			}
			else if(Pattern.matches(regPattern2, line)) {
				Matcher matcher = p2.matcher(line);
				if(matcher.find()) {
					mDate = keyDate +" "+ matcher.group(2);
					//
					mName = matcher.group(1);
					keyName = matcher.group(1);
					//
					mMessage = matcher.group(3);
					keyMessage = matcher.group(3);
				}
			}
			else if(line.equals(e1)||line.equals(e2)||line.equals(e3)||line.equals(e4)||line.equals(e5)||line.equals(e6)) { // 모든 에러의 특수 경우를 한곳에 모아~
				keyName = null;
				keyDate = null;
				keyMessage = null;
			}
			else if(Pattern.matches(regPattern3, line)) {
				Matcher matcher = p3.matcher(line);
				if(matcher.find()) {
					mMessage = keyMessage + "key\nnext" + matcher.group(); //중간에 엔터 : 다른 줄말이라는 것을 알려준다.
					keyName = null;
					keyDate = null;
					keyMessage = null;
				}
			}
			else {
				mName = null;
				mDate = null;
				mMessage = null;
			}
		}
		}
	}





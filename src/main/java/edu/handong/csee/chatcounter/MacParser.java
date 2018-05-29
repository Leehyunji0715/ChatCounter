package edu.handong.csee.chatcounter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MacParser {
	
	private String keyName;
	private String keyDate;
	private String keyMessage;
	String longSentence;
	
	public void parseMessage(String fileName, String line,Boolean Check,Message m) {
		Check = false;		
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
			String exceptPattern2 = "\\([^ ]+\\)"; //이모티콘 
			Pattern e2 = Pattern.compile(exceptPattern2);
			String exceptPattern3 = "Photo";// 사진 올릴 때 
			String exceptPattern4 = "JC's 한동 자바프로그램 님과 카카오톡 대화";// 그냥 나오는 거 
			String exceptPattern5 = "저장한 날짜 : [^ ]+ [^ ]+"; // 그냥 나오는 거 2
			Pattern e5 = Pattern.compile(exceptPattern5);
			String exceptPattern6 = "WARNING: "; //워닝 
			

			if(Pattern.matches(regPattern1, line)) {
				Matcher matcher = p1.matcher(line);
				if(matcher.find()) {
					m.setmDate(matcher.group(1));
					m.setmName(matcher.group(2));
					m.setmMessage(matcher.group(3));
				}
			}
			else if(Pattern.matches(regPattern2, line)) {
				Check = true; // start of sentences
				Matcher matcher = p2.matcher(line);
				if(matcher.find()) {
					m.setmDate(matcher.group(1));
					keyDate = matcher.group(1);
					//
					m.setmName(matcher.group(2));
					keyName = matcher.group(2);
					//
					m.setmMessage(matcher.group(3));
					keyMessage = matcher.group(3);
				}
			}
			else if(Pattern.matches(regPattern2_1, line)) { // ~~~~~ " 일때 
				Matcher matcher = p2_1.matcher(line);
				if(matcher.find()) {
					m.setmName(keyName);
					m.setmDate(keyDate);
					m.setmMessage(keyMessage + "\n" +matcher.group(1)); //중간에 띄어쓰기...맞나?
					
					keyName = null;
					keyDate = null;
					keyMessage = null;
				}
					Check = false; // end of sentences
			}
			else if(Check == true) { // middle of sentences
				m.setmName(keyName);
				m.setmDate(keyDate);
				m.setmMessage(keyMessage + " " + line);
			}
			
			else if(line.contains("저장한 날짜 : ")||line.contains(exceptPattern1)||line.contains(exceptPattern2)||
					line.equals(exceptPattern3)||line.equals(exceptPattern4)||
					line.equals(e5)||line.startsWith(exceptPattern6)||
					line.equals("(이모티콘)")||line.endsWith(" joined this chatroom.")||
					line.startsWith("WARNIN: An")		
					)
					
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
				m.setmName(null);
				m.setmDate(null);
				m.setmMessage(null);
			}
		}
}
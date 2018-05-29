package edu.handong.csee.chatcounter;
/**
 * This class is for parsing window file
 * String prevDate has value of date
 * public void parseMessage(String fileName, String line, Message m) is parsing message
 * 
 * 
 */
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WindowParser {
	String prevDate;
	ChangeMonthToNum changer = new ChangeMonthToNum();
	ChangeNumToString changerAM = new ChangeNumToString();
	ChangePMToString changerPM = new ChangePMToString();
	int hour;
	String shortSentence;
	public void parseMessage(String fileName, String line, Message m) {
		// 라인 케이스 1 : ---------날짜 ------------ 한글 
		String regPattern1 = "-+ (([0-9]{4}). ([0-9]+). ([0-9]+). (.+)) -+";
		Pattern p1 = Pattern.compile(regPattern1);

		// 라인 케이스 1 : ----------날짜 ----------- 영어 
		String regPattern1_1 = "-+ (([A-Za-z]+), ([A-Za-z]+) ([0-9]+). ([0-9]+)) -+";
		Pattern p1_1 = Pattern.compile(regPattern1_1);
		
		// 라인 케이스 2 : [이름] [시간] 내용// 키에다가 이름 저장한다. ( 한글 + 영어)
		String regPattern2 = "\\[(.+)\\] \\[(.+) ([0-9]+):([0-9]+)\\] (.+)";
		Pattern p2 = Pattern.compile(regPattern2);
		//*************
		String exceptPattern1 = " joined this chatroom.";
		String exceptPattern2 = "\\([^ ]+\\)";
		Pattern e2 = Pattern.compile(exceptPattern2);
		String exceptPattern3 = "Photo";
		String exceptPattern4 = " 님과 카카오톡 대화";
		String exceptPattern5 = "저장한 날짜 : ";
		String exceptPattern6 = "WARNING: An illegal reflective access"; // operation has occurred
		String exceptPattern7 = "사진";
		if(Pattern.matches(regPattern2, line)) { //------ 전체 [ ] [ ] -----
			Matcher matcher = p2.matcher(line);
			if(matcher.find()) { //[이름] [시간] 내용
				m.setmName(matcher.group(1));
				m.setmMessage(matcher.group(3));
				// 날짜 + 시간 변환 
				if(matcher.group(2).contains("AM")||matcher.group(2).contains("오전")) {
					hour = Integer.parseInt(matcher.group(3));
					m.setmDate(prevDate+changerAM.changeToString(hour)+matcher.group(4));
				}
				else if(matcher.group(2).contains("PM")||matcher.group(2).contains("오후")) {
					hour = Integer.parseInt(matcher.group(3));
					m.setmDate(prevDate+changerPM.changeToString(hour)+":"+matcher.group(4));
				}
			}
			//prevDate = null;
		}
		else if(Pattern.matches(regPattern1, line)) {
			Matcher matcher = p1.matcher(line);
			ChangeMonthToNum cmonth = new ChangeMonthToNum();
			ChangeDayToString cday = new ChangeDayToString();
			String month, day,year;
			try {
				year = matcher.group(2);
				month = cmonth.changeToNum(matcher.group(3));
				day = cday.changeToDay(matcher.group(4));
			}
			catch(Exception e) {
				return;
			}
			//if 문은 할필요 없을 듯 
			if(matcher.find()) {
				prevDate = year+"-"+month+"-"+day;// + matcher;
			}
		}
		else if(Pattern.matches(regPattern1_1, line)) {
			Matcher matcher = p1_1.matcher(line);
			ChangeMonthToNum cmonth = new ChangeMonthToNum();
			ChangeDayToString cday = new ChangeDayToString();
			String month, day,year;
			try {
				year = matcher.group(5);
				month = cmonth.changeToNum(matcher.group(3));
				day = cday.changeToDay(matcher.group(4));
			}
			catch(Exception e) {
				return;
			}
			if(matcher.find()) {
				prevDate = year+"-"+month+"-"+day;
			}
		}

		else if(Pattern.matches(exceptPattern2, line)) {
			m.setmName(null);
			m.setmDate(null);
			m.setmMessage(null);
		}
		else if(line.contains(exceptPattern1)||line.equals(exceptPattern7)||
				line.equals(exceptPattern3)||line.endsWith(exceptPattern4)||
				line.contains(exceptPattern5)||line.startsWith(exceptPattern6)
				) 
		{
			m.setmName(null);
			m.setmDate(null);
			m.setmMessage(null);
		}
		else {
			m.setmName(null);
			m.setmDate(null);
			m.setmMessage(null);
		}
	}
}

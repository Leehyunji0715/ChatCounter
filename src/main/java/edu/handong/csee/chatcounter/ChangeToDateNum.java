package edu.handong.csee.chatcounter;

public class ChangeToDateNum {
	String changeToNum(String str) {
		String result = "";
		if(str == "January") {
			result = "01";
		}
		else if(str == "Feburary") {
			result = "02";
		}
		else if(str == "March") {
			result = "03";
		}
		else if(str == "April") {
			result = "04";
		}
		else if(str == "May") {
			result = "05";
		}
		else if(str == "June") {
			result = "06";
		}
		else if(str == "July") {
			result = "07";
		}
		else if(str == "August") {
			result = "08";
		}
		else if(str == "September") {
			result = "09";
		}
		else if(str == "October") {
			result = "10";
		}
		else if(str == "November") {
			result = "11";
		}
		else if(str == "December") {
			result = "12";
		}
		return result;
	}
}

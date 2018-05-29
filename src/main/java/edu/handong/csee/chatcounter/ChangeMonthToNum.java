package edu.handong.csee.chatcounter;
/**
 * 
 * This class is for alternate Month to String '00' form
 * @author leehyunji0715
 *
 */
public class ChangeMonthToNum {
	String changeToNum(String str) {
		String result = "";
		if(str == "January"||str=="1") {
			result = "01";
		}
		else if(str == "Feburary"||str=="2") {
			result = "02";
		}
		else if(str == "March"||str=="3") {
			result = "03";
		}
		else if(str == "April"||str=="4") {
			result = "04";
		}
		else if(str == "May"||str=="5") {
			result = "05";
		}
		else if(str == "June"||str=="6") {
			result = "06";
		}
		else if(str == "July"||str=="7") {
			result = "07";
		}
		else if(str == "August"||str=="8") {
			result = "08";
		}
		else if(str == "September"||str=="9") {
			result = "09";
		}
		else if(str == "October"||str=="10") {
			result = "10";
		}
		else if(str == "November"||str=="11") {
			result = "11";
		}
		else if(str == "December"||str=="12") {
			result = "12";
		}
		return result;
	}
}

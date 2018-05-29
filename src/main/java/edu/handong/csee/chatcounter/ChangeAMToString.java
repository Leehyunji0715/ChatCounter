package edu.handong.csee.chatcounter;
/**
 * This class is for alternate AM time to String '00' form
 * @author leehyunji0715
 *
 */
public class ChangeAMToString {
	public String changeToString(int num) {
		if(num==1) {
			return "01";
		}
		else if(num==2) {
			return "02";
		}
		else if(num==3) {
			return "03";
		}
		else if(num==4) {
			return "04";
		}
		else if(num==5) {
			return "05";
		}
		else if(num==6) {
			return "06";
		}
		else if(num==7) {
			return "07";
		}
		else if(num==8) {
			return "08";
		}
		else if(num==9) {
			return "09";
		}
		else if(num==10) {
			return "10";
		}
		else if(num==11) {
			return "11";
		}
		else if(num==12) {
			return "00";
		}
		return null;
	}
}

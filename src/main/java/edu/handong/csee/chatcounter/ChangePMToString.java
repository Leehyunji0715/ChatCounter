package edu.handong.csee.chatcounter;
/**
 * This class is for alternate PM time to String '00' form
 * @author leehyunji0715
 *
 */
public class ChangePMToString {
	public String changeToString(int num) {
		if(num==1) {
			return "13";
		}
		else if(num==2) {
			return "14";
		}
		else if(num==3) {
			return "15";
		}
		else if(num==4) {
			return "16";
		}
		else if(num==5) {
			return "17";
		}
		else if(num==6) {
			return "18";
		}
		else if(num==7) {
			return "19";
		}
		else if(num==8) {
			return "20";
		}
		else if(num==9) {
			return "21";
		}
		else if(num==10) {
			return "22";
		}
		else if(num==11) {
			return "23";
		}
		else if(num==12) {
			return "00";
		}
		return null;
	}
}

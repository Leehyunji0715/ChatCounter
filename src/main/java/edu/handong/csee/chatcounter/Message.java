package edu.handong.csee.chatcounter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
/**
 * This class has String value named mMessage, mName, mDate;
 * For those Strings are private, each of them has getter and setter method
 * @author leehyunji0715
 *
 */
public class Message {
	String mMessage;
	String mName;
	String mDate;
	//HashMap<String, List<String>> nameToString = new HashMap<String, List<String>>();
	// 메인에서 messages.put(user, new ArrayList<String>();
	public String getmMessage() {
		return mMessage;
	}
	public void setmMessage(String mMessage) {
		this.mMessage = mMessage;
	}
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public String getmDate() {
		return mDate;
	}
	public void setmDate(String mDate) {
		this.mDate = mDate;
	}

	
}
package edu.handong.csee.chatcounter;

import java.util.ArrayList;

public class Message {
	private ArrayList<String> mUser;
	public ArrayList<String> getmUser() {
		return mUser;
	}
	public void setmUser(ArrayList<String> mUser) {
		this.mUser = mUser;
	}
	public ArrayList<String> getmDate() {
		return mDate;
	}
	public void setmDate(ArrayList<String> mDate) {
		this.mDate = mDate;
	}
	private ArrayList<String> mDate;
}
class MessageWindow extends Message{
	private ArrayList<String> mMessages;

	public ArrayList<String> getmMessages() {
		return mMessages;
	}

	public void setmMessages(ArrayList<String> mMessages) {
		this.mMessages = mMessages;
	}
}
class MessageMac extends Message{
	private ArrayList<String> mMessages;

	public ArrayList<String> getmMessages() {
		return mMessages;
	}

	public void setmMessages(ArrayList<String> mMessages) {
		this.mMessages = mMessages;
	}
}

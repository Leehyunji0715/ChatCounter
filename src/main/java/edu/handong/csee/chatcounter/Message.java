package edu.handong.csee.chatcounter;

import java.util.ArrayList;

public class Message {
	private ArrayList<String> mUser;
	private ArrayList<String> mDate;
}
class MessageWindow extends Message{
	private ArrayList<String> mMessages;
}
class MessageMac extends Message{
	private ArrayList<String> mMessages;
}

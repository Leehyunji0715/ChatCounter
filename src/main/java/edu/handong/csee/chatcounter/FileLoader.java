package edu.handong.csee.chatcounter;

import java.io.File;
import java.util.ArrayList;

public class FileLoader {
	//String fileName="";
	private ArrayList<String> messages;
	private ArrayList<String> fileNames;
	//final String fatternName = "자바";
	String path = "/Users/leehyunji0715/Desktop/ChatCounter/";
//	File file = new File(path);
//	public void readDirectory(String path) {
//	}
	//private ArrayList<String> getFileNames(){
	
	private void getFileNames(){
		File file = new File(path);
		//ArrayList<String> fileNames = new ArrayList<String>();
		fileNames = new ArrayList<String>();
		String[] fList = file.list();
		int i=0;
		while(true) {
			//fileNames.add(file.getName());
			fileNames.add(fList[i]); 
			if(!file.exists()) {
				break;
			}
			i++;
		}
	}
	public void getMessages(String fName) {
		if(fName.contains(".txt")) { // 만약 txt파일이라
			MessageWindow msgW = new MessageWindow();
		}
		else if(fName.contains(".csv")) {//csv 파일이라면...
			MessageMac msgM = new MessageMac();
		}
	}
}










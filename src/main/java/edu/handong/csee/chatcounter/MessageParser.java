package edu.handong.csee.chatcounter;
import java.io.File;

public class MessageParser {
		FileLoader fl = new FileLoader();
		
	public void parseMessage(String fileName) {
		fl.getFileContents(fileName);
		if(fileName.contains(".csv")) {
			MessageMac msgMac = new MessageMac();
			
			
		}
		else if(fileName.contains(".txt")) {
			MessageWindow msgWin = new MessageWindow();
			
			
		}
	}
	
	
	
	
	
}

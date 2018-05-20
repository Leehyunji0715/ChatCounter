package edu.handong.csee.chatcounter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller {

	public static void main(String[] args) {
		FileLoader fl = new FileLoader();
		//FileLoader fl2 = new FileLoader();
		
		fl.path = "/Users/leehyunji0715/Desktop/ChatCounter/";
		ArrayList<String> fNames = fl.getFileNames();
		System.out.println(fNames);
		for(int i=0;i<fNames.size();i++) {
			fl.getFileContents(fNames.get(i));
		}
	}

}

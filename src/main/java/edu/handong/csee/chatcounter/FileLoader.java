package edu.handong.csee.chatcounter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileLoader {
	private ArrayList<String> fileNames = new ArrayList<String>();
	public void setFileNames(ArrayList<String> fileNames) {
		this.fileNames = fileNames;
	}
	String path;
//	File file = new File(path);
//	public void readDirectory(String path) {
//	}
	//private ArrayList<String> getFileNames(){
	
	public ArrayList<String> getFileNames(){
		File file = new File(path);
		String[] fList = file.list();
		int i=0;
		for(int j=0;j<fList.length;j++) {  
			//fileNames.add(file.getName());
			fileNames.add(fList[i]); 
			i++;
		}
		return fileNames;
	}
	public void getMessages(String fName) {
		Scanner inputStream = null;
		String line;
		try {
			inputStream = new Scanner (new File (fName),"UTF-8");
	}
		catch(FileNotFoundException e) {
			System.out.println ("Error opening the file " + fName);
            System.exit (0);
		}
		while(inputStream.hasNextLine()) {
			line = inputStream.nextLine();
			System.out.println(line);
		}
		
		if(fName.contains(".txt")) { // 만약 txt파일이라 -> 윈도
			MessageWindow msgWin = new MessageWindow();
			//msgWin.getmUser();
			//msgWin.getmDate();
			//msgWin.getmMessages();//have to differentiate from macbook
		}
		else if(fName.contains(".csv")) {//csv 파일이라면... ->맥
			MessageMac msgMac = new MessageMac();
			//msgMac.getmUser();
			//msgMac.getmDate();
			//msgMac.getmMessages();
		}
		inputStream.close ();
	}
}
//	public void sample() {
//		String fileName = fileNames.get(0);
//		Scanner inputStream = null;
//		System.out.println("The file"+fileName + "\ncontains the following lines\n");
//		try {
//			inputStream = new Scanner (new File (fileName),"UTF-8");
//	}
//		catch(FileNotFoundException e) {
//			System.out.println ("Error opening the file " + fileName);
//            System.exit (0);
//		}
//		while(inputStream.hasNextLine()) {
//			String line = inputStream.nextLine();
//			System.out.println (line);
//		}
//		inputStream.close ();
//	}
//}










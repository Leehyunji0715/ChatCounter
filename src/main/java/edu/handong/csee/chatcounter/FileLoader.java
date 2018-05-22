package edu.handong.csee.chatcounter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileLoader {
	private ArrayList<String> fileNames = new ArrayList<String>();
	static String path;
	
	public void setFileNames(ArrayList<String> fileNames) {
		this.fileNames = fileNames;
	}
	public ArrayList<String> getFileNames(){
		File file = new File(path);
		String[] fList = file.list();
		int i=0;
		for(int j=0;j<fList.length;j++) {  
			fileNames.add(fList[i]); 
			i++;
		}
		return fileNames;
	}
//	public void getMessages(String fName) {
//		Scanner inputStream = null;
//		//MessageParser parser = new MessageParser();// instantiate MessgaeParser class
//		String line;
//		System.out.println("The file name is "+fName);
//		try {
//			inputStream = new Scanner (new File (fName),"UTF-8");
//	}
//		catch(FileNotFoundException e) {
//			System.out.println ("Error opening the file " + fName);
//            System.exit (0);
//		}
//		while(inputStream.hasNextLine()) {
//			line = inputStream.nextLine();
//			System.out.println(line);
//		}	
//		inputStream.close ();
//	}
	public void getFileContents(String fileName) {
		File callFile = new File(path+fileName);
		MessageParser parser = new MessageParser(fileName);
		try {
			
			Scanner inputStream = new Scanner(callFile);
			String line;// = inputStream.nextLine();
			while(inputStream.hasNextLine()) {
				line = inputStream.nextLine();
				//parser.parseMessage(line);
				
				//String[] ary = line.split(",");// split인데 나는 다른걸로 구분해야할듯,정규식????
				System.out.println(line);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Cannot find the file");
		}
	}
//	public void parseMessage(String fileName) {
//		getFileContents(fileName);
//		
//	}

}











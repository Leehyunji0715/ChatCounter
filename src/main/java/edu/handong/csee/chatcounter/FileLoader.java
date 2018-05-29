package edu.handong.csee.chatcounter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * This class's role is to load fileContents and fileNames
 * private ArrayList<String> fileNames is for getting fileNames
 * 
 * @author leehyunji0715
 */
public class FileLoader {
	private ArrayList<String> fileNames = new ArrayList<String>();
	static int lineNum;
	
	public ArrayList<String> getFileNames(String path){ 
		File file = new File(path);
		String[] fList = file.list();
		int i=0;
		for(int j=0;j<fList.length;j++) {  
			fileNames.add(fList[i]); 
			i++;
		}
		return fileNames;
	}  
	
	
	public ArrayList<String> getFileContents(String fileName,String path) {
		File callFile = new File(path+fileName); 
		ArrayList<String> fileLine = new ArrayList<String>();	
		try {
			Scanner inputStream = new Scanner(callFile);
			String line;
			while(inputStream.hasNextLine()) {
				line = inputStream.nextLine();
				fileLine.add(line);
				lineNum++;
			}	
		} catch (FileNotFoundException e) {
			System.out.println("Cannot find the file");
		}
		return fileLine;
	}


public void printFileContents(String fileName,int lineNum, String path) {
	File callFile = new File(path+fileName); 
	ArrayList<String> fileLine = new ArrayList<String>();
	
	try {
		Scanner inputStream = new Scanner(callFile);
		String line;
		while(inputStream.hasNextLine()) {
			line = inputStream.nextLine();
			System.out.println(line); 
		}	
	} catch (FileNotFoundException e) {
		System.out.println("Cannot find the file");
	}
}
}









package edu.handong.csee.chatcounter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileLoader {
	private ArrayList<String> fileNames = new ArrayList<String>();
	static String path;
	
	
	public ArrayList<String> getFileNames(){ // 파일 네임을 불러오는 메소드 1 
		File file = new File(path);
		String[] fList = file.list();
		int i=0;
		for(int j=0;j<fList.length;j++) {  
			fileNames.add(fList[i]); 
			i++;
		}
		return fileNames;
	} //완료!! 
	
	
	public ArrayList<String> getFileContents(String fileName,int lineNum) {
		File callFile = new File(path+fileName); //path에 있는 파일을 받아온다. 
		ArrayList<String> fileLine = new ArrayList<String>();	
		try {
			Scanner inputStream = new Scanner(callFile);
			String line;
			while(inputStream.hasNextLine()) {
			//	*****************************************
				line = inputStream.nextLine();
				fileLine.add(line);
				lineNum++;
			//  *****************************************
			}	
		} catch (FileNotFoundException e) {
			System.out.println("Cannot find the file");
		}
		return fileLine;
	}// complete...??????


public void printFileContents(String fileName,int lineNum) {
	File callFile = new File(path+fileName); //path에 있는 파일을 받아온다. 
	ArrayList<String> fileLine = new ArrayList<String>();
	
	try {
		
		Scanner inputStream = new Scanner(callFile);
		String line;
		while(inputStream.hasNextLine()) {
			line = inputStream.nextLine();
			System.out.println(line); // 파일 정상적인 출력 
		}	
	} catch (FileNotFoundException e) {
		System.out.println("Cannot find the file");
	}
}
}//***** complete!









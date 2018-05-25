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
	public ArrayList<String> getFileContents(String fileName,int lineNum) {
		File callFile = new File(path+fileName);
		MessageParser parser = new MessageParser(fileName);
		ArrayList<String> fileLine = new ArrayList<String>();
		
		try {
			
			Scanner inputStream = new Scanner(callFile);
			String line;// = inputStream.nextLine();
			while(inputStream.hasNextLine()) {
//				fileLine.add(inputStream.nextLine());
//				lineNum++;
			//	*****************************************
				line = inputStream.nextLine();
				System.out.println(line); // 파일 정상적인 출력 
			//  *****************************************
			}	
		} catch (FileNotFoundException e) {
			System.out.println("Cannot find the file");
		}
		return fileLine;
	}

}











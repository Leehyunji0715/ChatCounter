package edu.handong.csee.chatcounter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.Iterator;


public class Controller {

	public static void main(String[] args) {
			Cli cli = new Cli();
			cli.run(args);
			Message m = new Message();
			WindowParser wp = new WindowParser();
			MacParser mp = new MacParser();
			Boolean check = false;
			Map<String, ArrayList<Message>> map = new HashMap<String, ArrayList<Message>>();
			
			//*******cli**************
			
			//cli.path
			//**************fileLoader******************//
			FileLoader fl = new FileLoader();
			ArrayList<String> aFileLines = new ArrayList<String>();
			//fl.path = cli.path;
			//fl.path = "/Users/leehyunji0715/Desktop/ChatCounter/";
			ArrayList<String> fNames = fl.getFileNames(cli.path);
			System.out.println(fNames); // 파일 이름을 받아온다. 
			
			for(int i=0;i<fNames.size();i++) { // 파일 하나 불러들여오기 
				fl.printFileContents(fNames.get(i),fl.lineNum,cli.path);//~~~~~~~~~~~~~파일 내용 프린트 하기~~~~~~~~~~~~~~~~
				/*
				aFileLines = fl.getFileContents(fNames.get(i));// 하나의 파일 내용 로드하기 그것을 
				
					if(fNames.get(i).endsWith(".csv")) {
						MacParser mac = new MacParser();
						for(String aLine : aFileLines) {
							mac.parseMessage(fNames.get(i), aLine, check, m);
							if(!map.containsKey(m.getmName())){
								map.put(m.getmName(), new ArrayList <Message>());
								//새로운 해쉬맵을 new 한다. .put(user. New ArrayList <Message>());
							}
							else {
								
							}
						}
						
						
					}
					else if(fNames.get(i).endsWith(".txt")) {
						WindowParser window = new WindowParser();	
					}
			*/	
			}
		}	
}
	





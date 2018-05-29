package edu.handong.csee.chatcounter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.Iterator;


public class Controller {

	public static void main(String[] args) throws IOException {
			Cli cli = new Cli();
			cli.run(args);
			Message m = new Message();
			Boolean check = false;
			WindowParser window = new WindowParser();
			MacParser mac = new MacParser();
			Map<String, ArrayList<Message>> map = new HashMap<String, ArrayList<Message>>();
			String longSentence=null, shortSentence=null;
			
			
			//**************fileLoader******************//
			FileLoader fl = new FileLoader();
			ArrayList<String> aFileLines = new ArrayList<String>();
			//fl.path = "/Users/leehyunji0715/Desktop/ChatCounter/";
			ArrayList<String> fNames = fl.getFileNames(cli.path);
			System.out.println(fNames); // 파일 이름을 받아온다. 
			
			
			for(int i=0;i<fNames.size();i++) { // 파일 하나 불러들여오기 
				//fl.printFileContents(fNames.get(i),fl.lineNum,cli.path);
				//~~~~~~~~~~~~~파일 내용 프린트 하기~~~~~~~~~~~~~~~~
				aFileLines = fl.getFileContents(fNames.get(i), cli.path);
				// 하나의 파일 내용 로드하기 그것을 
				if(fNames.get(i).endsWith(".csv")) {
					mac = new MacParser();
					// macparser를 instantiate
					for(String aLine : aFileLines) {
						mac.parseMessage(fNames.get(i), aLine, check, m);
						longSentence = m.getmMessage();
						//if(!map.containsKey(m.getmName())){
							map.put(m.getmName(), new ArrayList <Message>(Arrays.asList(m))); // 선언후 바로 넣어주기 
							//새로운 해쉬맵을 new 한다. .put(user. New ArrayList <Message>());
						//}
					}
				}
						else if(fNames.get(i).endsWith(".txt")) {
							window = new WindowParser();
							for(String line : aFileLines) {
								window.parseMessage(fNames.get(i), line, m);
								shortSentence = m.getmMessage();
								//if(!map.containsKey(m.getmName())){
									map.put(m.getmName(), new ArrayList <Message>(Arrays.asList(m)));
									//새로운 해쉬맵을 new 한다.
								//}
						}
					}
				/*
				 * Redundancy Check
				 */
//				if(longSentence.contains(shortSentence)) {
//					System.out.println("");
//				}
//				for(HashMap<String, ArrayList<Message>()> h : map) {
//					
//				}
				for (Entry<String, ArrayList<Message>> pair: map.entrySet()) {
					int i1 =0,i2=0;
					ArrayList<Message> mes = new ArrayList<Message>();
					mes = pair.getValue();// ArrayList -- Message : 누구누구의 메세지 기록을 받는다. 
					String tempDate, tempMessage;
		            while(i1<mes.size()) {//메세지 기록 수 만큼 
		            		tempDate = mes.get(i1).mDate;
		            		tempMessage = mes.get(i1).mMessage;
		            		i2 = i1+1;
		            		while(i2<mes.size()) {// 그 다음 요소와 비교 
		            			if(mes.get(i2).mDate==tempDate&&mes.get(i2).mMessage.contains(tempMessage)) {
		            				mes.remove(i2);
		            				i2 = i1+1;
		            			}
		            			i2++;
		            		}
		            		i1++;
		            }
		        }
				}
			//PrintAndSortResult ps = new PrintAndSortResult(map);
			}
	}





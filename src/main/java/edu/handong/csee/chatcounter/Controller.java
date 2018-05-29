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
/**
 * This is Controller which shows a result of the number of KaKao Talk messages
 * m is Message Class
 * Boolean 'check' is for MacParser Class
 * mac is MacParserClass
 * window is WindowParser Class
 * (Hash)Map<String, ArrayList<Message>> map is for save data about name and Message m 
 * @author leehyunji0715
 *
 */

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
		System.out.println(fNames); // get file names


		for(int i=0;i<fNames.size();i++) { // call one file
			//fl.printFileContents(fNames.get(i),fl.lineNum,cli.path);
			//~~~~~~~~~~~~~print~Contents~~~~~~~~~~~~~~~
			aFileLines = fl.getFileContents(fNames.get(i), cli.path);

			if(fNames.get(i).endsWith(".csv")) {
				mac = new MacParser();
				// macparser를 instantiate
				for(String aLine : aFileLines) {
					mac.parseMessage(fNames.get(i), aLine, check, m);
					longSentence = m.getmMessage();
					//if(!map.containsKey(m.getmName())){
					map.put(m.getmName(), new ArrayList <Message>(Arrays.asList(m)));// at the same time
					//}
				}
			}
			else if(fNames.get(i).endsWith(".txt")) {
				window = new WindowParser();
				for(String line : aFileLines) {
					window.parseMessage(fNames.get(i), line, m);
					shortSentence = m.getmMessage();
					map.put(m.getmName(), new ArrayList <Message>(Arrays.asList(m)));
				}
			}
			/*
			 * Redundancy Check
			 */

			for (Entry<String, ArrayList<Message>> pair: map.entrySet()) {
				int i1 =0,i2=0;
				ArrayList<Message> mes = new ArrayList<Message>();
				mes = pair.getValue();// ArrayList -- Message : whos~~~
				String tempDate, tempMessage;
				while(i1<mes.size()) {//메세지 기록 수 만큼 
					tempDate = mes.get(i1).mDate;
					tempMessage = mes.get(i1).mMessage;
					i2 = i1+1;
					while(i2<mes.size()) {// compare to the next one
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





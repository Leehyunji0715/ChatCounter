package edu.handong.csee.chatcounter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Controller {

	public static void main(String[] args) {
		FileLoader fl = new FileLoader();
		String name, date, message;
		Map <String, String> DateAndName = new HashMap<String,String>();
		//FileLoader fl2 = new FileLoader();
		//path 입력받기 Scanner
		fl.path = "/Users/leehyunji0715/Desktop/ChatCounter/";
		ArrayList<String> fNames = fl.getFileNames();
		System.out.println(fNames);
		for(int i=0;i<fNames.size();i++) {
			fl.getFileContents(fNames.get(i));
		}
		String line;
		//System.out.println(fl.getFileContents(fNames.get(0)));
		
		// 경로 입력받기 
		// 파일 불러들어오기
		// 파일 라인을 각 뭐인지 분석하기  name, date, message 
		// 분석하고 중복일시 그냥 안셈		name, date, message가 기존것과 중복인지 체크한다.
		// 중복이 아닌다면 그 해당하는 사람의 말한 카운트를 올린다
		
		
		
		
		
	}

}

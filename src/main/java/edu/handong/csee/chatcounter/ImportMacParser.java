package edu.handong.csee.chatcounter;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
/**
 * Error
 * @author leehyunji0715
 *
 */
public class ImportMacParser {
	Message parsingMac(Message m, String path) throws IOException {
		try (
	            Reader reader = Files.newBufferedReader(Paths.get(path));
	            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
	                    .withHeader("Date", "Name", "Message")
	                    .withIgnoreHeaderCase()
	                    .withTrim());
	        ) {
	            for (CSVRecord csvRecord : csvParser) {
	                // Accessing values by the names assigned to each column
	                m.setmName(csvRecord.get("Name"));
	                m.setmDate(csvRecord.get("Date"));
	                m.setmMessage(csvRecord.get("Message"));

	                System.out.println("Record No - " + csvRecord.getRecordNumber());
	                System.out.println("---------------");

	                System.out.println("---------------\n\n");
	            }
	        }
		return m;
	    }
	}

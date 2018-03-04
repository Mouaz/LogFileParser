package utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

import entities.LogMessage;
import entities.Program;
import entities.Request;

public class LogFileParser {

	static Program p;
	
	public static void parseLogFileAndCreateProgram(String filePath, int topSlowerRequests) throws IOException{
		File file = new File(filePath);
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String line;
		long totalNumberProgramRun = 0;
		p = new Program(topSlowerRequests);
		/*
		 * loop through each log message
		 */
		while ((line = bufferedReader.readLine()) != null) {
			line = line.replaceAll("  ", " ");
			String[] parts = line.split(" ");
			LogMessage msg = new LogMessage();
			try{
				/*
				 * get date and time
				 */
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss,SSS");
			LocalDateTime  localDate = LocalDateTime.parse(parts[0]+" "+parts[1],formatter);
			msg.setDate(localDate.toLocalDate());
			msg.setTime(localDate.toLocalTime());
			p.addRequestToHoursData(msg.getTime().getHour());
			msg.setThreadId(parts[2]);
			msg.setUserContext(parts[3]);
			if(parts[4].contains("/")){
				/*
				 * url + query string
				 */
				String[] uriAndQuery = parts[4].split("\\?");
				msg.setURI(uriAndQuery[0]);
				if(uriAndQuery.length>1)
				msg.setQueryString(uriAndQuery[1]);
				int timeInMs = Integer.parseInt(parts[6]);
				msg.setDurationInMilliseconds(timeInMs);
				totalNumberProgramRun+= timeInMs;
				/*
				 * add arequest
				 */
				Request r = new Request();
				r.setId(msg.getURI());
				r.addDuration(timeInMs);
				p.addRequest(r);
			}else{
				/*
				 * resource with payload
				 */
				msg.setRequestedSourceName(parts[4]);
				String[] payLoad = new String[0];
				int i = 5;
				while(!parts[i].equals("in")){
					payLoad = Arrays.copyOf(payLoad, payLoad.length + 1);
					payLoad[i-5] = parts[i];
					i++;
				}
				msg.setDataPayload(payLoad);
				/*
				 * add to the duration
				 */
				int timeInMs = Integer.parseInt(parts[i+1]);
				msg.setDurationInMilliseconds(timeInMs);
				totalNumberProgramRun+= timeInMs;
				
				/*
				 * add arequest
				 */
				Request r = new Request();
				r.setId(msg.getRequestedSourceName());
				r.addDuration(timeInMs);
				p.addRequest(r);
			}
			}catch(Exception e){
				System.out.println(line);
				e.printStackTrace();
			}
		}
		fileReader.close();
		p.generateTopRequests();
		System.out.println(p.getTopRequests());
		p.setProgramRunTimeInMs(totalNumberProgramRun);
		p.drawHisto();
		System.out.println("Done...");
		System.out.println("Program has run in: "+totalNumberProgramRun+" milliseconds");
	}
}

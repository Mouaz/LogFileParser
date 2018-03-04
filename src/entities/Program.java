package entities;

import java.util.ArrayList;
import java.util.Arrays;
/*
 * @author mouaz
 * 
 * class to describe features of the log file in a program entity
 * 
 * understanding the class is not a big deal
 */
public class Program {

	private long programRunTimeInMs;
	private int numberOfTopRequests;
	private int[] hourlyNumberOfRequests;
	private ArrayList<LogMessage> logs;
	private ArrayList<Request> requests;
	private ArrayList<Request> topRequests;

	public Program(int topRequests){
		this.hourlyNumberOfRequests = new int[]{0,0,0,0,0,0,0,0,0,0,0,0};
		this.logs = new ArrayList<LogMessage>();
		this.requests = new ArrayList<Request>();
		this.topRequests = new ArrayList<Request>();
		this.numberOfTopRequests = topRequests;
	}
	
	public Program(long programRunTimeInMs, int numberOfTopRequests, int[] hourlyNumberOfRequests) {
		this.programRunTimeInMs = programRunTimeInMs;
		this.numberOfTopRequests = numberOfTopRequests;
		this.hourlyNumberOfRequests = hourlyNumberOfRequests;
	}
	
	public void generateTopRequests(){
		for (Request r1 : this.requests) {
			if(this.topRequests.size()<this.numberOfTopRequests)
				this.topRequests.add(r1);
			else
			for (Request r2 : this.topRequests) {
				int inx = topRequests.indexOf(r2);
				if(r1.getAverage()>r2.getAverage()){
					this.topRequests.set(inx, r1);
					break;
				}
			}
		}
	}
	public void addRequest(Request r){
		for (Request r2 : requests) {
			if(r2.getId().equals(r.getId())){
				int inx = requests.indexOf(r2);
				Request tmp = r2;
				tmp.addDuration(r.getAverage());
				requests.set(inx, tmp);
				return;
			}
		}
		requests.add(r);
	}
	public void drawHisto(){
		Histogram h = new Histogram();
		h.createDataset(hourlyNumberOfRequests);
		h.viewHistogram();
	}
	public void addRequestToHoursData(int index){
		hourlyNumberOfRequests[index] = hourlyNumberOfRequests[index]+1;
	}
	public void addLogMessage(LogMessage msg){
		this.logs.add(msg);
	}
	public long getProgramRunTimeInMs() {
		return programRunTimeInMs;
	}
	public int getNumberOfTopRequests() {
		return numberOfTopRequests;
	}
	public void setNumberOfTopRequests(int numberOfTopRequests) {
		this.numberOfTopRequests = numberOfTopRequests;
	}
	public int[] getHourlyNumberOfRequests() {
		return hourlyNumberOfRequests;
	}
	public void setHourlyNumberOfRequests(int[] hourlyNumberOfRequests) {
		this.hourlyNumberOfRequests = hourlyNumberOfRequests;
	}
	public void setProgramRunTimeInMs(long programRunTimeInMs) {
		this.programRunTimeInMs = programRunTimeInMs;
	}

	public ArrayList<LogMessage> getLogs() {
		return logs;
	}

	public void setLogs(ArrayList<LogMessage> logs) {
		this.logs = logs;
	}

	public ArrayList<Request> getRequests() {
		return requests;
	}

	public void setRequests(ArrayList<Request> requests) {
		this.requests = requests;
	}
	public ArrayList<Request> getTopRequests() {
		return topRequests;
	}

	public void setTopRequests(ArrayList<Request> topRequests) {
		this.topRequests = topRequests;
	}

	@Override
	public String toString() {
		return "Program [programRunTimeInMs=" + programRunTimeInMs + ", numberOfTopRequests=" + numberOfTopRequests
				+ ", hourlyNumberOfRequests=" + Arrays.toString(hourlyNumberOfRequests) + ", topLogs="
				+ requests + "]";
	}
}

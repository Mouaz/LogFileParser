package entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

/**
 * @author Mouaz Alabsawi
 * 
 * Description: Object to encapsulate the log message
 *
 */
public class LogMessage {

	private LocalDate date;
	private LocalTime time;
	private String threadId;
	private String userContext;
	private String URI;
	private String queryString;
	private int durationInMilliseconds;
	private String requestedSourceName;
	private String[] dataPayload;

	public LogMessage(LocalDate date, LocalTime time, String threadId, String userContext, String uRI,
			String queryString, int durationInMilliseconds, String requestedSourceName, String[] dataPayload) {
		this.date = date;
		this.time = time;
		this.threadId = threadId;
		this.userContext = userContext;
		URI = uRI;
		this.queryString = queryString;
		this.durationInMilliseconds = durationInMilliseconds;
		this.requestedSourceName = requestedSourceName;
		this.dataPayload = dataPayload;
	}

	@Override
	public String toString() {
		return "LogMessage [date=" + date + ", time=" + time + ", threadId=" + threadId + ", userContext=" + userContext
				+ ", URI=" + URI + ", queryString=" + queryString + ", durationInMilliseconds=" + durationInMilliseconds
				+ ", requestedSourceName=" + requestedSourceName + ", dataPayload=" + Arrays.toString(dataPayload)
				+ "]";
	}

	public LogMessage() {
		// TODO Auto-generated constructor stub
	}

	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public LocalTime getTime() {
		return time;
	}
	public void setTime(LocalTime time) {
		this.time = time;
	}
	public String getThreadId() {
		return threadId;
	}
	public void setThreadId(String threadId) {
		this.threadId = threadId;
	}
	public String getUserContext() {
		return userContext;
	}
	public void setUserContext(String userContext) {
		this.userContext = userContext;
	}
	public String getURI() {
		return URI;
	}
	public void setURI(String uRI) {
		URI = uRI;
	}
	public String getQueryString() {
		return queryString;
	}
	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}
	public int getDurationInMilliseconds() {
		return durationInMilliseconds;
	}
	public void setDurationInMilliseconds(int durationInMilliseconds) {
		this.durationInMilliseconds = durationInMilliseconds;
	}
	public String getRequestedSourceName() {
		return requestedSourceName;
	}
	public void setRequestedSourceName(String requestedSourceName) {
		this.requestedSourceName = requestedSourceName;
	}
	public String[] getDataPayload() {
		return dataPayload;
	}
	public void setDataPayload(String[] dataPayload) {
		this.dataPayload = dataPayload;
	}
	
}

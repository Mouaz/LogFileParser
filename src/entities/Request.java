package entities;

import java.util.ArrayList;

public class Request {

	private String id;
	
	private ArrayList<Integer> durations;
	
	public Request(){
		this.durations = new ArrayList<Integer>();
	}
	public void addDuration(int duration){
		durations.add(duration);
	}
	public int getAverage(){
		int sum = 0;
		for(int i : durations){
			sum+=i;
		}
		return sum/durations.size();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public ArrayList<Integer> getDurations() {
		return durations;
	}
	public void setDurations(ArrayList<Integer> durations) {
		this.durations = durations;
	}
	@Override
	public String toString() {
		return "Request [id=" + id + ", duration=" + getAverage() + "]";
	}
	
}

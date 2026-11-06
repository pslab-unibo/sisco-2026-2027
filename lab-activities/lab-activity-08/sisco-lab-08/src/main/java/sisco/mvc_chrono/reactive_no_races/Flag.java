package sisco.mvc_chrono.reactive_no_races;

public class Flag {

	private boolean flag;
	
	public synchronized void reset() {
		flag = false;
	}
	
	public synchronized void set() {
		flag = true;
	}
	
	public synchronized boolean isSet() {
		return flag;
	}
}

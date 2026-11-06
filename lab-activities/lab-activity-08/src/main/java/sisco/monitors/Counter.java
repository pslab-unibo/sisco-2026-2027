package sisco.monitors;

public class Counter {

	private int count;

	public Counter(){
		count = 0;
	}

	public synchronized void inc(){
		count++;
	}

	public synchronized int getValue(){
		return count;
	}
}
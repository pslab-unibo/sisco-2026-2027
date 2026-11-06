package sisco.monitors;

public class SynchCell {

	private int value;
	private boolean available;

	public SynchCell(){
		available = false;
	}

	public synchronized void set(int v){
		value = v;
		available = true;
		notifyAll();  
	}

	public synchronized int get() {
		while (!available){
			try {
				wait();
			} catch (InterruptedException ex){}
		}
		return value;
	}
}
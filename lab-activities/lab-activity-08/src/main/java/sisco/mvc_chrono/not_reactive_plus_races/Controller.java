package sisco.mvc_chrono.not_reactive_plus_races;

public class Controller {

	private static final int DELTA_TIME = 1000;
	private volatile boolean stopped;
	private Counter counter;
	
	public Controller(Counter counter) {
		this.counter = counter;
	}
	
	public void notifyStarted() {
		while (!stopped){
			counter.inc();
			System.out.println(counter.getValue());
			try {
				Thread.sleep(DELTA_TIME);
			} catch(Exception ex){
			}
		}
	}
	
	public void notifyStopped() {
		stopped = true;
	}

	public void notifyReset() {
		counter.reset();
	}
}

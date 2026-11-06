package sisco.mvc_chrono.reactive_no_races;

public class CounterAgent extends Thread {

	private Counter counter;
	private Flag stopFlag;
	private long delta;
	
	public CounterAgent(Counter c, Flag stopFlag, long delta){
		counter = c;
		this.stopFlag = stopFlag;
		this.delta = delta;
	}
	public void run(){
		stopFlag.reset();
		while (!stopFlag.isSet()){
			counter.inc();
			System.out.println(counter.getValue());
			try {
				Thread.sleep(delta);
			} catch(Exception ex){
			}
		}
	}
}

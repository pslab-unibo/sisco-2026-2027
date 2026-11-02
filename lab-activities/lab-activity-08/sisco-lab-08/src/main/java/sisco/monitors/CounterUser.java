package sisco.monitors;

public class CounterUser extends Worker {
	
	private Counter counter;
	
	public CounterUser(Counter counter){
		super("Counter user");
		this.counter = counter;
	}
	
	public void run(){
		while (true){
			counter.inc();
		}
	}
}

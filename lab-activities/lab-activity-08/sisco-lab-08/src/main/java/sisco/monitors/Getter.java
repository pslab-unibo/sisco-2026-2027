package sisco.monitors;

public class Getter extends Worker {
	
	private SynchCell cell;
	
	public Getter(SynchCell cell){
		super("getter");
		this.cell = cell;
	}
	
	public void run(){
		log("before getting");
		int value = cell.get();
		log("got value:"+value);
	}
}

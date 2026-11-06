package sisco.monitors;

public class Setter extends Worker {
	
	private SynchCell cell;
	private int value;
	
	public Setter(SynchCell cell, int value){
		super("setter");
		this.cell = cell;
		this.value = value;
	}
	
	public void run(){
		log("before setting");
		cell.set(value);
		log("after setting");
	}
}

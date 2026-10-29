package sisco.deadlock_simplest;

import java.util.Random;

public abstract class BaseAgent extends Thread {
 
	private Random gen;
	
	public BaseAgent(){
		gen = new Random();
	}
	
	protected void waitAbit() {
		try {
			log("going to wait a bit...");			
			Thread.sleep(gen.nextInt(200));
			log("wait done.");			
		} catch (Exception ex){}
	}
	
	protected void log(String msg) {
		System.out.println("[" + this.getName() + "] " + msg);
	}

}

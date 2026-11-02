package sisco.monitors.ex_barrier;

import java.util.Random;

public class Worker extends Thread {

	private Barrier barrier;
	
	public Worker(String name, Barrier barrier) {
		super(name);
		this.barrier = barrier;
	}
	
	public void run() {
		Random gen = new Random(System.nanoTime());
		try {
			waitFor(gen.nextInt(3000));
			log("before");
			barrier.hitAndWaitAll();
			log("after");
		} catch (InterruptedException ex) {
			log("Interrupted!");
		}
	}
	
	private void log(String msg) {
		synchronized(System.out) {
			System.out.println("[ "+getName()+" ] "+msg);
		}
	}
	
	private void waitFor(long ms) throws InterruptedException{
		Thread.sleep(ms);
	}
}

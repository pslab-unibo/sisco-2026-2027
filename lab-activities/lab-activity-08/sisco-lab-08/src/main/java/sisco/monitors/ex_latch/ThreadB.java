package sisco.monitors.ex_latch;

import java.util.Random;

public class ThreadB extends Thread {

	private Latch latch;
	
	public ThreadB(String name, Latch latch) {
		super(name);
		this.latch = latch;
	}
	
	public void run() {
		Random gen = new Random(System.nanoTime());
		try {
			waitFor(gen.nextInt(3000));
			log("done.");
			latch.countDown();
			log("after.");
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

package sisco.executors.deadlock;

import java.util.concurrent.CyclicBarrier;

public class MyTask implements Runnable {
	
	private String name;
	private CyclicBarrier barrier;
	
	public MyTask(String name, CyclicBarrier barrier) {
		this.name = name;
		this.barrier = barrier;
	}
	
	public void run() {
		log("started.");
		jobA();
		
		try {
			barrier.await();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		jobB();
		log("completed.");
	}

	private void jobA() {
		log("job A done.");
	}

	private void jobB() {
		log("job B done.");
	}
	
	private void log(String msg) {
		synchronized(System.out) {
			System.out.println("["+name+"] "+msg);
		}
	}
}

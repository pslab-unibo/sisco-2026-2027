package sisco.monitors.ex_latch;

public class ThreadA extends Thread {

	private Latch latch;
	
	public ThreadA(String name, Latch latch) {
		super(name);
		this.latch = latch;
	}
	
	public void run() {
		try {
			log("waiting opening.");
			latch.await();
			log("opened."); // 
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

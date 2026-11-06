package sisco.virtual_threads;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class MyMonitor {
	private Lock lock;
	
	public MyMonitor() {
		lock = new ReentrantLock();
	}
	public void m() throws Exception {
		try {
			lock.lock();
			System.out.println("[" + Thread.currentThread().getName()+ "] Entered into m ");
			Thread.sleep(5000);
			System.out.println("[" + Thread.currentThread().getName()+ "] Going to exit from m ");
		} finally {
			lock.unlock();
		}
	}
}

public class TestVTwithMonitors {
	
	public static void main(String[] args) throws Exception {

		log("Launching.. " + Thread.currentThread());
		
		MyMonitor mon = new MyMonitor();
		
		for (int i = 0; i <  10; i++) {
			Thread
			.ofVirtual()
			.name("myVirtualThread-"+i)
			.start(() -> {
				log("Hello from " + Thread.currentThread());
				try {
					mon.m();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			});
		}
		
		Thread.sleep(100000);
	}
	
	private static void log(String msg) {
		System.out.println(msg);
	}

}

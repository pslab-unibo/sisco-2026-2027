package sisco.monitors;

public class TestRawWaitNotify {

	public static void main(String[] args) throws Exception  {
		Object obj = new Object();
		
		new Thread(() ->  {
			try {
				Thread.sleep(1000);
			} catch (Exception ex) {}
			System.out.println("notifying...");
			synchronized (obj) {
				obj.notify();
			}
		}).start();
		
		new Thread(() ->  {
			try {
				synchronized (obj) {
					obj.wait();
				}
				System.out.println("unblocked");
			} catch (InterruptedException ex) {
			}
		}).start();

		
	}

}

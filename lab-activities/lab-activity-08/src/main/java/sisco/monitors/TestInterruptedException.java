package sisco.monitors;

public class TestInterruptedException {

	public static void main(String[] args) throws Exception  {
		Object obj = new Object();
		
	
		var th = new Thread(() ->  {
			try {
				synchronized (obj) {
					obj.wait();
				}
				System.out.println("unblocked");
			} catch (InterruptedException ex) {
				System.out.println("interrupted");
			}
		});
		th.start();

		Thread.sleep(1000);

		th.interrupt();
		
	}

}

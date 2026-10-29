package sisco.jpf;

/**
 * Check lost update race using precise race detector
 */
public class TestLostUpdate {

	static class Counter {
		private int count;
		
		public Counter(){
			count = 0;
		}
		
		public void inc(){
			count++;
		}
		
		public int getCount(){
			return count;
		}
	}

	static class MyThread extends Thread {
		private Counter c;
		
		public MyThread(Counter c){
			this.c = c;
		}
		
		public void run(){
			c.inc();
		}		
	}	
	
	public static void main(String[] args) throws Exception {
		
		Counter c = new Counter();
		Thread th0 = new MyThread(c);
		Thread th1 = new MyThread(c);
		th0.start();
		th1.start();
		th0.join();
		th1.join();		
	}
	
}

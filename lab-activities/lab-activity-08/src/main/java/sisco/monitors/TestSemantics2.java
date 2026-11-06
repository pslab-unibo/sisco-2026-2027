package sisco.monitors;

public class TestSemantics2 {

	static class MyMonitor {

		public synchronized void m1() {
			try {
				System.out.println("First thread inside, going to wait in 1 sec");
				try {
					Thread.sleep(1000);
				} catch (Exception ex) {
				}
				System.out.println("First thread inside, going to wait");
				wait();
				System.out.println("First thread unblocked.");
				try {
					Thread.sleep(5000);
				} catch (Exception ex) {
				}
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}

		public synchronized void m2() {
			System.out.println("Second thread inside");
			try {
				Thread.sleep(5000);
			} catch (Exception ex) {
			}
			System.out.println("Second thread inside, going to signal");
			notify();
			System.out.println("Second thread inside signaled.");
		}

		public synchronized void m3() {
			System.out.println("Third thread inside.");
			try {
				Thread.sleep(5000);
			} catch (Exception ex) {
			}
		}
	}

	static class MyThread1 extends Thread {
		private MyMonitor mon;

		public MyThread1(MyMonitor mon) {
			this.mon = mon;
		}

		public void run() {
			log("First thread started.");
			mon.m1();
		}

		private void log(String msg) {
			synchronized (System.out) {
				System.out.println(msg);
			}
		}
	}

	static class MyThread2 extends Thread {
		private MyMonitor mon;

		public MyThread2(MyMonitor mon) {
			this.mon = mon;
		}

		public void run() {
			log("Second thread started.");
			mon.m2();
		}

		private void log(String msg) {
			synchronized (System.out) {
				System.out.println(msg);
			}
		}
	}

	static class MyThread3 extends Thread {
		private MyMonitor mon;

		public MyThread3(MyMonitor mon) {
			this.mon = mon;
		}

		public void run() {
			log("Third thread started.");
			mon.m3();
		}

		private void log(String msg) {
			synchronized (System.out) {
				System.out.println(msg);
			}
		}
	}

	public static void main(String[] args) throws Exception {

		MyMonitor mon = new MyMonitor();
		new MyThread1(mon).start();
		new MyThread3(mon).start();
		new MyThread2(mon).start();

	}
}

package sisco.jpf;

public class TestScenarios {

	static class Worker extends Thread {
		protected void log(String msg) {
			synchronized (System.out) {
				System.out.println(msg);
			}
		}
	}

	static class MyWorkerA extends Worker {
		public void run() {
			log("a1");
			log("a2");
		}
	}

	static class MyWorkerB extends Worker {
		public void run() {
			log("b1");
			log("b2");
		}
	}

	public static void main(String[] args) throws Exception {
		Thread th0 = new MyWorkerA();
		Thread th1 = new MyWorkerB();
		th0.start();
		th1.start();
		th0.join();
		th1.join();
	}

}

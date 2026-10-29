package sisco.cs_withsem;

import java.util.concurrent.Semaphore;

public class TestCSWithSem {

	public static void main(String[] args) {
		
		/* this is a strong semaphore */
		Semaphore mutex = new Semaphore(1,true);
		
		new MyWorkerA("MyAgent-01", mutex).start();		
		new MyWorkerB("MyAgent-02", mutex).start();
		new MyWorkerC("MyAgent-03", mutex).start();
	}

}

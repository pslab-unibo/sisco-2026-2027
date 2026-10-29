package sisco.cs_withsem;

import java.util.concurrent.Semaphore;

public class MyWorkerB extends Worker {
	
	private Semaphore mutex;
	
	public MyWorkerB(String name, Semaphore lock){
		super(name);
		this.mutex = lock;
	}

	public void run(){
		while (true){
		  try {
			  mutex.acquire();
			  action1();	
			  action2();
		  } catch (InterruptedException ex) {
			  log("interrupted.");
		  } finally {
			  mutex.release();
		  }
		  action3();
		}
	}
	
	protected void action1(){
		println("b1");
		wasteRandomTime(0,1000);	
	}
	
	protected void action2(){
		println("b2");
		wasteRandomTime(100,200);	
	}
	protected void action3(){
		println("b3");
		wasteRandomTime(1000,2000);	
	}
}

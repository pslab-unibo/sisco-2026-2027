package sisco.cs_withsem;

import java.util.concurrent.Semaphore;


public class MyWorkerC extends Worker {
	
	private Semaphore mutex;
	
	public MyWorkerC(String name, Semaphore lock){
		super(name);
		this.mutex = lock;
	}

	public void run(){
		while (true){
		  try {
			  mutex.acquire();
			  action1();	
			  action2();
			  action3();
		  } catch (InterruptedException ex) {
			  log("interrupted.");
		  } finally {
			  mutex.release();
		  }
		}
	}
	
	protected void action1(){
		println("c1");
		wasteRandomTime(0,2000);	
	}
	
	protected void action2(){
		println("c2");
		wasteRandomTime(100,200);	
	}
	protected void action3(){
		println("c3");
		wasteRandomTime(1000,2000);	
	}
}

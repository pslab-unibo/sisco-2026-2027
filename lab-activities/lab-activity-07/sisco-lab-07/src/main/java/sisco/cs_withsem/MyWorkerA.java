package sisco.cs_withsem;

import java.util.concurrent.Semaphore;

public class MyWorkerA extends Worker {
	
	private Semaphore mutex;
	
	public MyWorkerA(String name, Semaphore mutex){
		super(name);
		this.mutex = mutex;
	}
	
	public void run(){
		while (true){
		  action1();	
		  try {
			  mutex.acquire();
			  action2();	
			  action3();	
		  } catch (InterruptedException ex) {
			  log("interrupted..");
		  } finally {
			  mutex.release();
		  }
		}
	}
	
	protected void action1(){
		println("a1");
		wasteRandomTime(100,500);	
	}
	
	protected void action2(){
		println("a2");
		wasteRandomTime(300,700);	
	}
	protected void action3(){
		println("a3");
		wasteRandomTime(300,700);	
	}
}


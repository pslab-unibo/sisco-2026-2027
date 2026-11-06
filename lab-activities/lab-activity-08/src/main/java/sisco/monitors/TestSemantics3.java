package sisco.monitors;

import java.util.concurrent.locks.*;

class MyMonitor {
	
	private ReentrantLock mutex;
	private Condition c;
	
	public MyMonitor(){
		mutex = new ReentrantLock();
		c = mutex.newCondition();
	}
	
	public  void m1(){
		try {
			mutex.lock();
			try {
				System.out.println("First thread inside, going to wait");
				c.await();
				System.out.println("First thread unblocked.");
				try {
					Thread.sleep(5000);
				} catch (Exception ex){}
			} catch (InterruptedException ex){
				ex.printStackTrace();
			}
		} finally {
			mutex.unlock();
		}
	}
	
	public  void m2(){
		try {
			mutex.lock();
			System.out.println("Second thread inside");
			try {
				Thread.sleep(5000);
			} catch (Exception ex){}
			System.out.println("Second thread inside, going to signal");
			c.signal();
			System.out.println("Second thread inside signaled.");
		} finally {
			mutex.unlock();
		}
	}

	public  void m3(){
		try {
			mutex.lock();
			System.out.println("Third thread inside.");
			try {
				Thread.sleep(5000);
			} catch (Exception ex){}
		} finally {
			mutex.unlock();
		}
	}
}

class MyThread1 extends Thread {
	private MyMonitor mon;
	
	public MyThread1(MyMonitor mon){
		this.mon = mon;
	}
	
	public void run(){
		log("First thread started.");
		mon.m1();
	}

	private void log(String msg){
		synchronized(System.out){
			System.out.println(msg);
		}
	}
}

class MyThread2 extends Thread {
	private MyMonitor mon;
	
	public MyThread2(MyMonitor mon){
		this.mon = mon;
	}
	
	public void run(){
		log("Second thread started.");
		mon.m2();
	}

	private void log(String msg){
		synchronized(System.out){
			System.out.println(msg);
		}
	}
}

class MyThread3 extends Thread {
	private MyMonitor mon;
	
	public MyThread3(MyMonitor mon){
		this.mon = mon;
	}
	
	public void run(){
		log("Third thread started.");
		mon.m3();
	}

	private void log(String msg){
		synchronized(System.out){
			System.out.println(msg);
		}
	}
}

public class TestSemantics3 {
	public static void main(String[] args) throws Exception {		
		MyMonitor mon = new MyMonitor();
		new MyThread1(mon).start();
		new MyThread2(mon).start();
		new MyThread3(mon).start();
	}
}

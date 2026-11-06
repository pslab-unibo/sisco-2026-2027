package sisco.monitors;

import java.util.*;

class Producer extends Thread {

	private BoundedBuffer<Integer> buffer;
	private Random gen;
	
	public Producer(String name, BoundedBuffer<Integer> buffer){
		super(name);
		gen = new Random();
		this.buffer = buffer;
	}

	public void run(){
		while (true){
			Integer item = produce();
			try {
				buffer.put(item);
				log("produced "+item);
				waitAbit();
			} catch(InterruptedException ex){
				ex.printStackTrace();
			}
		}
	}
	
	private Integer produce(){
		int v = gen.nextInt(100);
		return v;
	}
	
	private void waitAbit() {
		try {
			Thread.sleep(200);
		} catch (Exception ex) {}
	}
	
	private void log(String st){
		synchronized(System.out){
			System.out.println("[ "+this.getName()+" ] "+st);
		}
	}
}

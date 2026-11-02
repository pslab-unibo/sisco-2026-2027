package sisco.monitors;


public class Consumer extends Thread {

	private BoundedBuffer<Integer> buffer;
	
	public Consumer(String name, BoundedBuffer<Integer> buffer){
		super(name);
		this.buffer = buffer;
	}

	public void run(){
		while (true){
			try {
				Integer item = buffer.get();
				consume(item);
			} catch (InterruptedException ex){
				ex.printStackTrace();
			}
		}
	}
	
	private void consume(Integer item){
		log("consumed "+item);
	}
	
	private void log(String st){
		synchronized(System.out){
			System.out.println("[ "+this.getName()+" ] "+st);
		}
	}
}

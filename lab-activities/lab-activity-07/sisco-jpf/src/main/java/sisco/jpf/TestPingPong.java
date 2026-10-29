package sisco.jpf;

import java.util.concurrent.Semaphore;


abstract class ActiveComponent extends Thread {
	
	protected void print(String msg){
		synchronized (System.out){
			System.out.print(msg);
		}
	}

	protected void println(String msg){
		synchronized (System.out){
			System.out.println(msg);
		}
	}

}


class Pinger extends ActiveComponent {

	private Semaphore pongDoneEvent, pingDoneEvent;
	
	public Pinger(Semaphore pongDoneEvent, Semaphore  pingDoneEvent) {
		this.pongDoneEvent = pongDoneEvent;
		this.pingDoneEvent = pingDoneEvent;
	}	
	
	public void run() {
		while (true) {
			try {
				this.pongDoneEvent.acquire();
				println("ping");
				this.pingDoneEvent.release();
			} catch (Exception ex) {};
		}
	}
}

class Ponger extends ActiveComponent {
	
	private Semaphore pingDoneEvent;
	private Semaphore pongDoneEvent;

	public Ponger(Semaphore pingDoneEvent, Semaphore pongDoneEvent) {
		this.pingDoneEvent = pingDoneEvent;
		this.pongDoneEvent = pongDoneEvent;
	}	
	
	public void run() {
		while (true) {
			try {
				pingDoneEvent.acquire();
				println("pong");
				pongDoneEvent.release();
			} catch (Exception ex) {}
				
		}
	}
}

/**
 * Unsynchronized version
 * 
 * @TODO make it sync 
 * @author aricci
 *
 */
public class TestPingPong {
	public static void main(String[] args) {
		
		var pingDoneEvent = new Semaphore(0);
		var pongDoneEvent = new Semaphore(1);
		
		new Pinger(pongDoneEvent, pingDoneEvent).start();
		new Ponger(pingDoneEvent, pongDoneEvent).start();	
		
		// pongDoneEvent.release();
	}

}

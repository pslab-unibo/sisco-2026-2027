package sisco.mvc_async_cmd.controller;

import sisco.mvc_async_cmd.model.Counter;
import sisco.mvc_async_cmd.util.BoundedBuffer;
import sisco.mvc_async_cmd.util.BoundedBufferImpl;

public class ActiveController extends Thread {

	private BoundedBuffer<Cmd> cmdBuffer;
	private Counter counter;
	
	public ActiveController(Counter counter) {
		this.cmdBuffer = new BoundedBufferImpl<Cmd>(100);
		this.counter = counter;
	}
	
	public void run() {
		log("started.");
		while (true) {
			try {
				log("Waiting for cmds ");
				var cmd = cmdBuffer.get(); /* blocking */				
				log("new cmd fetched: " + cmd);
				cmd.execute(counter);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public void notifyNewCmd(Cmd cmd) {
		try {
			cmdBuffer.put(cmd);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private void log(String msg) {
		System.out.println("[ " + System.currentTimeMillis() + "][ Controller ] " + msg);
	}
}

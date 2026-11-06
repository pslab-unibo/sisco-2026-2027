package sisco.compfut;
import java.util.concurrent.CompletableFuture;

public class Test1_basic {

	public static void main(String[] args) {

		CompletableFuture<Void> cf = CompletableFuture.runAsync(() -> {
			log("doing.");
			waitFor(500);
			log("done.");
		});
		
	    log("state: " + cf.isDone());
		waitFor(1000);
	    log("state: " + cf.isDone());

	}
	
	private static void waitFor(long dt) {
		try {
			Thread.sleep(dt);
		} catch (Exception ex) {}
	}
	
	private static void log(String msg) {
		System.out.println("" + Thread.currentThread() + " " + msg);
	}

}

package sisco.compfut;
import java.util.concurrent.CompletableFuture;

public class Test2_accept {

	public static void main(String[] args) {

		CompletableFuture<Void> cf = CompletableFuture.runAsync(() -> {
			log("step 1 doing.");
			waitFor(500);
			log("step 1 done.");
		}).thenAccept((res) -> {
			log("step 2.");
		});

		// keep main thread alive.
		log("going to sleep.");
		waitFor(10000);
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

package sisco.compfut;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Test3_async_apply {

	public static void main(String[] args) {

		CompletableFuture.supplyAsync(() -> {
			log("computing...");
			waitFor(500);
			log("done.");
			return 13;
		}).thenApplyAsync(res -> {
			log("apply to " + res);
			waitFor(1000);
			return res + 1;
		}).thenApplyAsync(res -> {
			log("apply to " + res);
			waitFor(1000);
			return res + 1;
		}).thenAccept(res -> {
			waitFor(1000);
			log("accept " + res);
		});

		// keep main thread alive.
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

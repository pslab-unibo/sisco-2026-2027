package sisco.compfut;
import java.util.concurrent.CompletableFuture;

public class Test4_combine {

	public static void main(String[] args) {

		log("doing req #1...");
		CompletableFuture<String> resultA = CompletableFuture.supplyAsync(() -> {
			log("exec req #1 at "  + System.currentTimeMillis());
		    waitFor(1000);
		    return "alfa";
		});

		log("doing req #2...");
		CompletableFuture<String> resultB = CompletableFuture.supplyAsync(() -> {
			log("exec req #2 at " + System.currentTimeMillis());
		    waitFor(2000);
		    return "beta";
		});

		log("doing combination...");
		CompletableFuture<String> combinedFuture = resultA
		        .thenCombine(resultB, (resA, resB) -> {
			log("exec composition at " + System.currentTimeMillis());
		    return resA + resB;
		});

		try {
			log("The result is: " + combinedFuture.get());	
		} catch (Exception ex) {
			ex.printStackTrace();
		}
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

package sisco.virtual_threads;

import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class TestScalabilityWithExecutor {

	public static void main(String[] args) {
		var t0 = System.currentTimeMillis();
		try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
		    IntStream.range(0, 100_000).forEach(i -> {
		        executor.submit(() -> {
		            Thread.sleep(Duration.ofSeconds(1));
		            return i;
		        });
		    });
		}  // close() called implicitly
		var t1 = System.currentTimeMillis();
		System.out.println("Time elapsed: " + (t1 - t0));
		
	}

}

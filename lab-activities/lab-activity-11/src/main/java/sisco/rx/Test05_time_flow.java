package sisco.rx;

import java.util.concurrent.TimeUnit;
import io.reactivex.rxjava3.core.*;

public class Test05_time_flow {

	public static void main(String[] args) throws Exception {

		long startTime = System.currentTimeMillis(); 
		
		log("Generating.");
		
		Observable
		.interval(100, TimeUnit.MILLISECONDS)	// Long
        .doOnNext(v -> logDeb("1> " + v))
        .timestamp() 							// Timed
        .doOnNext(v -> logDeb("2> " + v))
        .sample(500, TimeUnit.MILLISECONDS)
        .doOnNext(v -> logDeb("3> " + v))
		.map(ts -> " " + (ts.time(TimeUnit.MILLISECONDS) - startTime) + " ms - value: " + ts.value())
        .doOnNext(v -> logDeb("4> " + v))
		.take(5)
        .doOnNext(v -> logDeb("5> " + v))
		.subscribe(Test05_time_flow::log);		
		
		log("Going to sleep.");
		Thread.sleep(10000);
		log("Done.");
		
	}

	static private void log(String msg) {
		System.out.println("[ " + Thread.currentThread().getName() + " ] " + msg);
	}

	static private void logDeb(String msg) {
		System.out.println("[ " + System.currentTimeMillis() + " ][ " + Thread.currentThread().getName() + " ] " + msg);
	}
	
}

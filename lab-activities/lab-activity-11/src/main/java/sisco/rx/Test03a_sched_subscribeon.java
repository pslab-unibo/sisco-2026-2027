package sisco.rx;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Test03a_sched_subscribeon {

	public static void main(String[] args) throws Exception {

		System.out.println("\n=== TEST No schedulers ===\n");
		
		/*
		 * Without using schedulers, by default all the computation 
		 * is done by the calling thread.
		 * 
		 */
		Observable
			.just(100)	
			.map(v -> { log("map 1 " + v); return v * v; })
			.map(v -> { log("map 2 " + v); return v + 1; })
			.subscribe(v -> {						
				log("sub " + v);
			});
		
		System.out.println("\n=== TEST subscribeOn ===\n");

		/* 
		 * subscribeOn:
		 * 
		 * move the computational work of a flow (Observable) on a specified scheduler
		 */
		Observable<Integer> src = Observable
		    .just(100)	
			.map(v -> { log("map 1 " + v); return v * v; })		
			.map(v -> { log("map 2 " + v); return v + 1; });		

		src
			.subscribeOn(Schedulers.computation()) /* run the observable on a worker */	
			.subscribe(v -> {									
				log("sub 1 " + v);
			});

		src
			.subscribeOn(Schedulers.computation()) /* run the observable on another worker */		
			.subscribe(v -> {									
				log("sub 2 " + v);
			});

		Thread.sleep(100);
		
		System.out.println("\n=== TEST parallelism  ===\n");

		/* 
		 * Running independent flows on a different scheduler 
		 * and merging their results back into a single flow 
		 * warning: flatMap => no order in merging
		 */

		Flowable.range(1, 10)
		  .flatMap(v ->
		      Flowable.just(v)
		      	.subscribeOn(Schedulers.computation()) /* each flowable has its own thread */
				.map(w -> { 
					log("map " + w); 
					return w * w; 
				})		
		  )
		  .blockingSubscribe(v -> {
			 log("sub > " + v); 
		  });
		
	}
		
	static private void log(String msg) {
		System.out.println("[" + Thread.currentThread().getName() + "] " + msg);
	}
	
}

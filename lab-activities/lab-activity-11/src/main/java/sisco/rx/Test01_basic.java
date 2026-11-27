package sisco.rx;

import java.util.Arrays;

import io.reactivex.rxjava3.core.*;

public class Test01_basic {

	public static void main(String[] args){
				
		log("creating with just.");
		
	    Observable
	    .just("Hello world")
	    .subscribe(s -> {	    		
	    		log(s);    		
	    });
	    
	    // with inline method
	    
	    Flowable.just("Hello world")
	    	.subscribe(System.out::println);
	    
		// creating a flow (an observable stream) from a static collection
		
	    // simple subscription 
	    
		String[] words = { "Hello", " ", "World", "!" }; 
		
		Flowable.fromArray(words)
			.subscribe((String s) -> {
				log(s);
			});
		
		// full subscription: onNext(), onError(), onCompleted()
		
		log("Full subscription...");
		
		Observable.fromArray(words)
			.subscribe((String s) -> {
				log("> " + s);
			},(Throwable t) -> {
				log("error  " + t);
			},() -> {
				log("completed");
			});
		
		// operators

		log("simple application of operators");
		
		Flowable<Integer> flow = 
		Flowable
			.range(1, 20)
			.map(v -> v * v)
			.filter(v -> v % 3 == 0);
		
		log("first subscription #1");
		flow.subscribe(System.out::println);

		log("first subscription #2");
		flow.subscribe((v) -> {
			log("" + v);
		});

		// doOnNext for debugging...
		
		log("showing the flow...");
		
		Flowable.range(1, 20)
			.doOnNext(v -> log("1> " + v))
			.map(v -> v * v)
			.doOnNext(v -> log("2> " + v))
			.filter(v -> v % 3 == 0)
			.doOnNext(v -> log("3> " + v))
			.subscribe(System.out::println);
						
		
		// simple composition
		
		log("simple composition");
		
		Observable<String> src1 = Observable.fromIterable(Arrays.asList(
				 "the",
				 "quick",
				 "brown",
				 "fox",
				 "jumped",
				 "over",
				 "the",
				 "lazy",
				 "dog"
				));

		Observable<Integer> src2 = Observable.range(1, 5);
		
		src1
			.zipWith(src2, (string, count) -> String.format("%2d. %s", count, string))
			.subscribe(System.out::println);
		
	}
	
	private static void log(String msg) {
		System.out.println("[" + Thread.currentThread().getName() + "] " + msg);
	}
}

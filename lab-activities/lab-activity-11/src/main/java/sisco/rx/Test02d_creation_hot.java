package sisco.rx;

import io.reactivex.rxjava3.core.*;
import io.reactivex.rxjava3.observables.ConnectableObservable;

public class Test02d_creation_hot {

	public static void main(String[] args) throws Exception {

		System.out.println("\n=== TEST Hot streams  ===\n");
		
		Observable<Integer> source = Observable.create(emitter -> {		     
			new Thread(() -> {
				int i = 0;
				while (i < 200){
					try {
						log("source: "+i); 
						emitter.onNext(i);
						Thread.sleep(10);
						i++;
					} catch (Exception ex){}
				}
			}).start();
		     //emitter.setCancellable(c::close);
		 });
		
		ConnectableObservable<Integer> hotObservable = source.publish();
		hotObservable.connect();
	
		/* give time for producing some data before any subscription */
		Thread.sleep(500);
		
		log("Subscribing A.");
		
		hotObservable.subscribe((s) -> {
			log("subscriber A: "+s);
			// Thread.sleep(5000);
		});	
		
		/* give time for producing some data before second subscriber */
		Thread.sleep(500);
		
		log("Subscribing B.");
		
		hotObservable.subscribe((s) -> {
			log("subscriber B: "+s); 
		});	
		
		log("Done.");
		
		Thread.sleep(10_000);

	}
	
	static private void log(String msg) {
		System.out.println("[ " + Thread.currentThread().getName() + "  ] " + msg);
	}
	

}

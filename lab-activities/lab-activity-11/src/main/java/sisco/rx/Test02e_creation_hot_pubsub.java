package sisco.rx;

import io.reactivex.rxjava3.subjects.PublishSubject;

public class Test02e_creation_hot_pubsub {

	public static void main(String[] args) throws Exception {

		System.out.println("\n=== TEST Hot streams with pubsub ===\n");

		/* Subjects: bridges functioning both as observer and observable */ 

		PublishSubject<Integer> source = PublishSubject.<Integer>create();
		 
		log("subscribing.");

		source.subscribe((s) -> {
			log("subscriber A: "+s); 
		}, Throwable::printStackTrace);
		 
		log("generating.");

		new Thread(() -> {
				int i = 0;
				while (i < 100){
					try {
						log("source: "+i); 
						source.onNext(i);
						Thread.sleep(10);
						i++;
					} catch (Exception ex){}
				}
			}).start();
		

		log("waiting.");

		Thread.sleep(100);
		
		source.subscribe((s) -> {
			log("subscriber B: "+s); 
		}, Throwable::printStackTrace);

	}
	
	static private void log(String msg) {
		System.out.println("[ " + Thread.currentThread().getName() + "  ] " + msg);
	}
	

}

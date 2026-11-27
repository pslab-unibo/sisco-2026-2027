package sisco.rx;

import io.reactivex.rxjava3.core.*;
import io.reactivex.rxjava3.flowables.ConnectableFlowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Test06b_backpressure_strategy_buffer {

	public static void main(String[] args) throws Exception {

		System.out.println("\n=== TEST backpressure | strategy BUFFER ===\n");

		/* generator with period 5 ms - strategy BUFFER | specifying size with onBackpressureBuffer*/
		
		Flowable<Long> source = genHotStream(5);

		log("subscribing.");

		/* with buffer size = 5_000, it generates a MissingBackpressureException 
		 * after ~8000 emits (it depends on the local config) */
		
		source
			.onBackpressureBuffer(5_000, () -> {
				log("HELP!");
			})
			.observeOn(Schedulers.computation())
			.subscribe(v -> {
				log("consuming " + v);
				Thread.sleep(100);
			}, error -> {
				log("ERROR: " + error);
			});

		Thread.sleep(1000);
	}

	private static Flowable<Long> genHotStream(int delay) {
		Flowable<Long> source = Flowable.create(emitter -> {
			new Thread(() -> {
				long i = 0;
				try {
					while (true) {
						if (i % 1000 == 0) {
							log("emitting: " + i);
						}
						emitter.onNext(i);
						i++;
						Thread.sleep(delay);
					}
				} catch (Exception ex) {
					ex.printStackTrace();
					log("exit");
				}
			}).start();
		}, BackpressureStrategy.BUFFER);

		ConnectableFlowable<Long> hotObservable = source.publish();
		hotObservable.connect();
		return hotObservable;
	}

	static private void log(String msg) {
		System.out.println("[" + Thread.currentThread().getName() + " ] " + msg);
	}

}

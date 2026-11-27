package sisco.rx;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.*;
import io.reactivex.rxjava3.flowables.ConnectableFlowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Test06d_backpressure_strategy_throttle {

	public static void main(String[] args) throws Exception {

		System.out.println("\n=== TEST backpressure | strategy BUFFER ===\n");

		/* generator with period 5ms - backpressure strategy set: DROPPING LATEST */
		Flowable<Long> source = genHotStream(5);

		log("subscribing.");

		/* using throttleLast to reduce the flow */
		 
		source
		.throttleLast(100, TimeUnit.MILLISECONDS)	// emits only the last item in 100 ms 
		.onBackpressureDrop(v  -> {
			log("DROPPING: " + v);
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
		}, BackpressureStrategy.LATEST);

		ConnectableFlowable<Long> hotObservable = source.publish();
		hotObservable.connect();
		return hotObservable;
	}

	static private void log(String msg) {
		System.out.println("[" + Thread.currentThread().getName() + " ] " + msg);
	}

}

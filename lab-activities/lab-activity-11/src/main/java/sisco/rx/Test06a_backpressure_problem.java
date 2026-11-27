package sisco.rx;

import io.reactivex.rxjava3.core.*;
import io.reactivex.rxjava3.flowables.ConnectableFlowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Test06a_backpressure_problem {

	public static void main(String[] args) throws Exception {

		System.out.println("\n=== TEST backpressure ===\n");

		/* generator with period 5 ms */
		Flowable<Long> source = genHotStream(5);

		log("subscribing.");

		/* generating a MissingBackpressureException after ~7000 emits (it depends on the local config) */

		source
		.observeOn(Schedulers.computation())
		.subscribe(v -> {
			log("consuming " + v);
			Thread.sleep(100);				// <------ creating a delay in consuming
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
						Thread.sleep(0, delay);
					}
				} catch (Exception ex) {
					ex.printStackTrace();
					log("exit");
				}
			}).start();
		}, BackpressureStrategy.ERROR);

		ConnectableFlowable<Long> hotObservable = source.publish();
		hotObservable.connect();
		return hotObservable;
	}

	static private void log(String msg) {
		System.out.println("[" + Thread.currentThread().getName() + " ] " + msg);
	}

}

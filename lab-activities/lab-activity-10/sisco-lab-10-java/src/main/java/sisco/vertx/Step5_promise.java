package sisco.vertx;

import io.vertx.core.*;


/**
 * 
 * Using promises (i.e. the inner side of Vert.x futures) 
 * 
 */
class VerticleWithPromise extends VerticleBase {
	
	public Future<?> start() throws Exception {
		log("started.");	
		var fut = this.getDelayedRandom(1000);
		fut.onComplete((res) -> {
			System.out.println("Result: " + res.result());	
		});
		return  super.start();
	}

	/**
	 * 
	 * Implementing an async method using promises.
	 * 
	 * The method returns a random value after 
	 * some specified time (delay)
	 * 
	 * @param delay
	 * @return
	 */
	protected Future<Double> getDelayedRandom(int delay){
		Promise<Double> promise = Promise.promise();
		this.vertx.setTimer(delay, (res) -> {
			var num = Math.random();
			promise.complete(num);
		});
		return promise.future();
	}
	
	private void log(String msg) {
		System.out.println("[ " + System.currentTimeMillis() + " ][ " + Thread.currentThread() + " ] " + msg);
	}
}

public class Step5_promise {
	public static void main(String[] args) {
		
		Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(new VerticleWithPromise());
		
	}
}


package sisco.vertx;

import io.vertx.core.*;

/**
 * 
 * Making a (short-term) blocking/sync call/task asynchronously
 * by using executeBlocking
 * 
 */
class TestExecBlocking extends VerticleBase {

	// private int x = 0;
	
	public Future<?> start() throws Exception {
		log("before");

		Future<Integer> res = this.vertx.executeBlocking(() -> {
			// Call some blocking API that takes a significant amount of time to return
			log("blocking computation started");
			try {
				Thread.sleep(5000);				
				/* notify promise completion */
				return 100;
			} catch (Exception ex) {
				
				/* notify failure */
				throw new Exception("exception");
			}
		});

		log("after triggering a blocking computation...");
		// x++;

		res.onComplete((AsyncResult<Integer> r) -> {
			log("result: " + r.result());
		});
		
		res.onSuccess((flatResult) -> {
			log("result: " + flatResult);

		});
		return super.start();
	}

	private void log(String msg) {
		System.out.println("[ " + System.currentTimeMillis() + " ][ " + Thread.currentThread() + " ] " + msg);
	}
}

public class Step6_withblocking_simple {

	public static void main(String[] args) {
		Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(new TestExecBlocking());
	}
}

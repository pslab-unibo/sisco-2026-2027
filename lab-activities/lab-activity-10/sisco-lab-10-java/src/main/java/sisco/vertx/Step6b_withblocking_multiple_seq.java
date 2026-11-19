package sisco.vertx;

import io.vertx.core.*;

/**
 * 
 * Making multiple (short-term) blocking calls/tasks
 * using a Worker pool thread with default size.
 * 
 * The default size is 1 (only 1 background worker),
 * so all blocking calls are executed sequentially. 
 *  
 */
class TestExecBlocking2 extends VerticleBase {

	// private int x = 0;
	
	public Future<?> start() throws Exception {
		log("before");

		Future<Integer> f1 = this.vertx.executeBlocking(() -> {
			// Call some blocking API that takes a significant amount of time to return
			log("blocking computation #1 started");
			try {
				Thread.sleep(5000);				
				/* notify promise completion */
				log("blocking computation #1 completed");
				return 100;
			} catch (Exception ex) {
				
				/* notify failure */
				throw new Exception("exception");
			}
		});

		Future<Integer> f2 = this.vertx.executeBlocking(() -> {
			// Call some blocking API that takes a significant amount of time to return
			log("blocking computation #2 started");
			try {
				Thread.sleep(5000);				
				/* notify promise completion */
				log("blocking computation #2 completed");
				return 100;
			} catch (Exception ex) {
				
				/* notify failure */
				throw new Exception("exception");
			}
		});
		
		log("after triggering the blocking computations...");
		// x++;

		Future
		.all(f1,f2)
		.onComplete(r -> {
			log("results: ");
			for (var res: r.result().list()){
				log("- " + res);
			}
		});
		
		return super.start();
	}

	private void log(String msg) {
		System.out.println("[ " + System.currentTimeMillis() + " ][ " + Thread.currentThread() + " ] " + msg);
	}
}

public class Step6b_withblocking_multiple_seq {

	public static void main(String[] args) {
		Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(new TestExecBlocking2());
	}
}

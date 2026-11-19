package sisco.vertx;

import java.util.concurrent.Callable;

import io.vertx.core.*;

/**
 * 
 * Making long-term blocking calls/tasks
 * by exploiting custom threads + promises
 *  
 */
class TestExecBlocking4 extends VerticleBase {

	
	public Future<?> start() throws Exception {
		log("before");

		Future<Integer> f1 = this.executeBlockingLongTermTask(() -> {
			// Call some blocking API that takes a significant amount of time to return
			log("blocking computation #1 started");
			try {
				Thread.sleep(10000);				
				/* notify promise completion */
				log("blocking computation #1 completed");
				return 200;
			} catch (Exception ex) {
				
				/* notify failure */
				throw new Exception("exception");
			}
		});

		Future<Integer> f2 = this.executeBlockingLongTermTask(() -> {
			// Call some blocking API that takes a significant amount of time to return
			log("blocking computation #2 started");
			try {
				Thread.sleep(13000);				
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
			for (var res: r.result().list()){
				log("result: " + res);
			}
		});
		
		return super.start();
	}

	/**
	 * 
	 * Custom method to manage any long-term computation 
	 *  
	 * @param task
	 * @return
	 */
	Future<Integer> executeBlockingLongTermTask(Callable<Integer> task){
		Promise<Integer> prom = Promise.promise();
		new Thread(() -> {
			try {
				var res = task.call();
				prom.complete(res);
			} catch (Exception ex) {
				prom.fail(ex.getMessage());
			}
			
		}).start();
	 return prom.future();
	}
	
	private void log(String msg) {
		System.out.println("[ " + System.currentTimeMillis() + " ][ " + Thread.currentThread() + " ] " + msg);
	}
}

public class Step6d_withblocking_custom {

	public static void main(String[] args) {
		Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(new TestExecBlocking4());
	}
}

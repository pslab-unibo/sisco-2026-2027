package sisco.vertx;

import io.vertx.core.Future;
import io.vertx.core.VerticleBase;
import io.vertx.core.Vertx;

class SimpleServer extends VerticleBase {
	
	private int numRequests;
	private int port;

	public SimpleServer(int port) {
		numRequests = 0;
		this.port = port;
	}
	
	public Future<?> start() throws Exception {
		return vertx
		.createHttpServer()
		.requestHandler(req -> {
			numRequests++;
			
			String fileName = req.path().substring(1);
			log("request " + numRequests + " arrived for file: " + fileName);

			vertx
			.fileSystem()
			.readFile(fileName)
			.onComplete(result -> {
				log("result ready");
				if (result.succeeded()) {
					log(result.result().toString());
					req.response().putHeader("content-type", "text/plain").end(result.result().toString());
				} else {
					log("Oh oh ..." + result.cause());
					req.response().putHeader("content-type", "text/plain").end("File not found");
				}
			});
			
		}).listen(port);
	}

	private  void log(String msg) {
		System.out.println("" + Thread.currentThread() + " " + msg);
	}

}

public class Step8_SimpleServer {
	static final int PORT = 8081;

	public static void main(String[] args) {
		Vertx vertx = Vertx.vertx();
		SimpleServer myVerticle = new SimpleServer(PORT);
		vertx
		.deployVerticle(myVerticle)
		.onSuccess(res -> {
			log("Server listening on port " + PORT);
		});
	}
	
	static private void log(String msg) {
		System.out.println("" + Thread.currentThread() + " " + msg);
	}
}

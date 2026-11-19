package sisco.vertx;

import io.vertx.core.*;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.file.FileSystem;

/**
 * Composing async calls by means of composite futures
 * 
 */
class TestCompo extends VerticleBase{
	
	public Future<?> start() throws Exception {
		FileSystem fs = vertx.fileSystem();    		
		
		Future<Buffer> f1 = fs.readFile("hello.md");
		Future<Buffer> f2 = fs.readFile("POM.xml");
				
		Future
		.all(f1,f2)
		.onSuccess((CompositeFuture res) -> {
			log("COMPOSITE => \n"+res.result().list());			
		}); 
		return super.start();
	}

	private void log(String msg) {
		System.out.println("[ " + System.currentTimeMillis() + " ][ " + Thread.currentThread() + " ] " + msg);
	}
}

public class Step4_compo {

	public static void main(String[] args) {
		Vertx  vertx = Vertx.vertx();
		vertx.deployVerticle(new TestCompo());
	}
}


	package sisco.vertx;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.file.FileSystem;
import java.io.*;

public class Step1_basic {

	public static void main(String[] args) {
		
		System.out.println(new File(".").getAbsoluteFile());
		
		Vertx  vertx = Vertx.vertx();

		FileSystem fs = vertx.fileSystem();    		

		log("doing the async call... ");
		
		Future<Buffer> fut = fs.readFile("hello.md");
		
		fut.onComplete((AsyncResult<Buffer> res) -> {	
			log("hello.md content: \n" + res.result().toString());
		});

		log("async call done. Waiting some time... ");

		try {
			Thread.sleep(1000);
		} catch (Exception ex) {}
		
		log("done");
	}
	
	private static void log(String msg) {
		System.out.println("[ " + System.currentTimeMillis() + " ][ " + Thread.currentThread() + " ] " + msg);
	}
}


package sisco.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.VerticleBase;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.*;
import io.vertx.ext.web.handler.BodyHandler;

class WebService extends VerticleBase {

	private int numRequests;
	private int port;

	public WebService(int port) {
		numRequests = 0;
		this.port = port;
	}
	
	public Future<?> start() throws Exception  {
		log("Service initializing...");
		HttpServer server = vertx.createHttpServer();
		Router router = Router.router(vertx);

		router.route().handler(BodyHandler.create());

		router
		.get("/api/numRequests")
		.respond(ctx -> {	
			log("new request arrived: " + ctx.currentRoute().getPath());
			JsonObject reply = new JsonObject();
			reply.put("numRequests", numRequests);
			return ctx
				.response()
			    .putHeader("Content-Type", "application/json")
			    .end(reply.toString());
		});

		router
		.get("/api/things/:thingId/state")
		.handler( ctx -> {
			log("new request arrived: " + ctx.currentRoute().getPath());

			JsonObject reply = new JsonObject();
			reply
			.put("id", ctx.pathParam("thingId"))
			.put("state", Math.random());			
			
			ctx
			.response()
		    .putHeader("Content-Type", "application/json")
		    .end(reply.toString());
			
			// sendReply(request, reply);
		});
		
		router.route().handler(BodyHandler.create());
		
		router
		.route(HttpMethod.POST, "/api/task/inc")
		.handler(ctx -> {
			log("new request arrived: " + ctx.currentRoute().getPath());

			JsonObject msgReq = ctx.body().asJsonObject();
			double value = msgReq.getDouble("value");
			double result = value + 1;			
			JsonObject reply = new JsonObject();
			numRequests++;
			
			reply
			.put("numReq", numRequests)
			.put("result", result);				
			log("reply: " + reply.encodePrettily());

			ctx
			.response()
		    .putHeader("Content-Type", "application/json")
		    .end(reply.toString());
		  });

		return server
				.requestHandler(router)
				.listen(port);
	}

	private void sendReply(RoutingContext request, JsonObject reply) {
		HttpServerResponse response = request.response();
		response.putHeader("content-type", "application/json");
		response.end(reply.toString());
	}
	
	private  void log(String msg) {
		System.out.println("" + Thread.currentThread() + " " + msg);
	}
}

public class Step9_WebService {
	static final int PORT = 8081;
	public static void main(String[] args){
		Vertx vertx = Vertx.vertx();
		var myVerticle = new WebService(PORT);
		vertx
		.deployVerticle(myVerticle)
		.onSuccess(res -> {
			log("Service listening on port " + PORT);
		});
		
	}
	static private void log(String msg) {
		System.out.println("" + Thread.currentThread() + " " + msg);
	}
}

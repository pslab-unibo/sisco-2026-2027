package sisco.deadlock_simplest;


public class Worker extends BaseAgent {
 
	private Resource resA;
	private Resource resB;
	
	public Worker(Resource resA, Resource resB){
		this.resA = resA;
		this.resB = resB;
	}
	
	public void run(){
		while (true){
			waitAbit();
			synchronized (resA) {
				waitAbit();
				synchronized (resB) {
					resA.use();
					resB.use();
					waitAbit();
				}
			}
		}
	}	
}

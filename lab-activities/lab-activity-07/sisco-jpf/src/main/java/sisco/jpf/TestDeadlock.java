package sisco.jpf;

class Resource {
	
	private String name;
	
	public Resource(String name) {
		this.name = name;
	}
	
	public void use(){
		System.out.println("using res: " + name);
	}
	
}
class Worker extends Thread {
	 
	private Resource resA;
	private Resource resB;
	
	public Worker(Resource resA, Resource resB){
		this.resA = resA;
		this.resB = resB;
	}
	
	public void run(){
		while (true){
			synchronized (resA) {
				resA.use();
				synchronized (resB) {
					resA.use();
				}
			}
		}
	}	
}

public class TestDeadlock {
	public static void main(String[] args) {
		Resource resAlfa = new Resource("Resource-ALFA");
		Resource resBeta = new Resource("Resource-BETA");
		new Worker(resAlfa, resBeta).start();
		new Worker(resBeta, resAlfa).start();
	}

}

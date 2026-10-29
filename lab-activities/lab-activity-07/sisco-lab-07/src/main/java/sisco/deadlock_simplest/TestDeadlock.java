package sisco.deadlock_simplest;

/**
 * Deadlock example 
 * 
 * @author aricci
 *
 */
public class TestDeadlock {
	public static void main(String[] args) {
		Resource resAlfa = new Resource("Resource-ALFA");
		Resource resBeta = new Resource("Resource-BETA");
		new Worker(resAlfa, resBeta).start();
		new Worker(resBeta, resAlfa).start();
		// new Worker(resAlfa, resBeta).start();
	}

}

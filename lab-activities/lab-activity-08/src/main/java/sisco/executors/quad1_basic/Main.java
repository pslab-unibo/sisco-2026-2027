package sisco.executors.quad1_basic;

public class Main {

	public static void main(String args[]) throws Exception {	

		double a = 0;
		double b = 3;		
		int nTasks = 100;
		int poolSize = Runtime.getRuntime().availableProcessors() + 1;
		
		var service = new QuadratureService(nTasks, poolSize);
		double result = service.compute((double x) -> { return Math.sin(x); }, a, b);
		System.out.println("Result: "+result);
		
		System.exit(0);
	}
	
}

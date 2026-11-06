package sisco.executors.quad3_withfutures;

public class Main {

	public static void main(String args[]) throws Exception {	

		double a = 0;
		double b = 3;		
		int nTasks = 1000;
		int poolSize = 5;
		
		var service = new QuadratureService(nTasks, poolSize);
		double result = service.compute((double x) -> { return Math.sin(x); }, a, b);
		System.out.println("Result: "+result);
		
		System.exit(0);
	}
	
}

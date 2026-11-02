package sisco.executors.quad2_withsynch;

public class Main {

	public static void main(String args[]) throws Exception {	

		double a = 0;
		double b = 3;		
		int nTasks = 10;
		int poolSize = Runtime.getRuntime().availableProcessors() + 1;
		
		QuadratureService service = new QuadratureService(nTasks, poolSize);
		double result = service.compute((double x) -> { return Math.sin(x); }, a, b);
		System.out.println("Result: "+result);
		
		System.exit(0);
	}
	
}

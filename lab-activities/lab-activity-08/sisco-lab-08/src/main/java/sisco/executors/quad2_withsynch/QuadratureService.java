package sisco.executors.quad2_withsynch;

import java.util.concurrent.*;

public class QuadratureService  {

	private int numTasks;
	private ExecutorService executor;
	
	public QuadratureService (int numTasks, int poolSize){		
		this.numTasks = numTasks;
		executor = Executors.newFixedThreadPool(poolSize);
	}
	
	public double compute(IFunction mf, double a, double b) throws InterruptedException { 

		QuadratureResult result = new QuadratureResult(numTasks);		
		double x0 = a;
		double step = (b-a)/numTasks;		
		for (int i = 0; i < numTasks; i++) {
			try {
				executor.execute(new ComputeAreaTask(x0, x0 + step, mf, result));
				log("submitted task " + x0 + " " + (x0+step));
				x0 += step;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}				
		double res = result.getResult();
		return res;
	}
	
	
	private void log(String msg){
		System.out.println("[SERVICE] "+msg);
	}
}

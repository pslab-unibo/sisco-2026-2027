package sisco.executors.quad3_withfutures;

import java.util.concurrent.Callable;


public class ComputeAreaTask implements Callable<Double>  {

	private IFunction mf;
	private double a, b;

	public ComputeAreaTask(double a, double b, IFunction mf) {
		this.mf = mf;
		this.a = a;
		this.b = b;
	}

	public Double call() {
		log("executing task " + a + " " + b);
		double sum = 0;
		double step = (b - a) / 1000;
		double x = a;
		for (int i = 0; i < 1000; i++) {
			sum += step * mf.eval(x);
			x += step;
		}
		log("Computed result " + a + " " + b + " " + sum);
		return sum;
	}

	private void log(String msg) {
		System.out.println(msg);
	}
}

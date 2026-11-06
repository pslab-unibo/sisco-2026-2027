package sisco.executors.quad1_basic;

public class QuadratureResult {
	
	private double sum; 
	
	public QuadratureResult(){
		sum = 0;
	}
	
	public synchronized void add(double value){
		sum += value;
	}

	public synchronized double getResult(){
		return sum;
	}
}

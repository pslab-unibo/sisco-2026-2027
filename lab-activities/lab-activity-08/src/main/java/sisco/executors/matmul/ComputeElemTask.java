package sisco.executors.matmul;

public class ComputeElemTask implements Runnable {
	
	private Mat a,b,c;
	private int i, j;
	
	public ComputeElemTask(int i, int j, Mat a, Mat b, Mat c){
		this.i = i;
		this.j = j;
		this.a = a;
		this.b = b;
		this.c = c;
	}
	
	public void run() {
		log("computing ("+i+","+j+")...");
		double sum = 0;
		for (int k = 0; k < a.getNColumns(); k++){
			sum += a.get(i, k)*b.get(k, j);
		}
		c.set(i,j,sum);
		log("computing ("+i+","+j+") done: "+sum);
	}
	
	private void log(String msg){
		// System.out.println("[TASK] "+msg);
	}

}

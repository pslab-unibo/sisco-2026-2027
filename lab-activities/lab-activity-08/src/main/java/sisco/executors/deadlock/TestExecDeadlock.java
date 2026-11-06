package sisco.executors.deadlock;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TestExecDeadlock {

	public static void main(String[] args) throws Exception {

		int nTasks = 100; 
		int nThreads = Runtime.getRuntime().availableProcessors() + 1;
		
		ExecutorService exec = Executors.newFixedThreadPool(nThreads);
		CyclicBarrier barrier = new CyclicBarrier(nTasks);
				
		for (int i = 0; i < nTasks; i++) {
			exec.execute(new MyTask("task-" + i, barrier));
		}		
		
		exec.shutdown();
		exec.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);		
	}
}



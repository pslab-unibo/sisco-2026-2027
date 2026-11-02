package sisco.monitors.ex_barrier;

import java.util.ArrayList;
import java.util.List;

public class TestBarrier {

	public static void main(String[] args) {
		
		int nWorkers = 10;
		
		/* this barrier is not working */
		Barrier barrier = new FakeBarrier(nWorkers);
		
		List<Worker> workers = new ArrayList<Worker>();
		for (int i = 0; i < nWorkers; i++) {
			workers.add(new Worker("Worker-"+i, barrier));
		}

		for (Worker w: workers) {
			w.start();
		}
		
	}
}

package sisco.deadlock_obs;

import java.util.ArrayList;
import java.util.List;

class MyObserver implements Observer {

	List<Observed> obsList;

	public MyObserver(){
		obsList = new ArrayList<Observed>();
	}
	
	public synchronized void observe(Observed obj){
		obsList.add(obj);
		obj.register(this);
	}
	
	public synchronized void notifyStateChanged(Observed obs) {
		synchronized(System.out){
			System.out.println("[" + System.currentTimeMillis() + "] state changed: "+obs.getState());
		}
	}

	public synchronized int getOverallState() {
		int sum = 0;
		for (Observed o: obsList){
			sum += o.getState();
		}
		return sum;
	}
}

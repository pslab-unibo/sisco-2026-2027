package sisco.deadlock_obs;

import java.util.ArrayList;
import java.util.List;

public class MyObservedEntity implements Observed {

	private List<Observer> obsList;
	private int state;

	public MyObservedEntity(){
		obsList = new ArrayList<Observer>();
	}

	public synchronized void register(Observer obs) {
		obsList.add(obs);
	}

	public synchronized int getState() {
		return state;
	}

	public synchronized void changeState1() {
		state++;
		for (Observer o: obsList){
			o.notifyStateChanged(this);
		}
	}

	public synchronized void changeState2() {
		state--;
		for (Observer o: obsList){
			o.notifyStateChanged(this);
		}
	}
}

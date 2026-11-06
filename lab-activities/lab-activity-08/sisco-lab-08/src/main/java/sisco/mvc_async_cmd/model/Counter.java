package sisco.mvc_async_cmd.model;

import java.util.ArrayList;
import java.util.List;

public class Counter {

	private int initialCont;
	private int cont;
	private List<CounterObserver> observers;
	
	public Counter(int cont) {
		this.initialCont = cont;
		this.cont = cont;
		observers = new ArrayList<>();
	}
	
	public synchronized void addObserver(CounterObserver o) {
		observers.add(o);
	}
	
	public synchronized void inc() {
		cont++;
		notifyObservers();
	}

	public synchronized void dec() {
		cont--;
		notifyObservers();
	}

	public synchronized void reset() {
		cont = initialCont;
		notifyObservers();
	}
	
	public synchronized int getCount() {
		return cont;
	}
	
	private void notifyObservers() {
		for (var o: observers) {
			o.modelUpdated(this);
		}
	}
}

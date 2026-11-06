package sisco.mvc_async_cmd.view;


public class ViewModel {

	private int value;
	
	public ViewModel(int initialValue) {
		this.value = initialValue;
	}
	
	public synchronized void update(int newValue) {
		this.value = newValue;
	}
	
	public synchronized int getCurrent() {
		return value;
	}

}

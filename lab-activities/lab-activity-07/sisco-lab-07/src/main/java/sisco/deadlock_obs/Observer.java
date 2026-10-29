package sisco.deadlock_obs;

public interface Observer {
	void notifyStateChanged(Observed obs);
}

package sisco.deadlock_obs;

public class TestObsPatternDeadlock {
	public static void main(String[] args) {
		
		MyObservedEntity objA = new MyObservedEntity();
		MyObserver objB = new MyObserver();
		objB.observe(objA);
		
		new AgentOne(objA).start();
		new AgentTwo(objB).start();

	}
}

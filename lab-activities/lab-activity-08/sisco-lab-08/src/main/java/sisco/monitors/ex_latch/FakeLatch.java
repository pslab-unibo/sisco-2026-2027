package sisco.monitors.ex_latch;

/*
 * Latch - to be implemented
 */
public class FakeLatch implements Latch {
	
	public FakeLatch() {}
	
	@Override
	public void await() throws InterruptedException {}

	@Override
	public void countDown() {}

	
}

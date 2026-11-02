package sisco.monitors.ex_latch;

/**
 * Latch behaviour
 * 
 * One agent waits until N agents did they job ("arrives to a gate") 
 * 
 * - The waiting agent calls await() and blocks until N agents called countDown(),
 * - the N agents do not block
 * 
 */
public interface Latch {

	void countDown();
	
	void await() throws InterruptedException;
}

package sisco.monitors;

import java.util.LinkedList;
import java.util.concurrent.locks.*;

/**
 * 
 * Simple implementation of a bounded buffer
 * as a monitor, using library support
 * 
 * @param <Item>
 */
public class BoundedBufferImplWithLib<Item> implements BoundedBuffer<Item> {

	private LinkedList<Item> buffer;
	private int maxSize;
	private Lock mutex;
	private Condition notEmpty, notFull;

	public BoundedBufferImplWithLib(int size) {
		buffer = new LinkedList<Item>();
		maxSize = size;
		mutex = new ReentrantLock();
		notEmpty = mutex.newCondition();
		notFull = mutex.newCondition();
	}

	public void put(Item item) throws InterruptedException {
		try {
			mutex.lock();
			while (isFull()) {
				notFull.await();
			}
			buffer.addLast(item);
			notEmpty.signal();
		} finally {
			mutex.unlock();
		}
	}

	public Item get() throws InterruptedException {
		try {
			mutex.lock();
			while (isEmpty()) {
				notEmpty.await();
			}
			Item item = buffer.removeFirst();
			notFull.signal();
			return item;
		} finally {
			mutex.unlock();
		}
	}

	private boolean isFull() {
		return buffer.size() == maxSize;
	}

	private boolean isEmpty() {
		return buffer.size() == 0;
	}
}

package sisco.monitors;

import java.util.LinkedList;

/**
 * 
 * Simple implementation of a bounded buffer
 * as a monitor, using raw mechanisms
 * 
 * @param <Item>
 */
public class BoundedBufferImplRaw<Item> implements BoundedBuffer<Item> {

	private LinkedList<Item> buffer;
	private int maxSize;

	public BoundedBufferImplRaw(int size) {
		buffer = new LinkedList<Item>();
		maxSize = size;
	}

	public synchronized void put(Item item) throws InterruptedException {
		while (isFull()) {
			wait();
		}
		buffer.addLast(item);
		notifyAll();
	}

	public synchronized Item get() throws InterruptedException {
		while (isEmpty()) {
			wait();
		}
		Item item = buffer.removeFirst();
		notifyAll();
		return item;
	}

	private boolean isFull() {
		return buffer.size() == maxSize;
	}

	private boolean isEmpty() {
		return buffer.size() == 0;
	}
}

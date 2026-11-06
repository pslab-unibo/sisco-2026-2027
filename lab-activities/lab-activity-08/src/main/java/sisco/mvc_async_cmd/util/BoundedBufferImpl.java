package sisco.mvc_async_cmd.util;

import java.util.LinkedList;
import java.util.Optional;

/**
 * 
 * Simple implementation of a bounded buffer
 * as a monitor, using raw mechanisms
 * 
 * @param <Item>
 */
public class BoundedBufferImpl<Item> implements BoundedBuffer<Item> {

	private LinkedList<Item> buffer;
	private int maxSize;

	public BoundedBufferImpl(int size) {
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

	public synchronized Optional<Item> poll() {
		if (isEmpty()) {
			return Optional.empty();
		} else {
			return Optional.of(buffer.removeFirst());
		}
	}
	
	private boolean isFull() {
		return buffer.size() == maxSize;
	}

	private boolean isEmpty() {
		return buffer.size() == 0;
	}
}

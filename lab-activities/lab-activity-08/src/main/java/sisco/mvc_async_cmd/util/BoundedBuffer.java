package sisco.mvc_async_cmd.util;

import java.util.Optional;

public interface BoundedBuffer<Item> {

    void put(Item item) throws InterruptedException;
    
    Item get() throws InterruptedException;

    /* non blocking */
	Optional<Item> poll();

}

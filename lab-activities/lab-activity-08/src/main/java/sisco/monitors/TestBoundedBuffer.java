package sisco.monitors;

public class TestBoundedBuffer {

	public static void main(String[] args){
		
		//BoundedBuffer<Integer> buffer = new BoundedBufferImplRaw<Integer>(4);
		BoundedBuffer<Integer> buffer = new BoundedBufferImplWithLib<Integer>(4);

		int nProducers = 3;
		int nConsumers = 5;

		for (int i = 0; i < nProducers; i++){
			new Producer("producer-" + i, buffer).start();
		}

		for (int i = 0; i < nConsumers; i++){
			new Consumer("consumer-" + i, buffer).start();
		}
	}	
}

package sisco.jpf;

import gov.nasa.jpf.vm.Verify;

public class TestSequentialWithRand {

	public static void main(String[] args) throws Exception {
		int simulatedRandom = Verify.random(20);
		System.out.println("input: " + simulatedRandom);
	}

}

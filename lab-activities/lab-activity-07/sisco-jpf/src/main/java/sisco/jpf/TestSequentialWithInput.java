package sisco.jpf;

import gov.nasa.jpf.vm.Verify;

public class TestSequentialWithInput {

	public static void main(String[] args) throws Exception {
		int simulatedInput = Verify.getInt(0,10);
		System.out.println("input: " + simulatedInput);
	}

}

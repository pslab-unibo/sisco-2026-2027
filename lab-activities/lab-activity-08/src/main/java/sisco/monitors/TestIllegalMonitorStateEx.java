package sisco.monitors;

public class TestIllegalMonitorStateEx {

	public static void main(String[] args)  {

		Object lock = new Object();
        
		try {
			// synchronized (lock){
		        lock.wait();
			// }
		} catch (Exception ex){
			ex.printStackTrace();
		}
	}

}

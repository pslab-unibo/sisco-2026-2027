package sisco.monitors;

public class TestSynchCell {
		
	public static void main(String args[]){
		
		SynchCell cell = new SynchCell();
		new Getter(cell).start();
		new Getter(cell).start();
		new Getter(cell).start();
		
		try {
			Thread.sleep(2000);
		} catch (Exception ex){}
		
		new Setter(cell,303).start();
	}
}

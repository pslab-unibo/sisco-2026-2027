package sisco.ex01_synchwithsem;

public class Ponger extends ActiveComponent {
	
	public Ponger() {
	}	
	
	public void run() {
		while (true) {
			try {
				println("pong");
			} catch (Exception ex) {}
				
		}
	}
}
package sisco.ex01_synchwithsem;

public class Pinger extends ActiveComponent {
	
	public Pinger() {
	}	
	
	public void run() {
		while (true) {
			try {
				println("ping");
			} catch (Exception ex) {};
		}
	}
}
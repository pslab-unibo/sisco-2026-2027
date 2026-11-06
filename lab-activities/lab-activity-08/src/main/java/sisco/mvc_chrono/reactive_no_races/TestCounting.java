package sisco.mvc_chrono.reactive_no_races;

public class TestCounting {
	public static void main(String[] args) {
		var counter = new Counter(0);
		var controller = new Controller(counter);
        new CounterGUI(counter, controller).display();
	}
}

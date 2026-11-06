package sisco.mvc_async_cmd;

import sisco.mvc_async_cmd.controller.*;
import sisco.mvc_async_cmd.model.AutonomousUpdater;
import sisco.mvc_async_cmd.model.Counter;
import sisco.mvc_async_cmd.view.View;
import sisco.mvc_async_cmd.view.ViewModel;

/**
 * 
 * Async Commands from the keyboard:
 * 
 * - key "I" to increase
 * - key "D" to decrease
 * - key "R" to reset the clock
 * 
 */
public class Main {

	
	public static void main(String[] argv) {

		var model = new Counter(0);
		
		var controller = new ActiveController(model);				
		
		var viewModel = new ViewModel(model.getCount());
		var view = new View(viewModel, controller);
		model.addObserver(view);
		
		controller.start();
		
		var updater = new AutonomousUpdater(model);
		updater.start();		
		
		view.display();

	}
	
}

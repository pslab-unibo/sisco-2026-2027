package sisco.mvc_async_cmd.view;

import javax.swing.SwingUtilities;

import sisco.mvc_async_cmd.controller.ActiveController;
import sisco.mvc_async_cmd.controller.Cmd;
import sisco.mvc_async_cmd.model.Counter;
import sisco.mvc_async_cmd.model.CounterObserver;
import sisco.mvc_async_cmd.util.BoundedBuffer;

public class View implements CounterObserver {

	private ViewModel viewModel;
	
	private ViewFrame frame;
	
	public View(ViewModel viewModel, ActiveController controller) {
		this.viewModel = viewModel;
		frame = new ViewFrame(viewModel, controller);	
	}

	public void display() {
		SwingUtilities.invokeLater(() -> {
			frame.setVisible(true);
		});
	}
	
	@Override
	public synchronized void modelUpdated(Counter model) {
		viewModel.update(model.getCount());
		frame.refresh();
	}
}

package sisco.mvc_chrono.not_reactive_plus_races;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CounterGUI extends JFrame 
                        implements ActionListener, CounterEventListener {

	private JButton start;
	private JButton stop;
	private JButton reset;
	private JTextField display;
	
	private Controller controller;
	private Counter counter;
	
	public CounterGUI(Counter c, Controller contr){
		setTitle("Counter GUI");
		setSize(300,100);		
		counter = c;		
		controller = contr;
		display = new JTextField(5);
		display.setText(""+ c.getValue());
		display.setEditable(false);		
		start = new JButton("start");
		stop  = new JButton("stop");
		reset = new JButton("reset");
		stop.setEnabled(false);
		
		Container cp = getContentPane();
		JPanel panel = new JPanel();
		
		Box p0 = new Box(BoxLayout.X_AXIS);
		p0.add(display);
		Box p1 = new Box(BoxLayout.X_AXIS);
		p1.add(start);
		p1.add(stop);
		p1.add(reset);
		Box p2 = new Box(BoxLayout.Y_AXIS);
		p2.add(p0);
		p2.add(Box.createVerticalStrut(10));
		p2.add(p1);
		
		panel.add(p2);
		cp.add(panel);
		
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent ev){
				System.exit(-1);
			}
			public void windowClosed(WindowEvent ev){
				System.exit(-1);
			}
		});

		start.addActionListener(this);
		stop.addActionListener(this);
		reset.addActionListener(this);
		counter.addListener(this);
	}
	
	public void actionPerformed(ActionEvent ev){
		Object src = ev.getSource();
		if (src==start){	
			controller.notifyStarted();
			start.setEnabled(false);
			stop.setEnabled(true);
			reset.setEnabled(false);			
		} else if (src == stop){
			controller.notifyStopped();
			start.setEnabled(true);
			stop.setEnabled(false);
			reset.setEnabled(true);
		} else if (src == reset){
			controller.notifyReset();
		}
	}

	/* this is called not by EDT: possible races */
	
	public void counterChanged(final CounterEvent ev){
		display.setText(""+ ev.getValue());
	}
	
	/* this is called not by EDT: possible races */
	
	public void display() {
        this.setVisible(true);
    }
	
	
}

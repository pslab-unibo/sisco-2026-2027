package sisco.mvc_async_cmd.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

import sisco.mvc_async_cmd.controller.*;
import sisco.mvc_async_cmd.util.*;

public class ViewFrame extends JFrame implements KeyListener {
    
    private VisualiserPanel panel;
    private ViewModel viewModel;
    private ActiveController controller;
    
    public ViewFrame(ViewModel viewModel, ActiveController controller){
    	setTitle("MVC with Async Cmds");
        setSize(400,400);
        setResizable(false);
        panel = new VisualiserPanel(400,400);
        getContentPane().add(panel);
        this.viewModel = viewModel;

		this.addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		requestFocusInWindow(); 
		this.controller = controller;
		
        addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent ev){
				System.exit(-1);
			}
			public void windowClosed(WindowEvent ev){
				System.exit(-1);
			}
		});
    }
     
    public void refresh(){
        panel.repaint();
    }
        
    public class VisualiserPanel extends JPanel {    
        public VisualiserPanel(int w, int h){
            setSize(w,h);
        }
        public void paint(Graphics g){
    		Graphics2D g2 = (Graphics2D) g;
    		var c = viewModel.getCurrent();
    		g2.setStroke(new BasicStroke(1));
    		g2.drawString("Current count: " + c , 20, 40);
    		g2.setStroke(new BasicStroke(3));
    		g2.drawLine(200,  200, 200 + (int)(100*Math.cos((90 - (c % 360))*2*Math.PI/360)), 200 - (int)(100*Math.sin((90 - (c % 360))*2*Math.PI/360)));
        }
        
    }

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getExtendedKeyCode() == KeyEvent.VK_I){
			controller.notifyNewCmd(new IncCmd());
		} else if (e.getExtendedKeyCode() == KeyEvent.VK_R){
			controller.notifyNewCmd(new ResetCmd());
		} else if (e.getExtendedKeyCode() == KeyEvent.VK_D){
			controller.notifyNewCmd(new DecCmd());
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}
}

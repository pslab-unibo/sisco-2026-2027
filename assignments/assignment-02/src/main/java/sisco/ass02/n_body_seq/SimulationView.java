package sisco.ass02.n_body_seq;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.RenderingHints;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.*;

/**
 * 
 * Simulation view
 *
 */
public class SimulationView {
        
	private VisualiserFrame frame;
    private long nFramesRendered;
    private long startTime;

    public SimulationView(int w, int h){
    	frame = new VisualiserFrame(w,h);
    }
    
    public void init() {
    	startTime = System.currentTimeMillis();
    	nFramesRendered = 0;	
    }
        
    public void display(List<Body> bodies, double vt, long iter){
    	nFramesRendered++;
    	long dt = System.currentTimeMillis() - startTime;
    	int framePerSec = (int)((((double)nFramesRendered)/dt)*1000);
    	frame.display(bodies, vt, iter, framePerSec); 
    }
    
    public static class VisualiserFrame extends JFrame {

        private VisualiserPanel panel;

        public VisualiserFrame(int w, int h){
            setTitle("Bodies Simulation");
            setSize(w,h);
            setResizable(false);
            panel = new VisualiserPanel(w,h);
            getContentPane().add(panel);
            addWindowListener(new WindowAdapter(){
    			public void windowClosing(WindowEvent ev){
    				System.exit(-1);
    			}
    			public void windowClosed(WindowEvent ev){
    				System.exit(-1);
    			}
    		});
    		this.setVisible(true);
        }
        
        public void display(List<Body> bodies, double vt, long iter, int framePerSec){
        	try {
	        	SwingUtilities.invokeAndWait(() -> {
	        		panel.display(bodies, vt, iter, framePerSec);
	            	repaint();
	        	});
        	} catch (Exception ex) {}
        };
        
        public void updateScale(double k) {
        	panel.updateScale(k);
        }    	
    }

    public static class VisualiserPanel extends JPanel implements KeyListener {
            	
    	private List<Body> bodies;
    	
    	private long nIter;
    	private int framePerSec = 0;
    	private double vt;
    	private double scale = 1;
    	
        private long dx;
        private long dy;
        
        
        public VisualiserPanel(int w, int h){
            setSize(w,h);
            dx = w/2 - 20;
            dy = h/2 - 20;
			this.addKeyListener(this);
			setFocusable(true);
			setFocusTraversalKeysEnabled(false);
			requestFocusInWindow(); 
        }

        public void paint(Graphics g){    		    		
    		if (bodies != null) {
        		Graphics2D g2 = (Graphics2D) g;
        		
        		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        		          RenderingHints.VALUE_ANTIALIAS_ON);
        		g2.setRenderingHint(RenderingHints.KEY_RENDERING,
        		          RenderingHints.VALUE_RENDER_QUALITY);
        		g2.clearRect(0,0,this.getWidth(),this.getHeight());
        		        		
	    		bodies.forEach( b -> {
	    			P2d p = b.getPos();
			        int radius = (int) (b.getRadius()*dx*scale);
			        if (radius < 1) {
			        	radius = 1;
			        }
			        g2.drawOval(getXcoord(p.getX()),getYcoord(p.getY()), radius, radius); 
			    });		    
	    		String time = String.format("%.2f", vt);
	    		g2.drawString("Bodies: " + bodies.size() + " - vt: " + time + " - nIter: " + nIter + " - frames/sec: " + framePerSec, 2, 20);
	    		g2.drawString("Commands: UP for zoom in, DOWN for zoom out", 2, 40);
    		}
        }
        
        private int getXcoord(double x) {
        	return (int)(dx + x*dx*scale);
        }

        private int getYcoord(double y) {
        	return (int)(dy - y*dy*scale);
        }
        
        public void display(List<Body> bodies, double vt, long iter, int framePerSec){
            this.bodies = bodies;
            this.vt = vt;
            this.nIter = iter;
            this.framePerSec = framePerSec;
        }
        
        public void updateScale(double k) {
        	scale *= k;
        }

		@Override
		public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 38){  		/* KEY UP */
					scale *= 1.1;
				} else if (e.getKeyCode() == 40){  	/* KEY DOWN */
					scale *= 0.9;  
				} 
		}

		public void keyReleased(KeyEvent e) {}
		public void keyTyped(KeyEvent e) {}
    }
}

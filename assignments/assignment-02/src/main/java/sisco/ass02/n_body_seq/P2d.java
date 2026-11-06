package sisco.ass02.n_body_seq;

/**
 * 
 * A point in a 2D space
 * 
 */
public class P2d { 

    private double x, y;

    public P2d(double x,double y){
        this.x = x;
        this.y = y;
    }

    public P2d sum(V2d v) {
    	x += v.getX();
    	y += v.getY();
    	return this;
    }
     
    public void change(double x, double y){
    	this.x = x;
    	this.y = y;
    }

    public double getX() {
    	return x;
    }

    public double getY() {
    	return y;
    }
    
    public String toString() {
    	return "(x: " + x + ", y: " + y + ")";
    }
}

package sisco.ass02.n_body_seq;

/**
 * 
 * A vector in a 2D space
 * 
 */
public class V2d  {

    private double x,y;

    public V2d(double x, double y){
        this.x = x;
        this.y = y;
    }

    public V2d(V2d v){
        this.x = v.x;
        this.y = v.y;
    }

    public V2d(P2d from, P2d to){
        this.x = to.getX() - from.getX();
        this.y = to.getY() - from.getY();
    }

    public V2d scalarMul(double k) {
    	x *= k;
    	y *= k;
    	return this;
    }
    
    public V2d sum(V2d v) {
    	x += v.x;
    	y += v.y;
    	return this;
    }
    
    public V2d normalize() throws NullVectorException {
    	double mod =  Math.sqrt(x*x + y*y);
    	if (mod > 0) {
    		x /= mod;
    		y /= mod;
    		return this;
    	} else {
    		throw new NullVectorException();
    	}

    }
    public V2d change(double x, double y) {
    	this.x = x;
    	this.y = y;
    	return this;
    }
    
    public double getX() {
    	return x;
    }

    public double getY() {
    	return y;
    }
    
    
}

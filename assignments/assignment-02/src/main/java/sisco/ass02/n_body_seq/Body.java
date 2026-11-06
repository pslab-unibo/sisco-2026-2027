package sisco.ass02.n_body_seq;

public class Body {
    
	private static final double G_CONST_SCALED = 0.001;
	
    private P2d pos;
    private V2d vel;
    private double mass;
    private double radius;
    private int id;
    
    public Body(int id, P2d pos, V2d vel, double radius, double mass){
    	this.id = id;
        this.pos = pos;
        this.vel = vel;
        this.radius = radius;
        this.mass = mass;
    }
    
    public double getMass() {
    	return mass;
    }
    
    public P2d getPos(){
        return pos;
    }

    public V2d getVel(){
        return vel;
    }
    
    public int getId() {
    	return id;
    }

    public double getRadius() {
    	return radius;
    }
    
    public boolean equals(Object b) {
    	return ((Body)b).id == id;
    }
    
    
    /**
     * Update the position, according to current velocity
     * 
     * @param dt time elapsed 
     */
    public void updatePos(double dt){    	
    	pos.sum(new V2d(vel).scalarMul(dt));
    }

    /**
     * Update the velocity, given the instant acceleration
     * @param acc instant acceleration
     * @param dt time elapsed
     */
    public void updateVelocity(V2d acc, double dt){    	
    	vel.sum(new V2d(acc).scalarMul(dt));
    }
    
    /**
     * Change the velocity
     * 
     * @param vx
     * @param vy
     */
    public void changeVel(double vx, double vy){
    	vel.change(vx, vy);
    }
  	
    public void forceTranslation(V2d v) {
    	pos.sum(v);
    }
    
    /**
     * Computes the distance from the specified body
     * 
     * @param b
     * @return
     */
    public double getDistanceFrom(Body b) {
    	double dx = pos.getX() - b.getPos().getX();
    	double dy = pos.getY() - b.getPos().getY();
    	return Math.sqrt(dx*dx + dy*dy);
    }
    
    /**
     * 
     * Compute G force exerted by another body
     * @param b
     * @return
     * @throws InfiniteForceException
     */
    public V2d computeGForceBy(Body b) throws InfiniteForceException {
		double dist = getDistanceFrom(b);
		if (dist > 0) {
			try {
				return new V2d(pos, b.getPos())
					.normalize()
					.scalarMul(b.getMass()*G_CONST_SCALED/(dist*dist));
			} catch (Exception ex) {
				throw new InfiniteForceException();
			}
		} else {
			throw new InfiniteForceException();
		}
    }    
}

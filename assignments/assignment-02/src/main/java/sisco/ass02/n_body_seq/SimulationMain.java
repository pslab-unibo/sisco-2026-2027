package sisco.ass02.n_body_seq;

import java.util.ArrayList;

/**
 * 
 * N-Body simulation - simple sequential version
 * 
 * @author aricci
 */
public class SimulationMain {

    public static void main(String[] args) {

    	
    	NBodySimulation sim = new NBodySimulation();
    	
    	// sim.init(TestBodySets.bodySet_two_bodies());
    	// sim.init(TestBodySets.bodySet_three_bodies());    	
    	sim.initRandom(5000);
    	
    	int nSteps = 100;
    	long startTime = System.currentTimeMillis();    	

    	log("Starting simulation with " + sim.getNumBodies() + " bodies - " + nSteps + " steps...");
    	
    	sim.execute(nSteps);
        
    	long endTime = System.currentTimeMillis();

    	log("Simulation completed in " + (endTime - startTime)/1000 + " seconds.");
        
    }
    
    private static void log(String msg) {
    	System.out.println("[" + System.currentTimeMillis() + "] " + msg);
    }


}

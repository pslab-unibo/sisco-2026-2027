package sisco.ass02.n_body_seq;

/**
 *
 * N-Body simulation - simple sequential version - vith a GUI
 * 
 * @author aricci
 */
public class SimulationMainWithGUI {

    public static void main(String[] args) {

    	int nSteps = 50000;

    	SimulationView view = new SimulationView(620,620);
    	NBodySimulation sim = new NBodySimulation(view);
    	
    	// sim.init(TestBodySets.bodySet_two_bodies());
    	// sim.init(TestBodySets.bodySet_three_bodies());    	
    	sim.initRandom(5000);
    	
    	sim.execute(nSteps);
   }
}

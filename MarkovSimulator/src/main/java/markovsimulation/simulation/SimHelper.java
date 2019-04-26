
package markovsimulation.simulation;

/**
 * Class for manipulating current running simulation.
 * @author eelismie
 */

public class SimHelper {
    double[][] savedState;
    double[][] savedTransition;
    
    public SimHelper(Simulation sim) {
        savedState = sim.state;
        savedTransition = sim.transition;
    }
    
    /**
     * Changes the transition matrix of the helped simulation to allow for jumps
     * between nodes, with beta describing the probability of jumps.
     * @param sim
     * @param beta 
     */
    
    public void allowJumps(Simulation sim, double beta) {
        int size = sim.size;
        double[][] newstate = new double[size][size];
        for (int i = 0; i < size; i++)  {
            for (int j = 0; j < size; j++) {
                newstate[i][j] = (1 - beta) * savedState[i][j] + beta * (1.0 / (float) size);
            }
        }
        sim.transition = newstate;
    }

    /**
     * recovers the original transition matrix for sim from the state saved in this helper.
     * @param sim simulation to recover state for 
     */
    public void disallowJumps(Simulation sim) {
        sim.transition = savedTransition;
    }
    
    /**
     * recovers the state matrix of sim
     * @param sim simulation to recover
     */
    
    public void recoverstate(Simulation sim) {
        sim.state = savedState;
    }
    
    public double[][] getsave() {
        return this.savedState;
    }
    
    public double[][] gettransition() {
        return this.savedTransition;
    }
}

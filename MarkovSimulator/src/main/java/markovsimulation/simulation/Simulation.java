
package markovsimulation.simulation;
import markovsimulation.domain.SimDescriptor;
import java.util.ArrayList;

/**
 * Class for running simulations
 * @author eelismie
 */

public class Simulation {
    int size;
    double[][] transition;
    double[][] state;
    /**
     * Constructor that creates 2d array representation of connections for running simulation
     * @param descriptor Simulation to turn into sim object
     */
    public Simulation(SimDescriptor descriptor) {
        this.size = descriptor.getNodes().size();
        ArrayList<ArrayList<Integer>> connections = descriptor.getConnects();
        
        transition = new double[size][size];
        state = new double[size][size];
        if (size == 0) { 
            return;
        }
        
        for (int i = 0; i < size; i++) {
            ArrayList<Integer> current = connections.get(i);
            if (current.isEmpty()) {
                transition[i][i] = 1.0; //if the node has no out-going connections, it is allowed to transition to itself.
                continue; //this keeps all of the paths inside the network and also avoids a division by zero.
            }
            for (int j : current) {
                transition[j][i] = 1.0 / ((double) current.size());
            }
        }
        state = transition;
    }
    /**
     * method for evolving the current state by step
     */
    public void next() {
        double[][] next = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                double sum = 0.0;
                for (int k = 0; k < size; k++) {
                    sum += state[i][k] * transition[k][j];
                }
                next[i][j] = sum;
            }
        }
        this.state = next;
    }
    
    public double[][] getTransitionMatrix() { //needed for tests
        return this.transition;
    }
    
    public double[][] getStateMatrix() { //needed for tests
        return this.state;
    }
    
    /**
     * Returns probabilities for each node for given starting index 
     * @param index starting node index
     * @return result
     */
    
    public ArrayList<Double> getProbability(int index) {
        ArrayList<Double> result = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            result.add(state[i][index]);
        }
        return result;
    }
    
}

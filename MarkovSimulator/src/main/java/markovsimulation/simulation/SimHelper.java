/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package markovsimulation.simulation;

import java.util.ArrayList;

public class SimHelper {
    double[][] savedstate;
    double[][] savedtransition;
    
    public SimHelper(Simulation sim){
        savedstate = sim.state;
        savedtransition = sim.transition;
    }
    
    public void allowJumps(Simulation sim, double beta) {
        int size = sim.size;
        double[][] newstate = new double[size][size];
        for (int i = 0; i < size; i++)  {
            for (int j = 0; j < size; j++) {
                // causes probabilities to lose normalization
                newstate[i][j] = (1.0 - beta) * savedstate[i][j] + beta * (1 / size);
            }
        }
        sim.transition = newstate;
    }
    
    public ArrayList<Double> sortedProbs(Simulation sim, int index) {
        int size = sim.size;
        double[][] state = sim.state;
        ArrayList<Double> result = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            result.add(state[i][index]);
        }
        return result;
    }
    
    public void disallowJumps(Simulation sim) {
        sim.transition = savedtransition;
    }

    public void recovertransition(Simulation sim) {
        sim.transition = savedtransition;
    }
    
    public void recoverstate(Simulation sim) {
        sim.state = savedstate;
    }
}

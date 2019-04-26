/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package markovsimulation.simulation;

public class SimHelper {
    double[][] savedState;
    double[][] savedTransition;
    
    public SimHelper(Simulation sim) {
        savedState = sim.state;
        savedTransition = sim.transition;
    }
    
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
    
    public void disallowJumps(Simulation sim) {
        sim.transition = savedTransition;
    }
    
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

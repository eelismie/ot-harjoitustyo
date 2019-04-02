
package markovsimulation.simulation;
import java.util.ArrayList;

public class Simulation {
    int size;
    double[][] transition;
    
    //constructor creates 2d array representation of connections
    public Simulation(ArrayList<String> names, ArrayList<ArrayList<Integer>> connections){
        this.size = names.size();
        transition = new double[size][size];
        if (size == 0) return;
        for (int i = 0; i < size; i++){
            ArrayList<Integer> current = connections.get(i);
            for (int j : current){
                transition[i][j] = 1.0/((double) current.size());
            }
        }
    }
    
    //method for evolving the current state by step
    public void next(){
        double[][] next = new double[size][size];
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                double sum = 0.0;
                for (int k = 0; k < size; k++){
                    sum += transition[i][k]*transition[k][j];
                }
                next[i][j] = sum;
            }
        }
        this.transition = next;
    }
    
    public double[][] getTransitionMatrix(){
        return this.transition;
    }
}

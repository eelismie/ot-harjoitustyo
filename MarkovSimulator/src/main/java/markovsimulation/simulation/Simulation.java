
package markovsimulation.simulation;
import java.util.ArrayList;

public class Simulation {
    int size;
    double[][] transition;
    
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
    
    
}

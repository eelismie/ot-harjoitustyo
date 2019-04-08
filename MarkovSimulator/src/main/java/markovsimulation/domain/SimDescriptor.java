
package markovsimulation.domain;

import java.util.ArrayList;
import java.util.HashSet;

public class SimDescriptor {
    HashSet<String> names;
    ArrayList<String> nodes;
    ArrayList<ArrayList<Integer>> connections;
    
    public SimDescriptor(){
        names = new HashSet<>();
        nodes = new ArrayList<>();
        connections = new ArrayList<>();
    }
    
    public void empty() {
        nodes = new ArrayList<>();
        connections = new ArrayList<>();
    }
}

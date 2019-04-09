
package markovsimulation.domain;

import java.util.ArrayList;
import java.util.HashSet;

public class SimDescriptor {
    HashSet<String> names;
    ArrayList<String> nodes;
    ArrayList<ArrayList<Integer>> connections;
    
    public SimDescriptor() {
        this.names = new HashSet<>();
        this.nodes = new ArrayList<>();
        this.connections = new ArrayList<>();
    }
    
    public SimDescriptor(HashSet names, ArrayList nodes, ArrayList connections){
        this.names = names;
        this.nodes = nodes;
        this.connections = connections;
    }
     
    public void empty() {
        nodes = new ArrayList<>();
        connections = new ArrayList<>();
        names = new HashSet<>();
    }
    
    public ArrayList<String> getNodes() {
        return this.nodes;
    }
    
    public ArrayList<ArrayList<Integer>> getConnects() { 
        return this.connections;
    }
    
    public HashSet<String> getNames() {
        return this.names;
    }
}


package markovsimulation.domain;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Class used for the encapsulation of all necessary simulation details
 * @author eelismie
 */

public class SimDescriptor {
    HashSet<String> names;
    ArrayList<String> nodes;
    ArrayList<ArrayList<Integer>> connections;
    
    /**
     * empty constructor
     */
    
    public SimDescriptor() {
        this.names = new HashSet<>();
        this.nodes = new ArrayList<>();
        this.connections = new ArrayList<>();
    }
    
    /**
     * Alternate constructor
     * @param names HashSet of node names
     * @param nodes String ArrayList of node names. Index describes node numbering. 
     * @param connections Adjacency list representation of connections. ArrayList of Integer ArrayLists.
     */
    
    public SimDescriptor(HashSet names, ArrayList nodes, ArrayList connections) {
        this.names = names;
        this.nodes = nodes;
        this.connections = connections;
    }
    
    /**
     * Method used to empty the memory of the object. 
     */
     
    public void empty() {
        nodes = new ArrayList<>();
        connections = new ArrayList<>();
        names = new HashSet<>();
    }
    
    /**
     * Getter method for nodes
     * @return ArrayList of nodes 
     */
    
    public ArrayList<String> getNodes() {
        return this.nodes;
    }
    
    /**
     * Getter method for connections
     * @return adjacency list representation of connections
     */
    
    public ArrayList<ArrayList<Integer>> getConnects() { 
        return this.connections;
    }
    
    /**
     * Getter method for node names
     * @return HashMap of names
     */
    
    public HashSet<String> getNames() {
        return this.names;
    }
}

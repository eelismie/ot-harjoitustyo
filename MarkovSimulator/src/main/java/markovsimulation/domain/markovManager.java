
package markovsimulation.domain;
import markovsimulation.simulation.Simulation;
import java.util.ArrayList;
import java.util.HashSet;

public class markovManager {
    boolean statesaved;
    Simulation currentsim;
    HashSet<String> names;
    ArrayList<String> nodes;
    ArrayList<ArrayList<Integer>> connections;
    
    public markovManager(){
        statesaved = false;
        nodes = new ArrayList<>();
        names = new HashSet<>();
        connections = new ArrayList<>();
    }
    
    public boolean initsim(){
        if (nodes.isEmpty()) return false;
        currentsim = new Simulation(nodes, connections);
        return true;
    }
    
    public void restart(){
        nodes = new ArrayList<>();
        connections = new ArrayList<>();
        currentsim = null;
    }
    
    public void addNode(String description){
        String trimmed = description.trim();
        nodes.add(trimmed);
        names.add(trimmed);
        connections.add(new ArrayList<>());
    }
    
    public boolean addConnect(String begin, String end){
        try {
            int a = Integer.parseInt(begin);
            int b = Integer.parseInt(end);
            boolean valid = (((a < nodes.size()) && (a >= 0))&&((b < nodes.size())&&(b >= 0)));
            if (valid && (a!=b)){
                connections.get(a).add(b);
                //connections.get(b).add(a); uncomment this for two-way connections;
                return true;
            }
        } catch(NumberFormatException e){
            return false;
        }
        return false;
    }
    
    public boolean nodeExists(String description){
        return names.contains(description);
    }
    
    public ArrayList getNodes(){
        return nodes;
    }
}

package markovsimulation.domain;
import markovsimulation.simulation.Simulation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class markovManager {
    Simulation currentsim;
    HashSet<String> names;
    ArrayList<String> nodes;
    ArrayList<ArrayList<Integer>> connections;
    
    public markovManager(){
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
                if (connections.get(a).contains(b)) return false;
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
    
    public boolean nothingloaded(){
        return (nodes.isEmpty());
    }
    
    public ArrayList getNodes(){
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < nodes.size(); i++){
            result.add(i + " : " +nodes.get(i));
        }
        return result;
    }
    
    public ArrayList getConnects(){
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < connections.size(); i++){
            final int a = i;
            ArrayList<Integer> ar = connections.get(i);
            if (ar.isEmpty()) continue;
            ar.forEach((j) -> {
                result.add(a + " -> " + j.toString());
            });
        }
        return result;
    }
    
    public ArrayList getProbabilities(int index){
        ArrayList<String> result = new ArrayList<>();
        ArrayList<Double> probs = currentsim.getProbability(index);
        for (int i = 0; i < connections.size(); i++){
            result.add(nodes.get(i) + " : " + probs.get(i).toString());
        }
        return result;
    }
    
    public void evolveCurrentSim(int n){
        for (int i = 0; i < n; i++){
            currentsim.next();
        }
    }
    
}

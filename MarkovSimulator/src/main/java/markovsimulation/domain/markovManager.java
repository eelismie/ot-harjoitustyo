/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package markovsimulation.domain;
import java.util.ArrayList;
import java.util.HashSet;

public class markovManager {
    boolean statesaved;
    boolean simexists;
    HashSet<String> names;
    ArrayList<String> nodes;
    ArrayList<ArrayList<Integer>> connections;
    
    public markovManager(){
        statesaved = false;
        simexists = false;
        nodes = new ArrayList<>();
        names = new HashSet<>();
        connections = new ArrayList<>();
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
                connections.get(b).add(a);
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

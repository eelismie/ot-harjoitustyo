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
    ArrayList<String> namenumbers;
    ArrayList<Integer>[] net;
    
    public markovManager(){
        statesaved = false;
        simexists = false;
        namenumbers = new ArrayList<>();
        names = new HashSet<>();
    }
    
    public void addNode(String description){
        namenumbers.add(description);
        names.add(description);
    }
    
    public boolean nodeExists(String description){
        return names.contains(description);
    }
}

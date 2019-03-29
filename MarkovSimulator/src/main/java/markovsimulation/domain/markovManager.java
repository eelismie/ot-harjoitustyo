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
    int nodes;
    
    public markovManager(){
        statesaved = false;
        simexists = false;
        namenumbers = new ArrayList<>();
    }
    
    public void addNode(String description){
        
    }
}

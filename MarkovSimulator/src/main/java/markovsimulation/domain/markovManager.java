
package markovsimulation.domain;

import markovsimulation.dao.SimDao;
import markovsimulation.simulation.Simulation;
import markovsimulation.simulation.SimHelper;
import java.util.ArrayList;

/**
 * Main logic class of the Markov process simulation. 
 * 
 * @author eelismie
 */

public class markovManager {
    int startNode;
    int stepSize;
    Simulation currentSim;
    SimHelper helper;
    SimDescriptor simDetails;
    
    public markovManager() {
        startNode = 0;
        stepSize = 1;
        simDetails = new SimDescriptor();
    }
    
    /**
     * Simulation initialisation class
     * @return boolean success value 
     */
    
    public boolean initSim() {
        if (simDetails.getNodes().isEmpty()) {
            return false;
        }
        currentSim = new Simulation(simDetails);
        helper = new SimHelper(currentSim);
        return true;
    }
    
    /**
     * Method for emptying simulation details
     */
    
    public void restart() {
        simDetails = new SimDescriptor();
        currentSim = null;
        helper = null;
    }
    
    /**
     * Adds trimmed version of node description to list of nodes, if it doesn't already exist.
     * @param description String describing node
     */
    
    public void addNode(String description) {
        String trimmed = description.trim();
        if (nodeExists(trimmed)) {
            return;
        }
        simDetails.getNodes().add(trimmed);
        simDetails.getConnects().add(new ArrayList<>());
        simDetails.getNames().add(trimmed);
    }
    
    /**
     * Method for adding connections to current loaded simulation.
     * 
     * @param begin String describing beginning index of connection
     * @param end String describing ending index of connection
     * @return boolean success value
     */
    
    public boolean addConnect(String begin, String end) {
        try {
            int a = Integer.parseInt(begin);
            int b = Integer.parseInt(end);
            int nodelen = simDetails.getNodes().size();
            boolean valid = (((a < nodelen) && (a >= 0)) && ((b < nodelen) && (b >= 0)));
            if (valid && (a != b)) {
                if (simDetails.getConnects().get(a).contains(b)) {
                    return false;
                }
                simDetails.getConnects().get(a).add(b);
                return true;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return false;
    }
    
    /**
     * Method for checking if a certain node is already in the network
     * @param description node to be checked
     * @return 
     */
    
    public boolean nodeExists(String description) {
        return simDetails.getNames().contains(description);
    }
    
    public boolean nothingLoaded() {
        if (simDetails == null) {
            return false;
        }
        return (simDetails.getNodes().isEmpty());
    }
    
    /**
     * Method for getting nodes and their indices
     * @return result
     */
    
    public ArrayList getNodes() {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < simDetails.getNodes().size(); i++) {
            result.add(i + " : " + simDetails.getNodes().get(i));
        }
        return result;
    }
    
    /**
     * Returns ArryList of Strings that describe connections in the network
     * @return result  
     */
    
    public ArrayList getConnects() {
        ArrayList<String> result = new ArrayList<>();
        int size = simDetails.getNodes().size();
        for (int i = 0; i < size; i++) {
            final int a = i;
            ArrayList<Integer> ar = simDetails.getConnects().get(i);
            if (ar.isEmpty()) {
                continue;
            }
            ar.forEach((j) -> {
                result.add(a + " -> " + j.toString());
            });
        }
        return result;
    }
    
    /**
     * Method for getting probabilities with selected starting node
     * @return result
     */
    
    public ArrayList getProbabilities() {
        int size = simDetails.getNodes().size();
        ArrayList<String> result = new ArrayList<>();
        ArrayList<Double> probs = currentSim.getProbability(startNode);
        ArrayList<String> nodes = simDetails.getNodes(); 
        
        for (int i = 0; i < size; i++) {
            result.add(nodes.get(i) + " : " + probs.get(i).toString());
        }       
        return result;
    }
    
    /**
     * Advance the current simulation by n steps 
     */
    
    public void evolveCurrentSim() {
        for (int i = 0; i < this.stepSize; i++) {
            currentSim.next();
        }
    }
    
    /**
     * Method for loading a simulation into the application
     * @param reader SimDao responsible for reading simulation
     * @return 
     */
    
    public boolean loadSim(SimDao reader) {
        try {
            SimDescriptor read = reader.loadSim();
            if (read == null) {
                return false;
            }
            this.simDetails = read;
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    
    /**
     * Save current loaded simulation to location defined by File object. 
     * @param saver SimDao responsible for saving simulation state
     * @return success 
     */
    
    public boolean saveSim(SimDao saver) {
        if (nothingLoaded()) {
            return false;
        }
        try {
            saver.saveSim(simDetails);
            return true;
        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }
    }
    
    /**
     * Adds or removes jumps to the current loaded simulation
     * @param beta 
     */
    
    public void addJumps(double beta) {
        if (beta > 0.01) {
            helper.allowJumps(currentSim, beta);
        } else {
            helper.disallowJumps(currentSim);
        }
    }
    
    /**
     * Recover the state of the current simulation
     */
    
    public void recoverSim() {
        helper.recoverstate(currentSim);
    }
    
    public void setResultDisplay(int i) {
        this.startNode = i;
    }
    
    public int getSize() {
        return simDetails.getNodes().size();
    }
    
    public Simulation getCurrentSim() {
        return this.currentSim;
    }
    
    public SimDescriptor getSimDescription() {
        return this.simDetails;
    }
    
    public void setStepSize(int n) {
        this.stepSize = n;
    }
}

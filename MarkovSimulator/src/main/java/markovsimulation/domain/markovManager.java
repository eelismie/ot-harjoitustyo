
package markovsimulation.domain;

import java.io.File;
import java.io.IOException;
import markovsimulation.simulation.Simulation;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class markovManager {
    int resultdisplay;
    Simulation currentsim;
    SimDescriptor simdetails;
    
    public markovManager() {
        resultdisplay = 0;
        simdetails = new SimDescriptor();
    }
    
    public boolean initsim() {
        if (simdetails.getNodes().isEmpty()) {
            return false;
        }
        currentsim = new Simulation(simdetails);
        return true;
    }
    
    public void restart() {
        simdetails = new SimDescriptor();
        currentsim = null;
    }
    
    public void addNode(String description) {
        String trimmed = description.trim();
        simdetails.getNodes().add(trimmed);
        simdetails.getConnects().add(new ArrayList<>());
        simdetails.getNames().add(trimmed);
    }
    
    public boolean addConnect(String begin, String end) {
        try {
            int a = Integer.parseInt(begin);
            int b = Integer.parseInt(end);
            int nodelen = simdetails.getNodes().size();
            boolean valid = (((a < nodelen) && (a >= 0)) && ((b < nodelen) && (b >= 0)));
            if (valid && (a != b)) {
                if (simdetails.getConnects().get(a).contains(b)) {
                    return false;
                }
                simdetails.getConnects().get(a).add(b);
                return true;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return false;
    }
    
    public boolean nodeExists(String description) {
        return simdetails.getNames().contains(description);
    }
    
    public boolean nothingloaded() {
        if (simdetails == null) {
            return false;
        }
        return (simdetails.getNodes().isEmpty());
    }
    
    public ArrayList getNodes() {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < simdetails.getNodes().size(); i++) {
            result.add(i + " : " + simdetails.getNodes().get(i));
        }
        return result;
    }
    
    public ArrayList getConnects() {
        ArrayList<String> result = new ArrayList<>();
        int size = simdetails.getNodes().size();
        for (int i = 0; i < size; i++) {
            final int a = i;
            ArrayList<Integer> ar = simdetails.getConnects().get(i);
            if (ar.isEmpty()) {
                continue;
            }
            ar.forEach((j) -> {
                result.add(a + " -> " + j.toString());
            });
        }
        return result;
    }
    
    public ArrayList getProbabilities() {
        ArrayList<String> result = new ArrayList<>();
        ArrayList<Double> probs = currentsim.getProbability(resultdisplay);
        int size = simdetails.getNodes().size();
        ArrayList<String> nodes = simdetails.getNodes();
        for (int i = 0; i < size; i++) {
            result.add(nodes.get(i) + " : " + probs.get(i).toString());
        }
        return result;
    }
    
    public void evolveCurrentSim(int n) {
        for (int i = 0; i < n; i++) {
            currentsim.next();
        }
    }
    
    public boolean loadsim(File file){
        SimFromFile filereader = new SimFromFile();
        try {
            SimDescriptor read = filereader.loadSim(file);
            
        } catch (Exception ex) {
            return false;
        }
        return false;
    }

    public void setResultDisplay(int i) {
        this.resultdisplay = i;
    }
    
    public int getSize() {
        return simdetails.getNodes().size();
    }
    
    public Simulation getCurrentSim() {
        return this.currentsim;
    }
    
    public SimDescriptor getSimDescription() {
        return this.simdetails;
    }
}


package markovsimulation.domain;

import java.io.File;
import markovsimulation.simulation.Simulation;
import markovsimulation.simulation.SimHelper;
import java.util.ArrayList;

public class markovManager {
    int resultdisplay;
    boolean resultsort;
    Simulation currentsim;
    SimHelper helper;
    SimDescriptor simdetails;
    
    public markovManager() {
        resultdisplay = 0;
        resultsort = false;
        simdetails = new SimDescriptor();
    }
    
    public boolean initsim() {
        if (simdetails.getNodes().isEmpty()) {
            return false;
        }
        currentsim = new Simulation(simdetails);
        helper = new SimHelper(currentsim);
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
        int size = simdetails.getNodes().size();
        ArrayList<String> result = new ArrayList<>();
        ArrayList<Double> probs;
        ArrayList<String> nodes; 
        if (!resultsort) {
            nodes = simdetails.getNodes();
            probs = currentsim.getProbability(resultdisplay);
        } else {
            nodes = simdetails.getNodes();
            probs = currentsim.getProbability(resultdisplay);
        }
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
    
    public boolean loadsim(File file) {
        SimFromFile filereader = new SimFromFile(file);
        try {
            SimDescriptor read = filereader.loadSim();
            this.simdetails = read;
            if (this.simdetails == null) {
                System.out.println("simdetails null");
                return false;
            }
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    
    public void setSort(boolean val) {
        resultsort = val;
    }
    
    public void addJumps(boolean allow, double beta) {
        if (allow){
            helper.allowJumps(currentsim, beta);
        } else {
            helper.disallowJumps(currentsim);
        }
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

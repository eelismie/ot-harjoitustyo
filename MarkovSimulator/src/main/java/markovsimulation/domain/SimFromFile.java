package markovsimulation.domain;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Class implementing SimDao that reads and writes simulations to and from .csv files
 * @author eelismie
 */

public class SimFromFile implements SimDao { 
    File location;
    
    public SimFromFile(File file) {
        location = file;
    }
    
    /**
     * Create SimDescriptor from File 
     * @return SimDescriptor
     * @throws Exception 
     */

    @Override
    public SimDescriptor loadSim() throws Exception {        
        ArrayList<ArrayList<Integer>> connections = new ArrayList<>();
        ArrayList<String> nodes = new ArrayList<>();
        HashSet<String> names = new HashSet<>();
        List<String[]> lines = getLines();
        int size = Integer.parseInt(lines.get(0)[0]);
        for (int i = 1; i < lines.size(); i++) {
            if (i <= size) {
                nodes.add(lines.get(i)[0]);
                names.add(lines.get(i)[0]);
                connections.add(new ArrayList<>());
            } else {
                int begin = Integer.parseInt(lines.get(i)[0]);
                int end = Integer.parseInt(lines.get(i)[1]);
                connections.get(begin).add(end);
            }
        }
        return new SimDescriptor(names, nodes, connections);
    }

    @Override
    public void saveSim(SimDescriptor sim) throws Exception {
        List<String[]> lines = generateLines(sim);
        try (CSVWriter writer = new CSVWriter(new FileWriter(location))) {
            for (String[] line : lines) {
                writer.writeNext(line);
            }
        }
    }
    
    /**
     * Helper method used to generate a list of String arrays from SimDescriptor
     * @param sim
     * @return lines
     */
    
    private List<String[]> generateLines(SimDescriptor sim) {
        ArrayList<ArrayList<Integer>> connects = sim.getConnects();
        List<String[]> lines = new ArrayList<>();
        String[] line1 = {Integer.toString(sim.getNodes().size())};
        lines.add(line1);
        for (String node : sim.getNodes()) {
            String[] line = {node};
            lines.add(line);
        }
        
        for (int i = 0; i < sim.getNodes().size(); i++) {
            for (Integer connect : connects.get(i)) {
                String[] line = {Integer.toString(i), Integer.toString(connect)};
                lines.add(line);
            }
        }        
        return lines;
    }
    
    private List<String[]> getLines() throws IOException {
        List<String[]> lines = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(location), ',' , '"' , 0)) {
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                lines.add(nextLine);
            }
        }
        return lines;
    }
}



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

public class SimFromFile implements SimDao { 
    File location;
    
    public SimFromFile(File file) {
        location = file;
    }

    @Override
    public SimDescriptor loadSim() throws Exception {
        
        CSVReader reader = new CSVReader(new FileReader(location), ',' , '"' , 0);
        ArrayList<ArrayList<Integer>> connections = new ArrayList<>();
        ArrayList<String> nodes = new ArrayList<>();
        HashSet<String> names = new HashSet<>();
        
        String[] nextLine;
        int size = 0;
        
        try {
            size = Integer.parseInt(reader.readNext()[0]);
        } catch (NumberFormatException e) {
            System.out.println("first number of csv (sim size) could not be read.");
            return null;
        }
        
        for (int i = 0; i < size; i++) {
            nextLine = reader.readNext();
            if ((nextLine == null) || nextLine[0].contentEquals("")) {
                return null;
            } else {
                nodes.add(nextLine[0]);
                connections.add(new ArrayList<>());
                names.add(nextLine[0]);
            }
        }
        
        while ((nextLine = reader.readNext()) != null) {
            if ((nextLine != null) && (nextLine.length == 2)) {
                String start = nextLine[0];
                String end = nextLine[1];
                try {
                    int a = Integer.parseInt(start);
                    int b = Integer.parseInt(end);
                    boolean valid = (((a < size) && (a >= 0)) && ((b < size) && (b >= 0)));
                    if (valid && (a != b)) {
                        if (!connections.get(a).contains(b)) {
                            connections.get(a).add(b);
                        }
                    }
                } catch (NumberFormatException e) {
                    return null;
                }
            }
        }
        reader.close();
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
    
    private List<String[]> getLines(File file) throws IOException {
        List<String[]> lines = new ArrayList<>();
        return lines;
    }
    
    private int convertToConnect() {
        return 1;
    }
}

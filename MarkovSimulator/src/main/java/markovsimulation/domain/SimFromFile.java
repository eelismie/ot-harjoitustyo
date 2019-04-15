

package markovsimulation.domain;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class SimFromFile implements SimDao { 
    File location;
    
    public SimFromFile(File file){
        location = file;
    }

    @Override
    public SimDescriptor loadSim() throws Exception {
        
        CSVReader reader = new CSVReader(new FileReader(location), ',' , '"' , 1);
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
        assert (size == nodes.size());
        
        while ((nextLine = reader.readNext()) != null) {
            if ((nextLine != null) && (nextLine.length == 2)) {
                String start = nextLine[0];
                String end = nextLine[1];
                System.out.println(start);
                System.out.println(end);
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
        return new SimDescriptor(names, nodes, connections);
    }

    @Override
    public void saveSim(SimDescriptor sim) throws Exception {
        ArrayList<String> nodes = sim.getNodes();
        ArrayList<ArrayList<Integer>> connects = sim.getConnects();
        int size = nodes.size();
        
        List<String[]> lines = new ArrayList<>();
        String[] line1 = {Integer.toString(size)};
        System.out.println(Arrays.toString(line1));
        lines.add(line1);
        for (String node : nodes) {
            String[] line = {node};
            System.out.println(Arrays.toString(line));
            lines.add(line);
        }
        for (int i = 0; i < size; i++) {
            for (Integer connect : connects.get(i)) {
                String[] line = {Integer.toString(i), Integer.toString(connect)};
                lines.add(line);
            }
        }
        
        CSVWriter writer = new CSVWriter(new FileWriter(location));
        writer.writeAll(lines);
    }
}

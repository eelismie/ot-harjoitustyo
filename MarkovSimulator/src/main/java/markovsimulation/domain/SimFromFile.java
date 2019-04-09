

package markovsimulation.domain;
import au.com.bytecode.opencsv.CSVReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class SimFromFile implements SimDao { 

    @Override
    public SimDescriptor loadSim(File file) throws Exception {
        
        CSVReader reader = new CSVReader(new FileReader(file), ',' , '"' , 1);
        ArrayList<ArrayList<Integer>> connections = new ArrayList<>();
        ArrayList<String> nodes = new ArrayList<>();
        HashSet<String> names = new HashSet<>();
        
        String[] nextLine;
        int size = 0;
        
        try {
            size = Integer.parseInt(reader.readNext()[0]);
        } catch (NumberFormatException e) {
            System.out.println("first number wrong");
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
    public SimDescriptor saveSim(File file) throws Exception {
        return null;
    }
}

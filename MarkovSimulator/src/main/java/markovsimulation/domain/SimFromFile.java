

package markovsimulation.domain;
import au.com.bytecode.opencsv.CSVReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

public class SimFromFile implements SimDao { 

    @Override
    public SimDescriptor loadSim(File file) throws Exception {
        CSVReader reader = new CSVReader(new FileReader(file), ',' , '"' , 1);
        reader.readAll();
        List<String[]> rows = reader.readAll();
        rows.forEach((row) -> {
            System.out.println(Arrays.toString(row));
        });
        return null;
    }

    @Override
    public SimDescriptor saveSim(File file) throws Exception {
        return null;//To change body of generated methods, choose Tools | Templates.
    }
}

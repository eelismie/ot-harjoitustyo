import au.com.bytecode.opencsv.CSVReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import markovsimulation.dao.SimDao;
import org.junit.Before;
import org.junit.Test;
import markovsimulation.domain.MarkovManager;
import markovsimulation.dao.SimFromFile;
import static org.junit.Assert.*;

public class SimFromFileTest {
    
    MarkovManager manager;
    
    @Before
    public void setUp() {
        this.manager = new MarkovManager();
        createNet();
    }
    
    @Test
    public void saveLoadOk() throws IOException {
        File temp = File.createTempFile("test", ".csv");
        temp.deleteOnExit();
        SimDao reader = new SimFromFile(temp);
        boolean saveOk = manager.saveSim(reader);
        manager.restart();
        boolean loadOk = manager.loadSim(reader);
        assertTrue(saveOk&&loadOk);
        assertTrue(!manager.nothingLoaded());
    }
    
    @Test
    public void correctLinesWritten() throws IOException {
        File temp = File.createTempFile("test", ".csv");
        temp.deleteOnExit();
        SimDao saver = new SimFromFile(temp);
        manager.saveSim(saver);
        
        List<String[]> lines = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(temp), ',' , '"' , 0)) {
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                lines.add(nextLine);
            }
        }       
        assertTrue("3".contentEquals(lines.get(0)[0]));
        assertTrue("event3".contentEquals(lines.get(3)[0]));
        assertTrue("1".contentEquals(lines.get(4)[1]));
    }
    
    @Test
    public void emptyFileReturnsFalse() throws IOException {
        File temp = File.createTempFile("empty", ".csv");
        temp.deleteOnExit();
        SimDao loader = new SimFromFile(temp);
        assertTrue(!manager.loadSim(loader));
    }
    
    private void createNet() {
        manager.addNode("event1");
        manager.addNode("event2");
        manager.addNode("event3");
        manager.addConnect("0", "1");
        manager.addConnect("0", "2");
        manager.initSim();       
    }
}

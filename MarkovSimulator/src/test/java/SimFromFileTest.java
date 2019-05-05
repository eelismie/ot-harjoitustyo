import java.io.File;
import java.io.IOException;
import markovsimulation.dao.SimDao;
import org.junit.Before;
import org.junit.Test;
import markovsimulation.domain.markovManager;
import markovsimulation.dao.SimFromFile;
import static org.junit.Assert.*;


public class SimFromFileTest {
    
    markovManager manager;
    
    @Before
    public void setUp() {
        this.manager = new markovManager();
        createNet();
    }
    
    @Test
    public void saveLoadOk() throws IOException {
        File temp = File.createTempFile("test", ".csv");
        temp.deleteOnExit();
        createNet();
        SimDao reader = new SimFromFile(temp);
        boolean saveOk = manager.saveSim(reader);
        boolean loadOk = manager.loadSim(reader);
        assertTrue(saveOk&&loadOk);
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

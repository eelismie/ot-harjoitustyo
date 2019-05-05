
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import markovsimulation.dao.SimDao;
import markovsimulation.dao.SimFromFile;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import markovsimulation.domain.markovManager;

public class markovManagerTest {
    markovManager manager;
    
    @Before
    public void setUp() {
        manager = new markovManager();
    }
    
    @Test
    public void initOk() {
        assertTrue(manager.getSize() == 0);
        assertTrue(manager.getSimDescription() != null);
        assertTrue(manager.getCurrentSim() == null);
    }
    
    @Test
    public void addNodeOk() {
        manager.addNode("event1");
        manager.addNode("event2 ");
        manager.addNode("event3");
        ArrayList<String> a = new ArrayList<>();
        a.add("event1");
        a.add("event2");
        a.add("event3");        
        assertTrue(a.equals(manager.getSimDescription().getNodes()));
        assertTrue(manager.getSimDescription().getConnects().size()==3);
    }
    
    @Test
    public void addBadConnectOk() {
        assertTrue(!manager.addConnect("0", "5"));
        assertTrue(!manager.addConnect("b", "e"));
        assertTrue(!manager.addConnect("", "2"));
    }
    
    public void simulationCycleWorks() {
        ArrayList<String> expected = new ArrayList<>();
        expected.add("event1 : 0.0");
        expected.add("event2 : 0.5");
        expected.add("event3 : 0.5");
        ArrayList<String> expected2 = new ArrayList<>();
        expected.add("event1 : 0.0");
        expected.add("event2 : 1.0");
        expected.add("event3 : 0.0");
        
        manager.addNode("event1");
        manager.addNode("event2");
        manager.addNode("event3");
        manager.addConnect("0", "1");
        manager.addConnect("0", "2");
        manager.initSim();
        manager.evolveCurrentSim();
        assertTrue(expected.equals(manager.getProbabilities()));
        manager.setResultDisplay(1);
        assertTrue(expected2.equals(manager.getProbabilities()));
    }
    
    @Test
    public void nodeExistWorks() {
        manager.addNode("  event1  ");
        assertTrue(manager.nodeExists("event1"));
        assertTrue(!manager.nodeExists("this one doesn't"));
    }
    
    @Test
    public void canaddJumps() {
        createNet();
        manager.addJumps(1.0);
        double val = 1.0 / 3.0;
        double[][] expected = {{val, val, val}, {val, val, val}, {val, val, val}};
        double[][] expected2 = manager.getCurrentSim().getStateMatrix();
        assertTrue(Arrays.deepEquals(expected, manager.getCurrentSim().getTransitionMatrix()));
        manager.addJumps(0.0);
        assertTrue(Arrays.deepEquals(expected2, manager.getCurrentSim().getStateMatrix()));
    }
    
    @Test
    public void getConnectsOk() {
        createNet();
        ArrayList<String> expected = new ArrayList<>();
        expected.add("0 -> 1");
        expected.add("0 -> 2");
        assertTrue(expected.equals(manager.getConnects()));
    }
    
    @Test
    public void getProbabilitiesOk() {
        createNet();
        ArrayList<String> expected = new ArrayList<>();
        expected.add("event1 : 0.0");
        expected.add("event2 : 0.5");
        expected.add("event3 : 0.5");
        ArrayList<String> received = manager.getProbabilities();
        assertTrue(received.equals(expected));
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

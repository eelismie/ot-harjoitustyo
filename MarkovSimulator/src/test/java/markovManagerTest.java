/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.Arrays;
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
        manager.initsim();
        manager.evolveCurrentSim(1);
        assertTrue(expected.equals(manager.getProbabilities()));
        manager.setResultDisplay(1);
        assertTrue(expected2.equals(manager.getProbabilities()));
    }
}

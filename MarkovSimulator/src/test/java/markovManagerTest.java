/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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
        manager.addNode("   event1");
        manager.addNode("event2 ");
        manager.addNode("event3");
        
        ArrayList<String> a = new ArrayList<>();
        a.add("event1");
        a.add("event2");
        a.add("event3");
    }
    

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}

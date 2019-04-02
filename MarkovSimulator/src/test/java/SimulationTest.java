/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Arrays;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


import markovsimulation.simulation.Simulation;
import java.util.ArrayList;
public class SimulationTest {
    Simulation sim;
    
    @Before
    public void setUp() {
        ArrayList<String> names = new ArrayList<>();
        ArrayList<ArrayList<Integer>> connects = new ArrayList<>();
        
        names.add("Event 1");
        names.add("Event 2");
        names.add("Event 3");
        for (int i = 0; i < 3; i++){connects.add(new ArrayList<>());};
        connects.get(0).add(1);
        connects.get(0).add(2);
        connects.get(1).add(0);
        connects.get(2).add(0);
        sim = new Simulation(names, connects);
    }
    
    @Test 
    public void simInitializedCorrectly(){
        double[][] expected = new double[3][3];
        expected[0][1] = 0.5;
        expected[0][2] = 0.5;
        expected[2][0] = 1.0;
        expected[1][0] = 1.0;
        assertTrue(Arrays.deepEquals(expected, sim.getTransitionMatrix()));
    }
    
    @Test
    public void networkWithNoConnectsOK(){
        ArrayList<String> nodes = new ArrayList<>();
        ArrayList<ArrayList<Integer>> connections = new ArrayList<>();
        
        nodes.add("Event 1");
        nodes.add("Event 2");
        nodes.add("Event 3");
        for (int i = 0; i < 3; i++){connections.add(new ArrayList<>());};
        Simulation sim2 = new Simulation(nodes, connections);
        
        double[][] expected = {{1.0, 0.0, 0.0}, {0.0,1.0,0.0}, {0.0,0.0,1.0}};
        assertTrue(Arrays.deepEquals(expected, sim2.getTransitionMatrix()));
    }
        
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}

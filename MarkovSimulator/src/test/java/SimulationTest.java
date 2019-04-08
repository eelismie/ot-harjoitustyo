
import java.util.Arrays;
import org.junit.Before;
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
        sim = new Simulation(names, connects);
    }
    
    @Test 
    public void simInitializedCorrectly(){
        double[][] expected = new double[3][3];
        expected[1][0] = 0.5;
        expected[2][0] = 0.5;
        expected[1][1] = 1.0;
        expected[2][2] = 1.0;
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
    
    @Test
    public void nextWorks() {
        double[][] expected = new double[3][3];
        expected[1][0] = 0.5;
        expected[2][0] = 0.5;
        expected[1][1] = 1.0;
        expected[2][2] = 1.0;
        
        sim.next();
        assertTrue(Arrays.deepEquals(expected, sim.getStateMatrix()));
    }
    
    @Test
    public void getProbsWorks(){
        ArrayList<Double> expected1 = new ArrayList<>(Arrays.asList(0.0, 0.5, 0.5));
        ArrayList<Double> expected2 = new ArrayList<>(Arrays.asList(0.0, 1.0, 0.0));
        ArrayList<Double> expected3 = new ArrayList<>(Arrays.asList(0.0, 0.0, 1.0));
        
        sim.next();
        assertTrue(expected1.equals(sim.getProbability(0)));
        assertTrue(expected2.equals(sim.getProbability(1)));
        assertTrue(expected3.equals(sim.getProbability(2)));
    }
        
}

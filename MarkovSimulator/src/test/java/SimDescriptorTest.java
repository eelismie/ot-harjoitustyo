import java.util.ArrayList;
import java.util.HashSet;
import markovsimulation.domain.SimDescriptor;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;

public class SimDescriptorTest {

    SimDescriptor sim;
    
    @Before
    public void setUp() {
        ArrayList<String> nodes = new ArrayList<>();
        ArrayList<ArrayList<Integer>> connections = new ArrayList<>();
        nodes.add("a");
        nodes.add("b");
        connections.add(new ArrayList<>());
        connections.add(new ArrayList<>());
        HashSet<String> names = new HashSet<>(nodes);
        connections.get(0).add(1);
        sim = new SimDescriptor(names, nodes, connections);
    }
    
    @Test
    public void canEmpty() {
        sim.empty();
        assertTrue(sim.getNames().isEmpty());
        assertTrue(sim.getNodes().isEmpty());
        assertTrue(sim.getNames().isEmpty());
    }
}

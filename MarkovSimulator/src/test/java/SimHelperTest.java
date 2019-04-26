/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.Arrays;
import markovsimulation.domain.SimDescriptor;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import markovsimulation.simulation.SimHelper;
import markovsimulation.simulation.Simulation;

public class SimHelperTest {
    Simulation sim;
    SimHelper helper;
    
    @Before
    public void setUp() {
        SimDescriptor details = new SimDescriptor();
        details.getNodes().add("Event1");
        details.getNodes().add("Event2");
        details.getNodes().add("Event3");
        details.getConnects().add(new ArrayList<>());
        details.getConnects().add(new ArrayList<>());
        details.getConnects().add(new ArrayList<>());
        details.getConnects().get(0).add(1);
        details.getConnects().get(0).add(2);
        details.getConnects().get(1).add(0);
        details.getConnects().get(2).add(0);
        sim = new Simulation(details);
        helper = new SimHelper(sim);
    }
    
    @Test
    public void initOK() {
        assertTrue(Arrays.deepEquals(sim.getStateMatrix(), helper.getsave()) &&
            Arrays.deepEquals(helper.gettransition(), sim.getTransitionMatrix()));
    }
}

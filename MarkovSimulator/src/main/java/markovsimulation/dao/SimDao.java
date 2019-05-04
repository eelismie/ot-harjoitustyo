
package markovsimulation.dao;

import markovsimulation.domain.SimDescriptor;

/**
 *
 * @author eelismie
 * 
 * Simulation data access model interface. Has methods loadSim() for creating SimDescriptor
 * and method saveSim that takes as a parameter a SimDescriptor and saves it. 
 */

public interface SimDao {
    SimDescriptor loadSim() throws Exception;
    void saveSim(SimDescriptor description) throws Exception;
}


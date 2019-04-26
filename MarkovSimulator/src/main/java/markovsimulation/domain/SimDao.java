
package markovsimulation.domain;

/**
 *
 * @author eelismie
 * 
 * Simulation data access model interface. Has methods loadSim() for creating SimDescriptor
 * and method saveSim that takes as a parameter a SimDescriptor and saves it according 
 * to the rules defined in the class. 
 */
public interface SimDao {
    SimDescriptor loadSim() throws Exception;
    void saveSim(SimDescriptor description) throws Exception;
}


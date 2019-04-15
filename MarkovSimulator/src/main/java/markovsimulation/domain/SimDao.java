
package markovsimulation.domain;

public interface SimDao {
    SimDescriptor loadSim() throws Exception;
    void saveSim(SimDescriptor description) throws Exception;
}


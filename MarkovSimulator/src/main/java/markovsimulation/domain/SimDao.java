
package markovsimulation.domain;

public interface SimDao {
    SimDescriptor loadSim() throws Exception;
    boolean saveSim(SimDescriptor description) throws Exception;
}



package markovsimulation.domain;

import java.io.File;

public interface SimDao {
    SimDescriptor loadSim(File file) throws Exception;
    SimDescriptor saveSim(File file) throws Exception;
}


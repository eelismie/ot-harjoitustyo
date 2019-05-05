import org.junit.Before;
import org.junit.Test;
import markovsimulation.domain.markovManager;
import markovsimulation.dao.SimFromDb;
import java.sql.*;
import static org.junit.Assert.*;

/**
 *
 * @author eelismie
 */
public class SimFromDbTest {
    
    markovManager manager;
    SimFromDb database;
    String testDb = "test.db";
    
    @Before
    public void setUp() {
        this.manager = new markovManager();
        this.database = new SimFromDb();
        this.database.initDb(this.testDb);
        createNet();
    }
    
    @Test
    public void saveLoadOk() {
        assertTrue(manager.saveSim(database));
        assertTrue(manager.loadSim(database));
    }
    
    private void createNet() {
        manager.addNode("event1");
        manager.addNode("event2");
        manager.addNode("event3");
        manager.addConnect("0", "1");
        manager.addConnect("0", "2");
        manager.initSim();       
    }
    
    public Connection getConnection() throws SQLException {
        String dbLocation = System.getenv("JDBC_DATABASE_URL");
        if (dbLocation != null && dbLocation.length() > 0) {
            return DriverManager.getConnection(dbLocation);
        }
        return DriverManager.getConnection("jdbc:sqlite:" + this.testDb);
    }
}

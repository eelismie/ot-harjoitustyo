package markovsimulation.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import markovsimulation.domain.SimDescriptor;

/**
 * Class implementing SimDao that accesses simulations through an SQLite database
 * @author eelismie
 */

public class SimFromDb implements SimDao {
    
    private String name;
    
    /**
     * Method for initialising all the necessary database tables.
     * @param name name of database
     */
    
    public void initDb(String name) {
        this.name = name;
        try {
            makeNodes();
            makeConnects();
        } catch (SQLException e) {
            System.out.println("SQL error: can't initialize database!");
        }
    }
    
    /**
     * Method for creating the 'Nodes' table in the database
     * @throws SQLException 
     */
    
    private void makeNodes() throws SQLException {
        Connection dbConnect = getConnection();
        PreparedStatement makeNodes = dbConnect.prepareStatement("CREATE TABLE IF NOT EXISTS Nodes ("
                + "id INT AUTO_INCREMENT,"
                + "name VARCHAR(255),"
                + "PRIMARY KEY (id))");
        makeNodes.execute();
        makeNodes.close();
        dbConnect.close();
    }
    
    /**
     * Method for creating the 'Connections' table in the database
     * @throws SQLException 
     */
    
    private void makeConnects() throws SQLException {
        Connection dbConnect = getConnection();
        PreparedStatement makeConnects = dbConnect.prepareStatement("CREATE TABLE IF NOT EXISTS Connections ("
                + "id INT AUTO_INCREMENT,"
                + "start INT,"
                + "end INT,"
                + "PRIMARY KEY (id))");
        makeConnects.execute();
        makeConnects.close();
        dbConnect.close();
    }
    
    /**
     * class implementing loadSim from SimDao
     * @return SimDescriptor for loaded sim
     * @throws Exception 
     */

    @Override
    public SimDescriptor loadSim() throws Exception {
        Connection dbConnect = getConnection();
        ArrayList<String> nodes = loadNodes(dbConnect);
        ArrayList<ArrayList<Integer>> connects = loadConnections(dbConnect, nodes.size());
        HashSet<String> names = new HashSet(nodes);
        dbConnect.close();
        return new SimDescriptor(names, nodes, connects);
    }
    
    /**
     * Implementation of saveSim from SimDao
     * @param description SimDescriptor of saved sim
     * @throws Exception 
     */

    @Override
    public void saveSim(SimDescriptor description) throws Exception {
        Connection dbConnect = getConnection();
        saveConnections(description.getConnects(), dbConnect);
        saveNodes(description.getNodes(), dbConnect);
        dbConnect.close();
    }
    
    /**
     * Saves an ArrayList of connections to database. 
     * @param connects list of lists of connections 
     * @param dbConnect established java sql database connection
     * @throws SQLException 
     */
    
    private void saveConnections(ArrayList<ArrayList<Integer>> connects, Connection dbConnect) throws SQLException {
        clearConnects(dbConnect);
        PreparedStatement add = dbConnect.prepareStatement("INSERT INTO Connections (start, end)"
                + "VALUES (?, ?)");
        for (int i = 0; i < connects.size(); i++) {
            ArrayList<Integer> currentList = connects.get(i);
            for (Integer j : currentList) {
                add.setInt(1, i);
                add.setInt(2, j);
                add.addBatch();
            }
        }
        add.executeBatch();
        add.close();
    }
    
    /**
     * Saves an ArrayList of nodes to the Database
     * @param nodes List of nodes
     * @param dbConnect established java sql connection 
     * @throws SQLException 
     */
    
    private void saveNodes(ArrayList<String> nodes, Connection dbConnect) throws SQLException {
        clearNodes(dbConnect);
        PreparedStatement add = dbConnect.prepareStatement("INSERT INTO Nodes (name)"
                + "VALUES (?)");
        for (String name : nodes) {
            add.setString(1, name);
            add.addBatch();
        }
        add.executeBatch();
        add.close();    
    }
    
    private void clearConnects(Connection connect) throws SQLException {
        PreparedStatement clear = connect.prepareStatement("DELETE FROM Connections");
        clear.execute();
        clear.close();
    }
    
    private void clearNodes(Connection connect) throws SQLException {
        PreparedStatement clear = connect.prepareStatement("DELETE FROM Nodes");
        clear.execute();
        clear.close();
    }
    
    /**
     * Loads an arrayList of connections from database
     * @param dbConnect established java sql connection
     * @param size size of the simulation
     * @return ArrayList<ArrayList<Integer>> connections
     * @throws SQLException 
     */
    
    private ArrayList<ArrayList<Integer>> loadConnections(Connection dbConnect, int size) throws SQLException {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        PreparedStatement stmnt = dbConnect.prepareStatement("SELECT * FROM Connections");
        ResultSet set = stmnt.executeQuery();
        for (int i = 0; i < size; i++) {
            result.add(new ArrayList<>());
        }
        while (set.next()) {
            result.get(set.getInt("start")).add(set.getInt("end"));
        }
        stmnt.close();
        return result;
    }
    
    /**
     * Returns nodes loaded from database as a list
     * @param dbConnect sql connection
     * @return ArrayList<String> nodes
     * @throws SQLException 
     */

    private ArrayList<String> loadNodes(Connection dbConnect) throws SQLException {
        ArrayList<String> result = new ArrayList<>();
        PreparedStatement stmnt = dbConnect.prepareStatement("SELECT * FROM Nodes");
        ResultSet set = stmnt.executeQuery();
        while (set.next()) {
            result.add(set.getString("name"));
        }
        stmnt.close();
        return result;
    }
    
    /**
     * Returns sql connection
     * @return Connection object
     * @throws SQLException 
     */
    
    public Connection getConnection() throws SQLException {
        String dbLocation = System.getenv("JDBC_DATABASE_URL");
        if (dbLocation != null && dbLocation.length() > 0) {
            return DriverManager.getConnection(dbLocation);
        }
        return DriverManager.getConnection("jdbc:sqlite:" + this.name);
    } 
}

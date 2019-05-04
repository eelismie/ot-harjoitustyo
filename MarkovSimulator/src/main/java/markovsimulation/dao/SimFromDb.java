/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package markovsimulation.dao;

import markovsimulation.domain.SimDescriptor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

    @Override
    public SimDescriptor loadSim() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveSim(SimDescriptor description) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Connection getConnection() throws SQLException {
        String dbLocation = System.getenv("JDBC_DATABASE_URL");
        if (dbLocation != null && dbLocation.length() > 0) {
            return DriverManager.getConnection(dbLocation);
        }
        return DriverManager.getConnection("jdbc:sqlite:" + this.name);
    }

    
}

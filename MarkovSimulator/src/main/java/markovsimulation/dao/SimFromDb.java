/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package markovsimulation.dao;

import markovsimulation.domain.SimDescriptor;
/**
 * Class implementing SimDao that accesses simulations through an SQLite database
 * @author eelismie
 */

public class SimFromDb implements SimDao {
    
    public void initDb() {
        return;
    }

    @Override
    public SimDescriptor loadSim() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveSim(SimDescriptor description) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

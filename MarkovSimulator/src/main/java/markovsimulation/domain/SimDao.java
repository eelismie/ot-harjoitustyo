/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package markovsimulation.domain;

import java.util.HashSet;

public interface SimDao {
    SimDescriptor loadSim();
    SimDescriptor saveSim();
}


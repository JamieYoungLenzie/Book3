package com.sas.memex.book3.dummy;

import com.sas.memex.book3.model.Subsector;
import com.sas.memex.book3.model.World;
import com.sas.memex.book3.interfaces.ISubsectorGeneratorService;
import com.sas.memex.book3.service.SubsectorGeneratorService;

public class SubsectorGeneratorServiceDummy implements ISubsectorGeneratorService {

    public SubsectorGeneratorServiceDummy() {

    }
    
    @Override
    public Subsector generate(SubsectorGeneratorService.Density density) {
        
        World earth = new World();
        World mars = new World();
        
        earth.setStarport("D");
        earth.setName("Earth");
        earth.setLocation("0505");
        earth.setSize(8);
        earth.setAtmosphere(6);
        earth.setHydrosphere(7);
        earth.setPopulation(9);
        earth.setGovernment(7);
        earth.setLawLevel(7);
        earth.setTechLevel(9);       
        earth.setUpp("D-867977-9");
        
        mars.setStarport("X");
        mars.setName("Mars");
        mars.setLocation("0506");
        mars.setSize(5);
        mars.setAtmosphere(1);
        mars.setHydrosphere(1);
        mars.setPopulation(0);
        mars.setGovernment(0);
        mars.setLawLevel(0);
        mars.setTechLevel(0);       
        mars.setUpp("X-511000-0");
        
        Subsector local = new Subsector();
        local.setName("Local");
        
        local.getWorlds().add(earth);
        local.getWorlds().add(mars);
        
        return local;
    }

    @Override
    public void print() {
        //TODO
    }
    
}

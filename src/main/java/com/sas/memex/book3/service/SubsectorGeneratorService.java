package com.sas.memex.book3.service;

import com.sas.memex.book3.interfaces.ISubsectorGeneratorService;
import com.sas.memex.book3.exception.CustomException;
import com.sas.memex.book3.model.Subsector;
import com.sas.memex.book3.model.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SubsectorGeneratorService implements ISubsectorGeneratorService {

    private final Subsector subsector;
    private final WorldGeneratorService worldGeneratorService;
    private final DiceService diceService;
    
    Logger logger = LoggerFactory.getLogger(SubsectorGeneratorService.class);

    public SubsectorGeneratorService(WorldGeneratorService worldGeneratorService, DiceService diceService, Subsector subsector) {
        this.subsector = subsector;
        this.worldGeneratorService = worldGeneratorService;
        this.diceService = diceService;
    }

    public enum Density {
        Sparse,
        Standard,
        Dense
    }    
    
    @Override
    public Subsector generate(Density density) {

        logger.trace("Enter SubsectorGeneratorService.generate");

        try {
            subsector.getWorlds().clear();
            
            for (int n = 1; n < 9; n++) {
                for (int k = 1; k < 11; k++) {
                    if (worldExists(density)) {
                        String location = String.format("%02d%02d", n, k);
                        World world = worldGeneratorService.generate();
                        world.setLocation(location);
                        subsector.getWorlds().add(world);
                    }
                }
            }
        } catch (Exception ex) {
            throw new CustomException(ex);
        }

        logger.trace("Exit SubsectorGeneratorService.generate");
        return subsector;
    }

    private Boolean worldExists(Density density) {

        Boolean exists = false;
        int roll = diceService.oneD6();

        switch (density) {
            case Sparse:
                if (roll < 3) {
                    exists = true;
                }
                break;
            case Standard:
                if (roll < 4) {
                    exists = true;
                }
                break;
            case Dense:
                if (roll < 5) {
                    exists = true;
                }
                break;
        }

        return exists;
    }

    @Override
    public void print() {
        for (World world : subsector.getWorlds()) {
            System.out.println(world.getLocation() + "\t" + world.getUpp());
        }
    }
}

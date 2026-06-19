package com.sas.memex.book3.service;

import com.sas.memex.book3.interfaces.IWorldGeneratorService;
import com.sas.memex.book3.model.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class WorldGeneratorService implements IWorldGeneratorService {

    Logger logger = LoggerFactory.getLogger(WorldGeneratorService.class);
    
    private final DiceService diceService;
    
    private final String[] upp = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
    private final String[] starport = {"", "", "A", "A", "A", "B", "B", "C", "C", "D", "E", "E", "X"};

    public WorldGeneratorService(DiceService diceService) {
        this.diceService = diceService;
    }
    
    @Override
    public World generate() {

        logger.trace("Enter WorldGeneratorService.generate");
        
        World world = new World();

        world.setName("Unknown");
        world.setLocation("0000");

        // Make a builder pattern?
        world.setStarport(generateStarport());
        world.setSize(generateSize());
        world.setAtmosphere(generateAtmosphere(world.getSize()));
        world.setHydrosphere(generateHydrosphere(world.getSize()));
        world.setPopulation(generatePopulation());
        world.setGovernment(generateGovernment(world.getPopulation()));
        world.setLawLevel(generateLawLevel(world.getGovernment()));
        world.setTechLevel(generateTechLevel(world.getStarport(), 
                world.getSize(), 
                world.getAtmosphere(), 
                world.getHydrosphere(), 
                world.getGovernment(), 
                world.getGovernment()));
        world.setUpp(generateUpp(world));

        logger.trace("Exit WorldGeneratorService.generate");
        return world;
    }

    private String generateStarport() {
        return starport[diceService.twoD6()];
    }

    private int generateSize() {
        return diceService.twoD6() - 2;
    }

    private int generateAtmosphere(int size) {
        return generateValue(size);
    }

    private int generateHydrosphere(int size) {
        int hydro = generateValue(size);
        return (hydro > 10) ? 10 : hydro;
    }

    private int generatePopulation() {
        return diceService.twoD6()- 2;
    }

    private int generateGovernment(int pop) {
        return generateValue(pop);
    }

    private int generateLawLevel(int gov) {
        return generateValue(gov);
    }

    private int generateTechLevel(String starport, int size, int atmos, int hydro, int pop, int gov) {

        int techLevel = diceService.oneD6();

        switch (starport) {
            case "A":
                techLevel = techLevel + 6;
                break;
            case "B":
                techLevel = techLevel + 4;
                break;
            case "C":
                techLevel = techLevel + 2;
                break;
            case "X":
                techLevel = techLevel - 4;
                break;
            default:
                break;
        }

        if (size == 0 || size == 1) {
            techLevel = techLevel + 2;
        }

        if (size == 2 || size == 3 || size == 4) {
            techLevel = techLevel + 1;
        }

        if (atmos < 4 || atmos > 9) {
            techLevel = techLevel + 1;
        }

        if (hydro == 9) {
            techLevel = techLevel + 1;
        }

        if (hydro == 10) {
            techLevel = techLevel + 2;
        }

        if (pop != 0 && pop < 6) {
            techLevel = techLevel + 1;
        }

        if (pop == 9) {
            techLevel = techLevel + 2;
        }

        if (pop == 10) {
            techLevel = techLevel + 4;
        }

        if (gov == 0 || gov == 5) {
            techLevel = techLevel + 1;
        }

        if (gov == 13) {
            techLevel = techLevel - 1;
        }

        techLevel = (pop <= 0) ? 0 : techLevel;
        
        return (techLevel < 0) ? 0 : techLevel;
    }

    private int generateValue(int modifier) {

        int value;

        if (modifier == 0) {
            value = 0;
        } else {
            value = diceService.twoD6() - 7 + modifier;
            value = (value < 0) ? 0 : value;
            value = (value > 12) ? 12 : value;
        }

        return value;
    }

    private String generateUpp(World world) {
        String profile = String.format("%s-%s%s%s%s%s%s-%s",
                world.getStarport(),
                upp[world.getSize()],
                upp[world.getAtmosphere()],
                upp[world.getHydrosphere()],
                upp[world.getPopulation()],
                upp[world.getGovernment()],
                upp[world.getLawLevel()],
                upp[world.getTechLevel()]
        );
        return profile;
    }
}

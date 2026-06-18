package com.sas.memex.book3.test.service;

import com.sas.memex.book3.model.World;
import com.sas.memex.book3.service.WorldGeneratorService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WorldGeneratorServiceTest {
    
    public WorldGeneratorServiceTest() {
    }
    
    @Autowired
    WorldGeneratorService worldGeneratorService;
            
    @Test
    public void testGenerate() {
        for (int n = 0; n < 20; n++){            
            World world = worldGeneratorService.generate();
            System.out.println(world.getUpp());
        }
    }
    
}

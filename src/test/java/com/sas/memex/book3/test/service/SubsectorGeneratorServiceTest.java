package com.sas.memex.book3.test.service;

import com.sas.memex.book3.model.Subsector;
import com.sas.memex.book3.model.World;
import com.sas.memex.book3.service.SubsectorGeneratorService;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SubsectorGeneratorServiceTest {
    
    @Autowired
    SubsectorGeneratorService subsectorGeneratorService;
            
    public SubsectorGeneratorServiceTest() {
    }
    
    @Test
    public void testGenerate() {
        Subsector subsector = subsectorGeneratorService.generate(SubsectorGeneratorService.Density.Standard);        
        List<World> worlds = subsector.getWorlds();
        assertTrue(worlds.size() > 0);
    }
}

package com.sas.memex.book3.test.service;

import com.sas.memex.book3.interfaces.ISubsectorGeneratorService;
import com.sas.memex.book3.dummy.SubsectorGeneratorServiceDummy;
import com.sas.memex.book3.model.Subsector;
import com.sas.memex.book3.service.LoadSaveService;
import com.sas.memex.book3.service.SubsectorGeneratorService.Density;
import java.io.File;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LoadSaveServiceTest {

    public LoadSaveServiceTest() {
    }

    @Autowired
    LoadSaveService service;
            
    @Test
    public void testLoad() {
        Subsector subsector = service.loadSubsector("Asgard");
        assertTrue(subsector.getWorlds().size() == 3);
    }    
    
    @Test
    public void testSave() {

        File f = new File("c:\\book3-app\\data\\Testsave.xml");
        if (f.exists()) {
            f.delete();
        }

        ISubsectorGeneratorService subSectorService = new SubsectorGeneratorServiceDummy();
        Subsector subsector = subSectorService.generate(Density.Standard);
        
        service.saveSubsector(subsector, "Testsave");

        f = new File("c:\\book3-app\\data\\Testsave.xml");
        assertTrue(f.exists());
        
        subsector = service.loadSubsector("Testsave");

        assertTrue(subsector.getWorlds().size() == 2);
    }
}

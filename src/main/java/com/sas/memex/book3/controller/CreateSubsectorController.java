package com.sas.memex.book3.controller;

import com.sas.memex.book3.model.Subsector;
import com.sas.memex.book3.model.World;
import com.sas.memex.book3.service.SubsectorGeneratorService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateSubsectorController {
    
    private final Logger logger = LoggerFactory.getLogger(CreateSubsectorController.class);
    
    private final SubsectorGeneratorService subsectorGeneratorService;
    
    // Autowired unnecessary with single constructor.
    public CreateSubsectorController(SubsectorGeneratorService subsectorGeneratorService) {
        this.subsectorGeneratorService = subsectorGeneratorService;
    }

    // GET by default
    @RequestMapping("/create") 
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<World>> createSubsector() {
        logger.info("CreateSubsectorController.createSubsector");
        Subsector subsector = subsectorGeneratorService.generate(SubsectorGeneratorService.Density.Standard);
        return ResponseEntity
            .ok()
            .body(subsector.getWorlds());
    }
}

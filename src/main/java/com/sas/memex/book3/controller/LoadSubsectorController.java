package com.sas.memex.book3.controller;

import com.sas.memex.book3.model.Subsector;
import com.sas.memex.book3.model.World;
import com.sas.memex.book3.service.LoadSaveService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoadSubsectorController {

    private final Logger logger = LoggerFactory.getLogger(LoadSubsectorController.class);
    
    private final LoadSaveService loadSaveService;
    
    @Autowired
    public LoadSubsectorController(LoadSaveService loadSaveService) {
        this.loadSaveService = loadSaveService;
    }
    
    @RequestMapping("/load/{name}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<World>> loadSubsector(@PathVariable String name) {        
        logger.info("LoadSubsectorController.loadSubsector");        
        Subsector subsector = loadSaveService.loadSubsector(name); 
        return ResponseEntity
            .ok()
            .body(subsector.getWorlds());
    }
}

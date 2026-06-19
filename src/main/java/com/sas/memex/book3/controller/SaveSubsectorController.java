package com.sas.memex.book3.controller;

import com.sas.memex.book3.model.Subsector;
import com.sas.memex.book3.service.LoadSaveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SaveSubsectorController {

    private final Logger logger = LoggerFactory.getLogger(SaveSubsectorController.class);

    private final LoadSaveService loadSaveService;

    public SaveSubsectorController(LoadSaveService loadSaveService) {
        this.loadSaveService = loadSaveService;
    }
    
    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Subsector> saveSubsector(@RequestBody Subsector subsector) {
        logger.info("SaveSubsectorController.saveSubsector");
        loadSaveService.saveSubsector(subsector, subsector.getName());        
        return ResponseEntity
            .ok()
            .body(subsector);
    }
}

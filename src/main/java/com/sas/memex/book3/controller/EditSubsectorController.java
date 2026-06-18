package com.sas.memex.book3.controller;

import com.sas.memex.book3.model.Subsector;
import com.sas.memex.book3.service.EditService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EditSubsectorController {

    private final Logger logger = LoggerFactory.getLogger(EditSubsectorController.class);

    private final EditService editService;

    // Autowired unnecessary with single constructor.
    public EditSubsectorController(EditService editService) {
        this.editService = editService;
    }

    @PutMapping(value = "/edit", consumes = MediaType.APPLICATION_JSON_VALUE)    
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Subsector> editSubsector(@RequestBody Subsector subsector) {
        logger.info("EditSubsectorController.editSubsector");
        editService.edit(subsector, subsector.getName());
        return ResponseEntity
            .ok()
            .body(subsector);
    }
}

package com.sas.memex.book3.controller;

import com.sas.memex.book3.service.DeleteService;
import com.sas.memex.book3.helper.Sanitise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeleteSubsectorController {

    private final Logger logger = LoggerFactory.getLogger(DeleteSubsectorController.class);

    private final DeleteService deleteService;

    // Autowired unnecessary with single constructor.
    public DeleteSubsectorController(DeleteService deleteService) {
        this.deleteService = deleteService;
    }

    @DeleteMapping("/delete/{subsectorName}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<String> delete(@PathVariable String subsectorName) {
        logger.info("DeleteSubsectorController.delete");
        
        // Validate input to prevent Path Traversal (CWE-23)
        if (!Sanitise.isFilename(subsectorName)) {
            throw new IllegalArgumentException(String.format("Invalid subsectorName: %s", subsectorName));
        }
        
        deleteService.deleteSubsector(subsectorName);
        return ResponseEntity
            .ok()
            .body(subsectorName);
    }
}

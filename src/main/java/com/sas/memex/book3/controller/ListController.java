package com.sas.memex.book3.controller;

import com.sas.memex.book3.model.SubsectorListItem;
import com.sas.memex.book3.service.ListService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ListController {

    private final Logger logger = LoggerFactory.getLogger(ListController.class);

    private final ListService listService;

    // Autowired unnecessary with single constructor.
    public ListController(ListService listService) {
        this.listService = listService;
    }
    
    @RequestMapping("/list")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<SubsectorListItem>> getList() {
        logger.info("Enter ListController.getList");
        return ResponseEntity
            .ok()
            .body(listService.getList().getItems());
    }
}

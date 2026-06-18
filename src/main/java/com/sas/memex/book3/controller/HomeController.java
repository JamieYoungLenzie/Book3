package com.sas.memex.book3.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    private final Logger logger = LoggerFactory.getLogger(HomeController.class);
    
    public HomeController() {        
    }
    
    @GetMapping("/")
    @CrossOrigin(origins = "http://localhost:3000")
    public String getHome() {
        logger.info("HomeController.getHome");
        return "forward:/index.html";
    }
}

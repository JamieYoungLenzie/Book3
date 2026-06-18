package com.sas.memex.book3.test.service;

import com.sas.memex.book3.helper.Sanitise;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SanitiseTest {
    
    public SanitiseTest() {
    }
    
    @Test
    public void SanitiseTest() {
        String file1 = "../test";
        String file2 = "test";
        String file3 = "/opt/memex/../etc/test";
        
        assertFalse(Sanitise.isFilename(file1));
        assertTrue(Sanitise.isFilename(file2));
        assertFalse(Sanitise.isFilename(file3));
        
    }
    
}

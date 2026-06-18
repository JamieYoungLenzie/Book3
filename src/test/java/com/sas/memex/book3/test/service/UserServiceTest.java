package com.sas.memex.book3.test.service;

import com.sas.memex.book3.model.AppUser;
import com.sas.memex.book3.model.AppUsers;
import com.sas.memex.book3.service.AppUserService;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {

    public UserServiceTest() {
    }

    @Autowired
    AppUserService service;
    
    @Test
    @Disabled
    public void loadTest() {
        AppUsers users = service.loadUsers();
        assertNotNull(users);
        assertTrue(users.getUsers().size() == 3);
        
        AppUser bobs = service.getUserById(2);
        assertTrue(bobs.getUsername().equals("bobs"));
        assertTrue(bobs.getPassword().equals("test"));  
        
        AppUser cindy = service.getUserByUsername("cindy");
        assertTrue(cindy.getPassword().equals("test"));
        assertTrue(cindy.getUsername().equals("cindy"));
        assertTrue(cindy.getId() == 3);
        assertTrue(cindy.getRole().equals("user"));
        
    }
    
}

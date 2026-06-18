package com.sas.memex.book3.service;

import com.sas.memex.book3.interfaces.IAppUserService;
import com.sas.memex.book3.exception.CustomException;
import com.sas.memex.book3.helper.Marshal;
import com.sas.memex.book3.model.AppUser;
import com.sas.memex.book3.model.AppUsers;
import java.io.File;
import org.springframework.stereotype.Service;

@Service
public class AppUserService implements IAppUserService {
    
    private final String folder;

   // Autowired unnecessary with single constructor.
    public AppUserService(String userFolder) {
        folder = userFolder;
    }
    
    @Override
    public AppUsers loadUsers() {
        try {
            String path = String.format("%s%susers.xml", folder, File.separator);
            return Marshal.unmarshall(AppUsers.class, path);
        } catch (Exception ex) {
            throw new CustomException(ex);
        }
    }

    @Override
    public AppUser getUserById(int id) {
        AppUsers users = loadUsers();
        return users.getUsers().stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .orElse(null);
    }
    
    @Override
    public AppUser getUserByUsername(String username) {
        AppUsers users = loadUsers();
        return users.getUsers().stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }
}

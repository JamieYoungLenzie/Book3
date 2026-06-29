package com.sas.memex.book3.service;

import com.sas.memex.book3.exception.CustomException;
import com.sas.memex.book3.helper.Marshal;
import com.sas.memex.book3.interfaces.IAppUserService;
import com.sas.memex.book3.model.AppUser;
import com.sas.memex.book3.model.AppUsers;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AppUserService implements IAppUserService {
    
    private final Path folderPath;

    public AppUserService(@Qualifier("userFolder") String userFolder) {
        if (userFolder == null || userFolder.isBlank()) {
            throw new IllegalArgumentException("userFolder must not be null or blank");
        }

        this.folderPath = Paths.get(userFolder);
    }
    
    @Override
    public AppUsers loadUsers() {
        try {
            Path path = folderPath.resolve("users.xml");
            return Marshal.unmarshal(AppUsers.class, path.toString());
        } catch (Exception ex) {
            throw new CustomException(ex);
        }
    }

    @Override
    public AppUser getUserById(int id) {
        return findUserById(id).orElse(null);
    }
    
    @Override
    public AppUser getUserByUsername(String username) {
        return findUserByUsername(username).orElse(null);
    }

    public Optional<AppUser> findUserById(int id) {
        AppUsers users = loadUsers();
        return users.getUsers().stream()
                .filter(u -> u.getId() == id)
                .findFirst();
    }

    public Optional<AppUser> findUserByUsername(String username) {
        AppUsers users = loadUsers();
        return users.getUsers().stream()
                .filter(u -> Objects.equals(u.getUsername(), username))
                .findFirst();
    }
}

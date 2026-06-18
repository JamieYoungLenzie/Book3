package com.sas.memex.book3.interfaces;

import com.sas.memex.book3.model.AppUser;
import com.sas.memex.book3.model.AppUsers;
import java.util.Optional;

public interface IAppUserService {

    AppUser getUserByUsername(String username);
    AppUser getUserById(int id);
    Optional<AppUser> findUserByUsername(String username);
    Optional<AppUser> findUserById(int id);
    AppUsers loadUsers();
    
}

package com.sas.memex.book3.interfaces;

import com.sas.memex.book3.model.AppUser;
import com.sas.memex.book3.model.AppUsers;

public interface IAppUserService {

    AppUser getUserByUsername(String username);
    AppUser getUserById(int id);
    AppUsers loadUsers();
    
}

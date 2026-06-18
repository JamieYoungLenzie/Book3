package com.sas.memex.book3.security;

import com.sas.memex.book3.exception.CustomException;
import com.sas.memex.book3.model.AppUser;
import com.sas.memex.book3.service.AppUserService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * Service that loads user details from the application's user data source.
 * Implements Spring Security's UserDetailsService interface to integrate
 * with the authentication framework.
 */

@Service
public class AppUserDetailsService implements UserDetailsService {
    
    private final AppUserService appUserService;

    public AppUserDetailsService(AppUserService appUserService) {
        this.appUserService = appUserService;
    }
    
    /**
     * Loads user details by username from the users.xml file.
     * Called by Spring Security during authentication to retrieve user credentials and roles.
     * 
     * @param userName the username to look up
     * @return UserDetails containing username, password, and assigned authorities (roles)
     * @throws CustomException if user not found or error occurs during data loading
     */

    @Override
    public UserDetails loadUserByUsername(String userName) {
        try {
            AppUser appUser = appUserService.findUserByUsername(userName)
                    .orElseThrow(() -> new CustomException("User not found: " + userName));

            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(appUser.getRole()));

            return new User(appUser.getUsername(), appUser.getPassword(), authorities);
        } catch (CustomException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new CustomException(ex);
        }
    }
}

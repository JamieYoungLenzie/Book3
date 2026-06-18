package com.sas.memex.book3.controller;

import com.sas.memex.book3.model.AuthenticationRequest;
import com.sas.memex.book3.security.AppUserDetailsService;
import com.sas.memex.book3.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AppUserDetailsService appUserDetailsService;

    @Autowired
    private JwtUtil JwtTokenUtil;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<UserDetails> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch (Exception ex) {
            throw new BadCredentialsException(ex.getMessage());
        }

        final UserDetails userDetails = appUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = "Bearer " + JwtTokenUtil.generateToken(userDetails);
        
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, jwt)
                .body(userDetails);
    }
}

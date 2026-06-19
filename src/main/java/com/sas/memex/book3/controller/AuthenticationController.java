package com.sas.memex.book3.controller;

import com.sas.memex.book3.model.AuthenticationRequest;
import com.sas.memex.book3.security.AppUserDetailsService;
import com.sas.memex.book3.security.JwtUtil;
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
    private final AuthenticationManager authenticationManager;
    private final AppUserDetailsService appUserDetailsService;
    private final JwtUtil jwtTokenUtil;

    public AuthenticationController(AuthenticationManager authenticationManager,
                                    AppUserDetailsService appUserDetailsService,
                                    JwtUtil jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.appUserDetailsService = appUserDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<UserDetails> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch (Exception ex) {
            throw new BadCredentialsException(ex.getMessage());
        }

        final UserDetails userDetails = appUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = "Bearer " + jwtTokenUtil.generateToken(userDetails);
        
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, jwt)
                .body(userDetails);
    }
}

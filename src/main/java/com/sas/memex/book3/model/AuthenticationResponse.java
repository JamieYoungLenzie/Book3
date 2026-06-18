
package com.sas.memex.book3.model;

public class AuthenticationResponse {
    
    private String jwt;

    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }
    
    /**
     * @return the jwt
     */
    public String getJwt() {
        return jwt;
    }
    
}

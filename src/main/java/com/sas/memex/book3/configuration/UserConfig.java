package com.sas.memex.book3.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

    @Value("${book3.user.folder}")
    private String userPath;
    
    @Bean
    public String userFolder() {
        return userPath;
    }    
}

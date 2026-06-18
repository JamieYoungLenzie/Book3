package com.sas.memex.book3.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataConfig {

    @Value("${book3.data.folder}")
    private String dataPath;
    
    @Bean
    public String dataFolder() {
        return dataPath;
    }
}

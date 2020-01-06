package org.zerock.day7.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import nz.net.ultraq.thymeleaf.LayoutDialect;

/**
 * LayoutConfig
 */
@Configuration
public class LayoutConfig {

    @Bean
    public LayoutDialect LayoutDialect() {
        return new LayoutDialect();
    }
    
}
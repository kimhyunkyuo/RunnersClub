package com.example.runnersclub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



    @Configuration
    public class PasswordEncoderConfig {

        @Bean
        public BCryptPasswordEncoder encodePassword() {
            return new BCryptPasswordEncoder();
        }

    }

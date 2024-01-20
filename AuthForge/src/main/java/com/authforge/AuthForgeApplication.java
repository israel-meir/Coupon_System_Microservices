package com.authforge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
public class AuthForgeApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthForgeApplication.class, args);
    }

}

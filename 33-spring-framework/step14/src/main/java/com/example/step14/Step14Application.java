package com.example.step14;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class Step14Application {

    public static void main(String[] args) {
        SpringApplication.run(Step14Application.class, args);
    }

}

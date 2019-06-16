package com.jaafar.data.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({ "com.jaafar" })
public class DataSearchEndpointApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataSearchEndpointApplication.class, args);
    }

}

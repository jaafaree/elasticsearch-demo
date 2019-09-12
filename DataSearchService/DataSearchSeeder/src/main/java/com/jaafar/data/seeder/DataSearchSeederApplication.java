package com.jaafar.data.seeder;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

@Slf4j
@ComponentScan("com.jaafar")
@SpringBootApplication
public class DataSearchSeederApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(DataSearchSeederApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
        log.info("================= Seeder application startup success =================");
    }

}

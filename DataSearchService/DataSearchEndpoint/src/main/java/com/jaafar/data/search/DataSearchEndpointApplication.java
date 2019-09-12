package com.jaafar.data.search;

import org.joda.time.DateTime;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@SpringBootApplication
@ComponentScan({ "com.jaafar" })
public class DataSearchEndpointApplication {

//    public static void main(String[] args) {
//        SpringApplication.run(DataSearchEndpointApplication.class, args);
//    }

    public static void main(String[] args) {
        try {
            new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").parse("2019-06-27T11:19:33");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DateTime dateTime = new DateTime("2018-06-23");
        int hourOfDay = dateTime.getHourOfDay();
    }

}

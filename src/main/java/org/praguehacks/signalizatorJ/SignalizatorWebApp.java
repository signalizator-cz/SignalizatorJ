package org.praguehacks.signalizatorJ;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

/**
 * Created by kucerajn on 13.6.2015.
 */

@SpringBootApplication
public class SignalizatorWebApp extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SignalizatorWebApp.class); //To change body of generated methods, choose Tools | Templates.
    }

    public static void main(String[] args) {
        SpringApplication.run(SignalizatorWebApp.class, args);
    }
}

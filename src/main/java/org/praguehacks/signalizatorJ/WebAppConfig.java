package org.praguehacks.signalizatorJ;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by kucerajn on 22.6.2015.
 */
@Configuration
@PropertySource("classpath:application.properties")
public class WebAppConfig extends WebMvcConfigurerAdapter {
    @Value("${email.from.username}")
    private String emailFromUsername;

    @Value("${email.from.password}")
    private String emailFromPassword;
    
    @Value("${email.from.smtpServer}")
    private String emailFromSmtpServer;
    
    @Value("${email.from.port}")
    private int emailFromPort;

    public String getEmailFromUsername() {
        return emailFromUsername;
    }

    public String getEmailFromPassword() {
        return emailFromPassword;
    }

    public String getEmailFromSmtpServer() {
        return emailFromSmtpServer;
    }

    public int getEmailFromPort() {
        return emailFromPort;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("*")
                .allowedOrigins("*")
                .allowedMethods("PUT", "POST")
                .allowedHeaders("Content-Type", "x-requested-with")
                .exposedHeaders("Content-Type", "x-requested-with")
                .allowCredentials(false).maxAge(3600);
    }
}

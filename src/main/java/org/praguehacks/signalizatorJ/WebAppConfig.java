package org.praguehacks.signalizatorJ;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by kucerajn on 22.6.2015.
 */
@Configuration
@PropertySource("classpath:application.properties")
public class WebAppConfig {
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
}

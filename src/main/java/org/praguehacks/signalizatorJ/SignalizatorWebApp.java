package org.praguehacks.signalizatorJ;


import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by kucerajn on 13.6.2015.
 */

@SpringBootApplication
public class SignalizatorWebApp extends SpringBootServletInitializer {
    
    @Bean
    ComboPooledDataSource c3p0DataSource() throws PropertyVetoException, IOException {
        Resource resource1 = new ClassPathResource("/application.properties");
        Properties appProps = PropertiesLoaderUtils.loadProperties(resource1);
        
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        
        dataSource.setDriverClass(appProps.getProperty("spring.datasource.driverClassName"));
        dataSource.setJdbcUrl(appProps.getProperty("spring.datasource.url"));
        dataSource.setUser(appProps.getProperty("spring.datasource.username"));
        dataSource.setPassword(appProps.getProperty("spring.datasource.password"));
        
        dataSource.setAcquireIncrement(Integer.valueOf(appProps.getProperty("c3p0.acquire_increment")));
        dataSource.setIdleConnectionTestPeriod(Integer.valueOf(appProps.getProperty("c3p0.idle_test_period")));
        dataSource.setMaxPoolSize(Integer.valueOf(appProps.getProperty("c3p0.max_size")));
        dataSource.setPreferredTestQuery(appProps.getProperty("c3p0.preferredTestQuery"));
        dataSource.setTestConnectionOnCheckin(Boolean.valueOf(appProps.getProperty("c3p0.testConnectionOnCheckin")));
        dataSource.setTestConnectionOnCheckout(Boolean.valueOf(appProps.getProperty("c3p0.testConnectionOnCheckout")));
        return dataSource;
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SignalizatorWebApp.class); //To change body of generated methods, choose Tools | Templates.
    }

    public static void main(String[] args) {
        SpringApplication.run(SignalizatorWebApp.class, args);
    }
}

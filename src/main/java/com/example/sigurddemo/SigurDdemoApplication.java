package com.example.sigurddemo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import javax.persistence.EntityManagerFactory;

@SpringBootApplication
@ConfigurationPropertiesScan
public class SigurDdemoApplication {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public static void main(String[] args) {
        SpringApplication.run(SigurDdemoApplication.class, args);
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource
                = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages/logMessages");
        return messageSource;
    }

    @Bean
    public Session getSession() {
        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();
        return session;
    }

}

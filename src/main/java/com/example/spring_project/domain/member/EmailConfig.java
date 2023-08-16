package com.example.spring_project.domain.member;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class EmailConfig {

    private final Environment env;

    public EmailConfig(Environment env) {
        this.env = env;
    }

//    @Value("${mail.smtp.port}")
//    private int port;
//    @Value("${mail.smtp.socketFactory.port}")
//    private int socketPort;
//    @Value("${mail.smtp.auth}")
//    private boolean auth;
//    @Value("${mail.smtp.starttls.enable}")
//    private boolean starttls;
//    @Value("${mail.smtp.starttls.required}")
//    private boolean startlls_required;
//    @Value("${mail.smtp.socketFactory.fallback}")
//    private boolean fallback;
//    @Value("${AdminMail.id}")
//    private String id;
//    @Value("${AdminMail.password}")
//    private String password;

    @Bean
    public JavaMailSender javaMailService() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(env.getProperty("spring.mail.host"));
        javaMailSender.setUsername(env.getProperty("spring.mail.username"));
        javaMailSender.setPassword(env.getProperty("spring.mail.password"));
        javaMailSender.setPort(Integer.parseInt(env.getProperty("spring.mail.port")));
        javaMailSender.setJavaMailProperties(getMailProperties());
        javaMailSender.setDefaultEncoding("UTF-8");
        return javaMailSender;
    }
    private Properties getMailProperties() {
        Properties pt = new Properties();
        pt.put("mail.smtp.socketFactory.port", Integer.parseInt(env.getProperty("spring.mail.properties.mail.smtp.socketFactory.port")));
        pt.put("mail.smtp.auth", env.getProperty("spring.mail.properties.mail.smtp.auth"));
        pt.put("mail.smtp.starttls.enable", env.getProperty("spring.mail.properties.mail.smtp.starttls.enable"));
        pt.put("mail.smtp.starttls.required", env.getProperty("spring.mail.properties.mail.smtp.starttls.required"));
        pt.put("mail.smtp.socketFactory.fallback", env.getProperty("spring.mail.properties.mail.smtp.socketFactory.fallback"));
        pt.put("mail.smtp.socketFactory.class", env.getProperty("spring.mail.properties.mail.smtp.socketFactory.class"));
        return pt;
    }
}

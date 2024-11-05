package com.ohgiraffers.collatime.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Repository;

import java.util.Properties;

@Configuration
public class EmailConfig {
    private String host = "smtp.gmail.com";
    private int port = 587;
    private String sendUsername = "gudjtr097@gmail.com";
    private String sendPassword = "lgtpxiwhexrhcoun";
    private boolean auth = true;
    private boolean starttlsEnable = true;
    private boolean starttlsRequired = true;
    private int connectionTimeout = 5000;
    private int timeout = 5000;
    private int writeTimeout = 5000;
//이메일에 대한 정보들




    @Bean
    public JavaMailSender javaMailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(sendUsername);
        mailSender.setPassword(sendPassword);
        mailSender.setDefaultEncoding("UTF-8");
        mailSender.setJavaMailProperties(getMailProperties());
//자바메일 센더에 기본정보들 입력
        return mailSender;
    }

    private Properties getMailProperties(){
        Properties properties = new Properties();
//  커스텀 한 내용들 프로퍼티로 입력
        properties.put("mail.smtp.auth", auth);
        properties.put("mail.smtp.starttls.enable", starttlsEnable);
        properties.put("mail.smtp.starttls.required", starttlsRequired);
        properties.put("mail.smtp.connectiontimeout", connectionTimeout);
        properties.put("mail.smtp.timeout", timeout);
        properties.put("mail.smtp.writetimeout", writeTimeout);

        return properties;
    }
}

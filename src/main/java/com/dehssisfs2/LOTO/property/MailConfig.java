package com.dehssisfs2.LOTO.property;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {

    @Autowired
    private Environment env;

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

//        mailSender.setHost("smtp.yandex.ru");
//        mailSender.setPort(465);
        MyConstants.setMyEmail(env.getProperty("spring.mail.username"));
        MyConstants.setMyPassword(env.getProperty("spring.mail.password"));
        MyConstants.setEmail1(env.getProperty("email_1").trim());
        MyConstants.setEmail2(env.getProperty("email_2").trim());
        MyConstants.setEmail3(env.getProperty("email_3").trim());


        mailSender.setUsername(MyConstants.MY_EMAIL);
        mailSender.setPassword(MyConstants.MY_PASSWORD);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.debug", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
//        System.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        return mailSender;
    }

}
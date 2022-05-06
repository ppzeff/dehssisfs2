package com.dehssisfs2.tinkoff.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class BotConfig {

    // Имя бота заданное при регистрации
    @Value("${botUserName}")
    String botUserName;

    // Токен полученный при регистрации
    @Value("${telegramBotToken}")
    String token;

    public String getBotUserName() {
        return botUserName;
    }

    public void setBotUserName(String botUserName) {
        this.botUserName = botUserName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
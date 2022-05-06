package com.dehssisfs2.tinkoff.config;

import com.dehssisfs2.tinkoff.service.BotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Component
public class BotInitializer {
    @Autowired
    BotService botService;

    @EventListener({ContextRefreshedEvent.class})
    public void Init() throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        try {
            telegramBotsApi.registerBot(botService);
        } catch (TelegramApiRequestException e) {
//            log.error(exceptionStackTraceToString(e));
        }
    }

}
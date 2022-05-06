package com.dehssisfs2.tinkoff.service;

import com.dehssisfs2.tinkoff.config.BotConfig;
import com.dehssisfs2.tinkoff.model.Model;
import com.dehssisfs2.tinkoff.model.TelegramUser;
import com.dehssisfs2.tinkoff.model.UserActions;
import com.dehssisfs2.tinkoff.repo.TelegramUserRepo;
import com.dehssisfs2.tinkoff.repo.UserActionsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.util.Date;

import static javax.management.timer.Timer.*;


/**
 * Касс является основным пулом взаимодействия с Telegram
 * получение, обработка, отправка сообщений
 */
@Component
public class BotService extends TelegramLongPollingBot {

    final
    BotConfig config;

    @Autowired
    TelegramUserRepo telegramUserRepo;

    @Autowired
    MainService mainService;

    @Autowired
    UserActionsRepo userActionsRepo;

    @Autowired
    MakeGraphServiceImp makeGraphService;

    public BotService(BotConfig config) {

        this.config = config;
    }

    public void onUpdateReceived(Update update) {
        update.getUpdateId();
        SendMessage.SendMessageBuilder messageBuilder = SendMessage.builder();
        String messageText;
        String chatId;
        if(update.hasCallbackQuery()){
            System.out.println(update.getCallbackQuery().getData());
            return;
//            try {
//                execute(new SendMessage().setText(
//                        update.getCallbackQuery().getData())
//                        .setChatId(update.getCallbackQuery().getMessage().getChatId()));
//            } catch (TelegramApiException e) {
//                e.printStackTrace();
//            }
        }
        if (update.getMessage() != null && update.getMessage().hasText()) {

            TelegramUser telegramUser = new TelegramUser(
                    update.getMessage().getChatId(),
                    update.getMessage().getFrom().getFirstName(),
                    update.getMessage().getFrom().getLastName(),
                    update.getMessage().getFrom().getUserName(),
                    update.getMessage().getFrom().getIsBot(),
                    new Date());

            System.out.println(telegramUser.toString());
            if (telegramUserRepo.findTelegramUserByUserId(telegramUser.getUserId()) == null) {
                telegramUserRepo.save(telegramUser);
            }

            chatId = update.getMessage().getChatId().toString();
            messageBuilder.chatId(chatId);
            messageBuilder.parseMode(ParseMode.HTML);
            messageText = update.getMessage().getText();

//          логирование действия пользователя
            UserActions userActions = new UserActions();
            userActions.setUserId(telegramUser.getUserId());
            userActions.setMessage(messageText);
            userActions.setDtQuery(new Date());
            userActionsRepo.save(userActions);

        }

        else {
            chatId = update.getChannelPost().getChatId().toString();
            messageBuilder.chatId(chatId);
            messageText = update.getChannelPost().getText();
        }

        if (messageText.contains("/hello")) {
            messageBuilder.text("Привет");
            try {
                execute(messageBuilder.build());
            } catch (TelegramApiException e) {
                System.out.println(e.toString());
            }
        }


        if (messageText.contains("/month840")) {

            long start = System.currentTimeMillis();
            String fileName = makeGraphService.makeGraph(840,ONE_DAY*30)+".jpg";
            SendPhoto photo = new SendPhoto();
            photo.setChatId(chatId);
            photo.setPhoto(new InputFile(new File(fileName)));
            photo.setCaption("График сформирован за: " +  (System.currentTimeMillis()-start) + "милисекунд");
            try {
                execute(photo);
                new File(fileName).delete();
//                execute(messageBuilder.build());
            } catch (TelegramApiException e) {
                System.out.println(e.toString());
            }
        }


        if (messageText.contains("/week840")) {

            long start = System.currentTimeMillis();
            String fileName = makeGraphService.makeGraph(840,ONE_WEEK)+".jpg";
            SendPhoto photo = new SendPhoto();
            photo.setChatId(chatId);
            photo.setPhoto(new InputFile(new File(fileName)));
            photo.setCaption("График сформирован за: " +  (System.currentTimeMillis()-start) + "милисекунд");
            try {
                execute(photo);
                new File(fileName).delete();
//                execute(messageBuilder.build());
            } catch (TelegramApiException e) {
                System.out.println(e.toString());
            }
        }

        if (messageText.contains("/day840")) {

            long start = System.currentTimeMillis();
            String fileName = makeGraphService.makeGraph(840,ONE_DAY)+".jpg";
            SendPhoto photo = new SendPhoto();
            photo.setChatId(chatId);
            photo.setPhoto(new InputFile(new File(fileName)));
            photo.setCaption("График сформирован за: " +  (System.currentTimeMillis()-start) + "милисекунд");
            try {
                execute(photo);
                new File(fileName).delete();
//                execute(messageBuilder.build());
            } catch (TelegramApiException e) {
                System.out.println(e.toString());
            }
        }

        if (messageText.contains("/hour840")) {

            long start = System.currentTimeMillis();
            String fileName = makeGraphService.makeGraph(840,ONE_HOUR)+".jpg";
            SendPhoto photo = new SendPhoto();
            photo.setChatId(chatId);
            photo.setPhoto(new InputFile(new File(fileName)));
            photo.setCaption("График сформирован за: " +  (System.currentTimeMillis()-start) + "милисекунд");
            try {
                execute(photo);
                new File(fileName).delete();
//                execute(messageBuilder.build());
            } catch (TelegramApiException e) {
                System.out.println(e.toString());
            }

        }

        if (messageText.contains("/all840")) {

            long start = System.currentTimeMillis();
            long period = start - 1638053234380L;
            String fileName = makeGraphService.makeGraph(840,period)+".jpg";
            SendPhoto photo = new SendPhoto();
            photo.setChatId(chatId);
            photo.setPhoto(new InputFile(new File(fileName)));
            photo.setCaption("График сформирован за: " +  (System.currentTimeMillis()-start) + "милисекунд");
            try {
                execute(photo);
                new File(fileName).delete();
//                execute(messageBuilder.build());
            } catch (TelegramApiException e) {
                System.out.println(e.toString());
            }

        }

        if (messageText.contains("/rates")) {
            Model model = mainService.cashModelMap.get(840);
            String rates = "Текущий курс: \n";
            rates = rates.concat(model.toTeleString());
            rates = rates.concat("\n");

            model = mainService.cashModelMap.get(978);
            rates = rates.concat(model.toTeleString());
            rates = rates.concat("\n");

            model = mainService.cashModelMap.get(826);
            rates = rates.concat(model.toTeleString());

            messageBuilder.text(rates);
//            messageBuilder.replyMarkup(new KeyboardMarkup().kb());

            try {
                execute(messageBuilder.build());
            } catch (TelegramApiException e) {
                System.out.println(e.toString());
            }
        }

        if (messageText.contains("/chartId")) {
            messageBuilder.text("ID Канала : <b>" + chatId + "</b>");
            try {
//                messageBuilder.
                execute(messageBuilder.build());
            } catch (TelegramApiException e) {
                System.out.println(e.toString());
            }
        }
    }


    @Override
    public String getBotUsername() {
        return config.getBotUserName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }
}

//rates - получить текущие курсы
//graph - получить график
//hour840 - график $ за час
//day840 - график $ за день
//week840 - график $ за неделю
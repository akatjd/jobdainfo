package com.example.jobdainfo;

import com.example.jobdainfo.telegram.handlers.TelegramBotHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
@EnableScheduling
public class JobdainfoApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(JobdainfoApplication.class, args);

        // 텔레그램 봇 등록
        try {
            TelegramBotHandler botHandler = context.getBean(TelegramBotHandler.class);
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(botHandler);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}

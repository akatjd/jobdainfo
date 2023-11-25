package com.example.jobdainfo.telegram.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@RestController
@Controller
@Slf4j
@Component
@RequestMapping("/telegram")
public class TelegramController extends TelegramLongPollingBot {

//    private final String botId = "-jobdabot";
//    private final String botToken = "6434259180:AAGEAFCVoMEOFhmD6yqq8qmMCR6qRFFy3So";
//    private final String chatId = "-1002087272871";

    @Value("${telegram.bot.id}")
    public String botId;

    @Value("${telegram.bot.token}")
    public String botToken;

    @Value("${telegram.chat.id}")
    public String chatId;

    // 봇의 사용자 이름 반환
    @Override
    public String getBotUsername() {
        return botId;
    }

    // 봇의 토큰 반환
    @Override
    public String getBotToken() {
        return botToken;
    }

    // 메시지를 받았을 때의 처리
    @Override
    public void onUpdateReceived(Update update) {
        // 필요한 경우 메시지 수신 처리 로직을 구현할 수 있습니다.
    }

    // 채팅방에 메시지 보내기
    public void sendMessageToChat(String chatId, String messageText) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("test");
        try {
            execute(message); // 메시지 전송
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @GetMapping(value = "/sendMsg")
    public String senMsg() {
        String msg = "test";

        log.debug("들어옴");
        
//        String chatId = "jobdabot"; // 여기에 채팅방 ID를 입력하세요
        String messageText = "전송할 메시지를 입력하세요";

        sendMessageToChat(chatId, messageText);

        return msg;
    }

}

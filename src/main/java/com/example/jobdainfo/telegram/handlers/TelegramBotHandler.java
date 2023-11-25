package com.example.jobdainfo.telegram.handlers;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TelegramBotHandler extends TelegramLongPollingBot {
    // 텔레그램 봇 토큰 설정
    @Value("${telegram.bot.id}")
    public String botId;

    @Value("${telegram.bot.token}")
    public String botToken;

    @Value("${telegram.chat.id}")
    public String chatId;

    // 텔레그램 봇 이름 설정
    @Override
    public String getBotUsername() {
        return botId;
    }

    // 텔레그램에서 메시지가 오면 이 메소드가 호출됨
    @Override
    public void onUpdateReceived(Update update) {
        System.out.println(update);
        System.out.println(update.getChannelPost().getText());

        if (update.hasChannelPost() && update.getChannelPost().hasText()) {
            String userMessage = update.getChannelPost().getText();

            // 사용자 입력을 WAS로 전송
            sendToWAS(userMessage);

            // WAS로부터 받은 응답 (if any) 처리 후, 사용자에게 응답 보내기
            // String responseFromWAS = receiveFromWAS();
            // sendTelegramMessage(update.getMessage().getChatId(), responseFromWAS);
        }
    }

    // WAS로 사용자 입력 전송
    private void sendToWAS(String userMessage) {
        // HTTP 요청 등을 통해 WAS로 전송
        // 예: Spring RestTemplate 또는 HttpClient를 사용하여 WAS로 전송

        sendTelegramMessage("오냐 ~");
    }

    // 텔레그램 메시지 전송
    public void sendTelegramMessage(String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
}

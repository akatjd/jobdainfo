package com.example.jobdainfo.telegram.schedulers;

import com.example.jobdainfo.telegram.handlers.TelegramBotHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CoinPriceScheduler {

    private final TelegramBotHandler telegramBotHandler;

    @Autowired
    public CoinPriceScheduler(TelegramBotHandler telegramBotHandler) {
        this.telegramBotHandler = telegramBotHandler;
    }

    @Scheduled(fixedRate = 60000) // 1분(60,000밀리초)마다 실행
    public void fetchBitcoinPrice() {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "https://api.binance.com/api/v3/ticker/price?symbol=BTCUSDT";

        // Binance API 호출
        String response = restTemplate.getForObject(apiUrl, String.class);

        // 응답 파싱
        // 여기서는 JSON 파싱 등을 통해 원하는 데이터를 가져올 수 있습니다.
        // 예시로 전체 응답을 출력하는 코드입니다.
        System.out.println("Bitcoin price response: " + response);

        telegramBotHandler.sendTelegramMessage(response);
    }
}

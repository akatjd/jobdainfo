package com.example.jobdainfo;

import com.binance.connector.client.SpotClient;
import com.binance.connector.client.impl.SpotClientImpl;

import java.util.LinkedHashMap;
import java.util.Map;

public class Klines {
    private Klines() {
    }

    public static void main(String[] args) {
        Map<String, Object> parameters = new LinkedHashMap<>();

        SpotClient client = new SpotClientImpl(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY);

        parameters.put("symbol", "BNBUSDT");
        parameters.put("interval", "1m");

        String result = client.createMarket().klines(parameters);
        System.out.println(result);
    }
}

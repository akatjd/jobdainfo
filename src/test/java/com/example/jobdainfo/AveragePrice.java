package com.example.jobdainfo;

import com.binance.connector.client.SpotClient;
import com.binance.connector.client.impl.SpotClientImpl;

import java.util.LinkedHashMap;
import java.util.Map;

public class AveragePrice {
    private AveragePrice() {
    }

    public static void main(String[] args) {
        Map<String, Object> parameters = new LinkedHashMap<>();

        SpotClient client = new SpotClientImpl();

        parameters.put("symbol", "BNBUSDT");
        String result = client.createMarket().averagePrice(parameters);
        System.out.println(result);
    }
}

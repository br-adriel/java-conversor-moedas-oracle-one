package br.com.alura.conversormoedas.services;

import java.net.http.HttpClient;

public class ExchangeRateService {
    private String apiKey;
    private HttpClient httpClient;

    ExchangeRateService(String apiKey) {
        this.apiKey = apiKey;
        this.httpClient =  HttpClient.newHttpClient();
    }
}

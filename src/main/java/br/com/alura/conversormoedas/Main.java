package br.com.alura.conversormoedas;

import br.com.alura.conversormoedas.services.ExchangeRateService;
import io.github.cdimascio.dotenv.Dotenv;

public class Main {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        String apiKey = dotenv.get("EXCHANGE_RATE_API_KEY");

        ExchangeRateService exchangeRateService = new ExchangeRateService(apiKey);
    }
}

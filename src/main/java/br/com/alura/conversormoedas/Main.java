package br.com.alura.conversormoedas;

import br.com.alura.conversormoedas.clis.ExchangeRateCli;
import br.com.alura.conversormoedas.dtos.ConversionResultDto;
import br.com.alura.conversormoedas.enums.Currency;
import br.com.alura.conversormoedas.services.ExchangeRateService;
import io.github.cdimascio.dotenv.Dotenv;

public class Main {
    public static void main(String[] args) {
        int option = 0;
        double value = 0;
        Currency baseCurrency, targetCurrency;
        ConversionResultDto result;


        Dotenv dotenv = Dotenv.load();
        String apiKey = dotenv.get("EXCHANGE_RATE_API_KEY");

        ExchangeRateService exchangeRateService = new ExchangeRateService(apiKey);

        while (option != 8) {
            ExchangeRateCli.showMenu();
            option = ExchangeRateCli.getUserMenuInput();

            if (option < 7) {
                value = ExchangeRateCli.getUserValueInput();
            }

            switch(option) {
                case 1:
                    baseCurrency = Currency.USD;
                    targetCurrency = Currency.ARS;
                    break;
                case 2:
                    baseCurrency = Currency.ARS;
                    targetCurrency = Currency.USD;
                    break;
                case 3:
                    baseCurrency = Currency.USD;
                    targetCurrency = Currency.BRL;
                    break;
                case 4:
                    baseCurrency = Currency.BRL;
                    targetCurrency = Currency.USD;
                    break;
                case 5:
                    baseCurrency = Currency.USD;
                    targetCurrency = Currency.COP;
                    break;
                case 6:
                    baseCurrency = Currency.COP;
                    targetCurrency = Currency.USD;
                    break;
                case 7:
                    baseCurrency = ExchangeRateCli.getUserBaseCurrencyInput();
                    targetCurrency = ExchangeRateCli.getUserTargetCurrencyInput();
                    value = ExchangeRateCli.getUserValueInput();
                    break;
                default:
                    continue;
            }

            System.out.println("\nConvertendo...");
            result = exchangeRateService.convertValue(baseCurrency, targetCurrency, value);
            ExchangeRateCli.showConversionResult(value, result);
        }
    }
}

package br.com.alura.conversormoedas;

import br.com.alura.conversormoedas.clis.ExchangeRateCli;
import br.com.alura.conversormoedas.dtos.ConversionResultDto;
import br.com.alura.conversormoedas.enums.Currency;
import br.com.alura.conversormoedas.loggers.ExchangeRateLogger;
import br.com.alura.conversormoedas.services.AppUserHistoryService;
import br.com.alura.conversormoedas.services.ExchangeRateService;
import io.github.cdimascio.dotenv.Dotenv;

public class Main {
    public static void main(String[] args) {
        int option = 0;
        double value = 0;
        Currency baseCurrency, targetCurrency;
        ConversionResultDto result;
        AppUserHistoryService historyService = new AppUserHistoryService();

        Dotenv dotenv = Dotenv.load();
        String apiKey = dotenv.get("EXCHANGE_RATE_API_KEY");

        ExchangeRateService exchangeRateService = new ExchangeRateService(apiKey);

        while (option != 9) {
            ExchangeRateCli.showMenu();
            option = ExchangeRateCli.getUserMenuInput();

            ExchangeRateLogger.logUserOptionInput("" + option);

            if (option < 7) {
                value = ExchangeRateCli.getUserValueInput();
                ExchangeRateLogger.logUserValueInput("" + value);
            }

            switch (option) {
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
                    ExchangeRateLogger.logUserCurrencyInput(baseCurrency.name());

                    targetCurrency = ExchangeRateCli.getUserTargetCurrencyInput();
                    ExchangeRateLogger.logUserCurrencyInput(targetCurrency.name());

                    value = ExchangeRateCli.getUserValueInput();
                    ExchangeRateLogger.logUserValueInput("" + value);
                    break;
                case 8:
                    ExchangeRateCli.showHistory(historyService.getHistory());
                    continue;
                default:
                    continue;
            }

            System.out.println("\nConvertendo...");
            result = exchangeRateService.convertValue(baseCurrency, targetCurrency, value);
            ExchangeRateLogger.logSuccessfulConversion(result);
            historyService.addToHistory(value, result);
            ExchangeRateCli.showConversionResult(value, result);
        }
    }
}

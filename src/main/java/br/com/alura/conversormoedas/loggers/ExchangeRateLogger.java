package br.com.alura.conversormoedas.loggers;

import br.com.alura.conversormoedas.dtos.ConversionResultDto;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExchangeRateLogger {
    public static void logSuccessfulConversion(ConversionResultDto result) {
        LocalDateTime now = LocalDateTime.now();
        try {
            FileWriter fw = new FileWriter("exchange-rate-usage.log", true);
            fw.append(now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                    .append(" SUCCESSFUL CONVERSION FROM ")
                    .append(result.baseCode())
                    .append(" TO ")
                    .append(result.targetCode())
                    .append(" WITH ")
                    .append(String.valueOf(result.conversionRate()))
                    .append(" RATE")
                    .append("\n");
            fw.close();
        } catch (IOException ignored) {
        }
    }

    public static void logUserOptionInput(String input) {
        LocalDateTime now = LocalDateTime.now();
        try {
            FileWriter fw = new FileWriter("exchange-rate-usage.log", true);
            fw.append(now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                    .append(" USER OPTION INPUT \"")
                    .append(input)
                    .append("\"\n");
            fw.close();
        } catch (IOException ignored) {
        }
    }

    public static void logUserValueInput(String input) {
        LocalDateTime now = LocalDateTime.now();
        try {
            FileWriter fw = new FileWriter("exchange-rate-usage.log", true);
            fw.append(now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                    .append(" USER VALUE INPUT \"")
                    .append(input)
                    .append("\"\n");
            fw.close();
        } catch (IOException ignored) {
        }
    }

    public static void logUserCurrencyInput(String input) {
        LocalDateTime now = LocalDateTime.now();
        try {
            FileWriter fw = new FileWriter("exchange-rate-usage.log", true);
            fw.append(now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                    .append(" USER CURRENCY INPUT \"")
                    .append(input)
                    .append("\"\n");
            fw.close();
        } catch (IOException ignored) {
        }
    }
}

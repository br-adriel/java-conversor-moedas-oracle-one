package br.com.alura.conversormoedas.models;

import br.com.alura.conversormoedas.enums.Currency;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Conversion {
    private final LocalDateTime time;
    private final double baseValue;
    private final double targetValue;
    private final Currency baseCurrency;
    private final Currency targetCurrency;

    public Conversion(
            String time,
            String baseValue,
            String targetValue,
            String baseCurrency,
            String targetCurrency
    ) {
        this.time = LocalDateTime.parse(time);
        this.baseValue = Double.parseDouble(baseValue);
        this.targetValue = Double.parseDouble(targetValue);
        this.baseCurrency = Currency.valueOf(baseCurrency);
        this.targetCurrency = Currency.valueOf(targetCurrency);
    }

    public Conversion(
            LocalDateTime time,
            double baseValue,
            double targetValue,
            Currency baseCurrency,
            Currency targetCurrency
    ) {
        this.time = time;
        this.baseValue = baseValue;
        this.targetValue = targetValue;
        this.baseCurrency = baseCurrency;
        this.targetCurrency = targetCurrency;
    }

    public Conversion(String[] data) {
        this.time = LocalDateTime.parse(data[0]);
        this.baseValue = Double.parseDouble(data[1]);
        this.targetValue = Double.parseDouble(data[2]);
        this.baseCurrency = Currency.valueOf(data[3]);
        this.targetCurrency = Currency.valueOf(data[4]);
    }

    public String toCsvString() {
        return this.time.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + ","
               + this.baseValue + "," + this.targetValue + ","
               + this.baseCurrency.name() + "," + this.targetCurrency.name()
               + "\n";
    }

    public LocalDateTime getTime() {
        return time;
    }

    public double getBaseValue() {
        return baseValue;
    }

    public double getTargetValue() {
        return targetValue;
    }

    public Currency getBaseCurrency() {
        return baseCurrency;
    }

    public Currency getTargetCurrency() {
        return targetCurrency;
    }
}

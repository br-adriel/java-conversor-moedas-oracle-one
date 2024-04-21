package br.com.alura.conversormoedas.clis;

import br.com.alura.conversormoedas.dtos.ConversionResultDto;
import br.com.alura.conversormoedas.enums.Currency;
import br.com.alura.conversormoedas.models.Conversion;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class ExchangeRateCli {
    private static final Scanner sc = new Scanner(System.in);


    public static void showMenu() {
        System.out.println("*************************************************");
        System.out.println("Bem vindo(a) ao conversor de moedas!\n");
        System.out.println("1) Dólar => Peso Argentino");
        System.out.println("2) Peso Argentino => Dólar");
        System.out.println("3) Dólar => Real Brasileiro");
        System.out.println("4) Real brasileiro => Dólar");
        System.out.println("5) Dólar => Peso Colombiano");
        System.out.println("6) Peso Colombiano => Dólar");
        System.out.println("7) Customizar seleção");
        System.out.println("8) Histórico");
        System.out.println("9) Sair");
    }

    public static int getUserMenuInput(int min, int max) {
        int option = 0;
        while (option < min || option > max) {
            try {
                System.out.println("\nEscolha uma opção:");
                System.out.print(">> ");
                option = Integer.parseInt(sc.nextLine());

                if (option < min || option > max) {
                    System.out.println("\n[!] Opção inválida");
                }
            } catch (Exception e) {
                System.out.println("\n[!] Opção inválida");
            }
        }
        return option;
    }

    public static int getUserMenuInput() {
        return getUserMenuInput(1, 9);
    }

    public static double getUserValueInput() {
        while (true) {
            try {
                System.out.println("\nDigite o valor a ser convertido:");
                double value = sc.nextDouble();
                sc.nextLine();
                return value >= 0 ? value : -1 * value;
            } catch (Exception e) {
                System.out.println("\n[!] Valor inválido");
            }
        }

    }

    public static Currency getUserBaseCurrencyInput() {
        return getUserCurrencyInput("\nDigite o código da moeda de origem:");
    }

    public static Currency getUserTargetCurrencyInput() {
        return getUserCurrencyInput("\nDigite o código da moeda de destino:");
    }

    private static Currency getUserCurrencyInput(String message) {
        while (true) {
            System.out.println(message);
            String baseCurrencyCode = sc.nextLine().strip();

            try {
                return Currency.valueOf(baseCurrencyCode);
            } catch (IllegalArgumentException e) {
                System.out.println("\n[!] Essa moeda não é suportada pelo sistema");
            }
        }
    }

    public static void showConversionResult(double initialValue, ConversionResultDto result) {
        System.out.println("\nConversão concluída *****************************");
        System.out.println("Taxa de conversão: " + result.conversionRate());
        System.out.printf("Valor original: %.2f %s\n", initialValue, result.baseCode());
        System.out.printf("Valor convertido: %.2f %s\n", result.conversionResult(), result.targetCode());
        System.out.println("*************************************************\n");
    }

    public static void showHistory(List<Conversion> history) {
        System.out.println("\n*************************************************");
        System.out.println("Histórico de conversões");
        System.out.println("*************************************************\n");

        if (history.isEmpty())
            System.out.println("Histórico vazio ---------------------------------");

        for (Conversion c : history) {
            System.out.println("Data/Horário: " + c.getTime().format(DateTimeFormatter.ofPattern("dd/MM/YYYY HH:mm:ss")));
            System.out.println("Valor base: " + c.getBaseValue() + " " + c.getBaseCurrency());
            System.out.println("Valor convertido: " + c.getTargetValue() + " " + c.getTargetCurrency() + "\n");
        }
    }
}

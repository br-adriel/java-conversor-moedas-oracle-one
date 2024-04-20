package br.com.alura.conversormoedas.dtos;

public record ConversionResultDto(
        String baseCode,
        String targetCode,
        double conversionRate,
        double conversionResult
) {
}

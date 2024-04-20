package br.com.alura.conversormoedas.services;

import br.com.alura.conversormoedas.dtos.ConversionResultDto;
import br.com.alura.conversormoedas.enums.Currency;
import br.com.alura.conversormoedas.exceptions.BadRequestException;
import br.com.alura.conversormoedas.exceptions.ForbiddenException;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ExchangeRateService {
    private HttpClient httpClient;
    private String baseUrl;


    public ExchangeRateService(String apiKey) {
        this.httpClient = HttpClient.newHttpClient();
        this.baseUrl = "https://v6.exchangerate-api.com/v6/" + apiKey + "/";
    }

    public ConversionResultDto convertValue(
            Currency origin,
            Currency target,
            double value
    ) {
        String url = this.baseUrl + "pair/" + origin.toString() + "/" + target.toString() + "/" + value;
        try {
            URI uri = new URI(url);
            HttpRequest req = HttpRequest.newBuilder().uri(uri).build();
            HttpResponse<String> response = this.httpClient.send(
                    req, HttpResponse.BodyHandlers.ofString()
            );

            switch (response.statusCode()) {
                case 400:
                    throw new BadRequestException("Moedas inválidas");
                case 403:
                    throw new ForbiddenException("Chave de API inválida");
            }

            Gson gson = new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                    .create();
            return gson.fromJson(response.body(), ConversionResultDto.class);
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

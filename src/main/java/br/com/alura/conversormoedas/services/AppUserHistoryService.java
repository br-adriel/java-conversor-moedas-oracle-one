package br.com.alura.conversormoedas.services;

import br.com.alura.conversormoedas.dtos.ConversionResultDto;
import br.com.alura.conversormoedas.enums.Currency;
import br.com.alura.conversormoedas.models.Conversion;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class AppUserHistoryService {
    private final List<Conversion> history;

    public AppUserHistoryService() {
        this.history = new LinkedList<>();
        try {
            Scanner historyFile = new Scanner(new File("history.csv"));

            while (historyFile.hasNextLine()) {
                String line = historyFile.nextLine();
                String[] columns = line.split(",");
                Conversion conversion = new Conversion(columns);
                this.history.add(conversion);
            }
            historyFile.close();
        } catch (FileNotFoundException ignore) {
        }
    }

    public void addToHistory(double baseValue, ConversionResultDto result) {
        Conversion conversion = new Conversion(
                LocalDateTime.now(),
                baseValue,
                result.conversionResult(),
                Currency.valueOf(result.baseCode()),
                Currency.valueOf(result.targetCode())
        );

        int limit = 5;
        if (history.size() == limit) history.remove(0);
        history.add(conversion);

        StringBuilder resultingCsv = new StringBuilder();
        history.forEach((c) -> resultingCsv.append(c.toCsvString()));

        try {
            FileWriter fw = new FileWriter("history.csv");
            fw.write(resultingCsv.toString());
            fw.close();
        } catch (IOException ignore) {
        }
    }

    public List<Conversion> getHistory() {
        return history;
    }
}

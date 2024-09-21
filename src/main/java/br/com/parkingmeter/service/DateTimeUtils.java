package br.com.parkingmeter.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {

    // Método para formatar LocalDateTime em String
    public static String formatarDataHora(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return dateTime.format(formatter);
    }

    // Método para obter a data e hora atual
    public static LocalDateTime agora() {
        return LocalDateTime.now();
    }

    // Método para converter uma string em LocalDateTime
    public static LocalDateTime parseDataHora(String dataHora) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return LocalDateTime.parse(dataHora, formatter);
    }
}

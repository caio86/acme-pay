package br.com.acmepay.application.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * FormatDate
 */
public class FormatDate {

    public static String formatedDate() {
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return date.format(formatters);
    };
}

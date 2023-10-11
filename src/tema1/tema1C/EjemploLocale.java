package tema1.tema1C;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;


// Este ejemplo muestra como utilizar las clases
// para mostrar fechas en distintos idiomas/locales.

public class EjemploLocale {

    public static void main(String[] args) {
        // Obtenemos la fecha/tiempo actual
        ZonedDateTime now = ZonedDateTime.now();

        // Escribimos por consola la fecha con formato de texto largo en el locale actual
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
        System.out.println(formatter.format(now));

        // Escribimos por consola la fecha con formato de texto largo en con locale japonés.
        DateTimeFormatter japaneseFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(Locale.JAPAN);
        System.out.println(japaneseFormatter.format(now));

        // Escribimos por consola la fecha con formato de texto largo en el locale italiano.
        DateTimeFormatter italianFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(Locale.ITALY);
        System.out.println(italianFormatter.format(now));
    }
}
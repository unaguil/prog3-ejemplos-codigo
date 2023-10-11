package tema1.tema1C;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

// Este ejemplo muestra como utilizar el nuevo API para
// la gestión de fechas y tiempos.

public class EjemploTime {

    public static void main(String[] args) {
        // Obtenemos la fecha/tiempo actual
        ZonedDateTime now = ZonedDateTime.now();

        // Escribimos por consola la fecha con la localización
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss ZZ");
        System.out.println("Fecha/hora/zona: " + formatter.format(now));

        // Sumar 10 a la fecha actual
        ZonedDateTime futureTime = now.plusYears(10);
        System.out.println("Fecha/hora/zona: " + formatter.format(futureTime));

        // Convertir un string en formato dd-MM-YY a una fecha interna
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate newDate = LocalDate.parse("22-09-2020", dateFormatter);
        System.out.println("Fecha parseada: " + newDate);
    }
}
package tema1.tema1C;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;


// Este programa escribe 'Hola' de una vez en un fichero.
// Para ello utiliza un BufferedWriter intermedio.
// Esta es la manera recomendaba de gestionar los recursos a 
// partir de Java 7 gracias a la interfaz Closeable.

public class EjemploSalidaWriter {

    public static void main(String[] args) {
        // Ahora construimos varios objetos conectados para escribir
        // líneas de texto directamente a un stream de salida a fichero.

        try (BufferedWriter buffWriter = new BufferedWriter(new FileWriter("output.txt"))) {

           buffWriter.write("Hola");

        } catch (FileNotFoundException e) {
            // Si no se encuentra el fichero al intentar abrirlo
            System.out.println("No se pudo encontrar el fichero");
        } catch (IOException e) {
            // Si hay problemas al escribir en el fichero.
            System.out.println("Hay problemas al escribir");
        }
    }
}
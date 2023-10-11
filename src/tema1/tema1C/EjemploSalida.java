package tema1.tema1C;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


// Este programa escribe 'Hola' caracter a caracter en un fichero.
// Esta es la manera recomendaba de gestionar los recursos a 
// partir de Java 7 gracias a la interfaz Closeable.

public class EjemploSalida {

    public static void main(String[] args) {
        // Creamos el objeto FileOutputStream usando el constructor 
        // que recibe la ruta al fichero de salida.

        try (FileOutputStream fos = new FileOutputStream("output.txt")) {

            fos.write('H');
            fos.write('o');
            fos.write('l');
            fos.write('a');

        } catch (FileNotFoundException e) {
            // Si no se encuentra el fichero al intentar abrirlo
            System.out.println("No se pudo encontrar el fichero");
        } catch (IOException e) {
            // Si hay problemas al escribir en el fichero.
            System.out.println("Hay problemas al escribir");
        }
    }
}
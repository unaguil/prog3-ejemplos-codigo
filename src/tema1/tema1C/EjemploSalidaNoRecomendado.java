package tema1.tema1C;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


// Este programa escribe 'Hola' caracter a caracter en un fichero.

/////////////////
// ¡Atención! No se recomienda utilizar esta gestión
// de recursos a partir de Java 7. Se debe utilizar
// try-with-resources: ver EjemploSalida.java
/////////////////

public class EjemploSalidaNoRecomendado {

    public static void main(String[] args) {
        // Creamos el objeto FileOutputStream usando el constructor 
        // que recibe la ruta al fichero de salida.

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("output.txt");

            fos.write('H');
            fos.write('o');
            fos.write('l');
            fos.write('a');

        } catch (FileNotFoundException e) {
            // Si no se encuentra el fichero al intentar abrirlo
            System.out.println("No se pudo encontrar el fichero");
        } catch (IOException e) {
            // Si hay problemas al escribir en el fichero.
        } finally {
            // En cualquier caso, tanto si hay error como si no hay,
            // se comprueba si el stream está abierto y se cierra
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    //
                }
            }
        }
    }
}
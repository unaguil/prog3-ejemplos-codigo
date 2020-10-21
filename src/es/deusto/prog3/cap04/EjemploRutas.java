package es.deusto.prog3.cap04;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.Scanner;

// Este programa muestra el uso de las clases para la gestión
// de rutas, directorios y ficheros.

public class EjemploRutas {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Introduce una ruta: ");
            
            try {
                // Usamos la ruta introducida por el usuario
                Path path = Path.of(scanner.nextLine());
                
                // Podemos comprobar propiedades de la ruta
                if (path.isAbsolute()) {
                    System.out.println(String.format("'%s' es una ruta absoluta", path));
                } else {
                    System.out.println(String.format("'%s' es una ruta relativa"));
                }

                // Convertirla a un objeto fichero para obtener otros datos
                File file = path.toFile();

                if (!file.isDirectory()) {
                    // Si es un fichero lo leemos en modo texto
                    System.out.println("Hace referencia a un fichero");
                    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                        String line = null;
                        while ((line = reader.readLine()) != null) {
                            System.out.println(line);
                        }
                    } catch (IOException e) {
                        System.out.println("Problema accediendo al fichero. " + e.getMessage());
                    }
                } else {
                    System.out.println("Hace referencia a un directorio:");

                    for (File child : file.listFiles()) {
                        System.out.println(String.format("%s \t %s \t %d", child.getName(), child.isDirectory(), child.length()));
                    }
                }

            } catch (InvalidPathException e) {
                System.out.println("Error. No se ha introducidad una ruta válida.");
            }

        }
    }
}
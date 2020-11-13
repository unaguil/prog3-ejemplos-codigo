package es.deusto.prog3.cap05.estructuras;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


// Ejemplo de programa utilizado para medir para medir
// la eficiencia de las distintas implementaciones de la
// interfaz List en Java.

public class Eficiencia {

    public static long MIN = 4000; // 64000
    public static long MAX = 128000; // 16384000
    
    public static void main(String[] args) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.csv"))) {
            writer.write("n, ms\n");

            for (long n = MIN; n <= MAX; n *= 2) {
                List<String> arrayList = new ArrayList<String>(); // Cambiar la implementación
                // Añadir datos si son necesarios, por ejemplo para probar el borrado.
                
                long startTime = System.currentTimeMillis();
                for (long i = 0; i < n; i++) {
                    arrayList.add("Some string"); // Probar diferentes operaciones
                }
                long elapsedTime = System.currentTimeMillis() - startTime;

                writer.write(String.format("%d,%d\n", n, elapsedTime));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }     
    }
}

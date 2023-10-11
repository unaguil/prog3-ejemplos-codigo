package tema4.tema4B.listas;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;
import java.util.function.Consumer;


// Programa que mide el tiempo que tardan distintas operaciones
// sobre listas utilizando la implementación LinkedList y ArrayList
// con el objetivo de comprobar la eficiencia de cada implementación.

// Se hace uso de interfaces y expresiones lambda para reutilizar el
// código de las pruebas.

public class EficienciaImplLista {

    public static final long MIN = 4000; // 64000
    public static final long MAX = 128000; // 16384000

    /**
     * Método que realiza la prueba de eficiencia y escribe los resultados
     * writer pasado como parámetro.
     * @param writer writer en el que se escriben los resultados.
     * @param prueba nombre de la prueba a escribir en el fichero.
     * @param lista implementación de lista sobre la que realizar la prueba.
     * @param crearLista supplier que proporciona la instancia de la lista con la implementación a utilizar.
     * @param operacion consumer que aplica la operación correspondiente sobre la lista recibida
     */
    private static void realizarPrueba(Writer writer, String prueba, 
            Supplier<List<String>> crearLista, Consumer<List<String>> operacion) throws IOException {
        
        System.out.format("Realizando prueba %s%n", prueba);

        writer.write(String.format("%s%n", prueba));

        // se repite la prueba para distintos valores de n
        // que se van multiplicando por 2 desde MIN hasta MAX
        for (long n = MIN; n <= MAX; n *= 2) {
            List<String> lista = crearLista.get();
            // se obtiene el tiempo antes de la prueba
            long startTime = System.currentTimeMillis();
            // se aplica la prueba 
            for (long i = 0; i < n; i++) {
                operacion.accept(lista); // se aplica la operación sobre la lista
            }
        
            // calcular el tiempo transcurrido y escribirlo en el writer
            long elapsedTime = System.currentTimeMillis() - startTime;
            writer.write(String.format("%d, %d%n", n, elapsedTime));
        }
        writer.write("*************************************\n");
    }
    
    public static void main(String[] args) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("eficiencia.txt"))) {
            // se prueban las operaciones con ArrayList
            realizarPrueba(writer, "ArrayList-addFirst", ArrayList<String>::new, e -> e.add(0, "Some string"));
            realizarPrueba(writer, "ArrayList-addLast", ArrayList<String>::new, e -> e.add("Some string"));
            realizarPrueba(writer, "ArrayList-addMiddle", ArrayList<String>::new, e -> e.add(e.size() / 2, "Some string"));
            realizarPrueba(writer, "ArrayList-isEmpty", ArrayList<String>::new, e -> e.isEmpty());

            // se prueban las operaciones con LinkedList
            realizarPrueba(writer, "LinkedList-addFirst", LinkedList<String>::new, e -> e.add(0, "Some string"));
            realizarPrueba(writer, "LinkedList-addLast", LinkedList::new, e -> e.add("Some string"));
            realizarPrueba(writer, "LinkedList-addMiddle", LinkedList<String>::new, e -> e.add(e.size() / 2, "Some string"));
            realizarPrueba(writer, "LinkedList-isEmpty", LinkedList<String>::new, e -> e.isEmpty());

        } catch (IOException e) {
            e.printStackTrace();
        }     
    }
}
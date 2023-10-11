package tema4.tema4B.cola;

import java.util.LinkedList;
import java.util.Queue;

// Ejemplo que muestra el uso de la interfaz Queue
// que representa Colas en Java.

// Aunque se usa una implementación LinkedList, el
// acceso se hace a través de la interfaz Queue, que
// limita las operaciones a aquellas propias de una Cola.

public class Cola {
    
    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        
        // se encolan los elementos
        for (int i = 0; i < 100; i++) {
            queue.add(i);
        }

        // la cola se recorre hasta que no hay más 
        // elementos para procesar -> esta vacía
        // Los elementos se procesan con el método FIFO
        while (!queue.isEmpty()) {
            Integer i = queue.poll();
            System.out.println(i);
        }
    }
}

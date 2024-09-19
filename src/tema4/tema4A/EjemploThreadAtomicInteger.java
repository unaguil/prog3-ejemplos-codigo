package tema4.tema4A;

import java.util.concurrent.atomic.AtomicInteger;

// En este ejemplo se muestra que el acceso a datos compartidos
// debe estar protegido por un método sincronizado.
// Tenemos un entero inicializado en cero que va a ser incrementado
// por cada uno de los hilos creados. La operación de incremento
// no es atómica por lo que se pueden producir las llamadas condiciones 
// de carrera (race-condition) cuando uno o más hilos compiten por
// actualizar la misma variable a la vez.

// En este caso la propia variable usada como contador se encarga
// de controlar el acceso desde varios hilos concurrentes.
public class EjemploThreadAtomicInteger {
    private static final int NUM_THREADS = 20;
    
    public static void main(String[] args) {
    	// instancia compartida por todos los threads
        final AtomicInteger counter = new AtomicInteger(0);

        // creamos y lanzamos todos los hilos
        Thread t = null;
        for (int i = 0; i < NUM_THREADS; i++) {
            t = new Thread(() -> counter.incrementAndGet());
            t.start();
        }
        
        // vamos a esperar que todos los hilos terminen
        for (int i = 0; i < NUM_THREADS; i++) {
            try {
				t.join();
			} catch (InterruptedException e) {
				// no hacer nada
			}
        }
    	
        System.out.println("Valor final: " + counter.get());    	
    }
}

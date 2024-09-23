package tema4.tema4A;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * En este ejemplo se muestra el uso del API Future de Java para
 * realizar las diferentes tareas en las que se particiona la suma
 * de manera paralela, obtener las sumas parciales y agregar
 * todos los resultados parciales posteriormente 
 */
public class EjemploTareaParalelizadaFuture {

    // el número máximo hasta el que sumar desde cero
    private static final int MAX_NUM = 1000_000_000;
    // el número de hilos que se van a crear en el pool
    private static final int MAX_HILOS = 10;

    // el número de tareas que se van a crear para procesar la suma
    private static final int NUM_TAREAS = 20;
    
    public static void main(String[] args) {
        // creamos el pool de hilos
        ExecutorService executor = Executors.newFixedThreadPool(MAX_HILOS);

        // creamos una lista para almacenar las tareas que vamos a lanzar
        // y poder esperar a que terminen antes de sumarlas
        List<Future<Long>> tareas = new ArrayList<Future<Long>>();

        // tiempo actual del sistema
        long instanteInicial = System.currentTimeMillis();

        // creamos las tareas, los hilos y los lanzamos
        for (int i = 0; i < NUM_TAREAS; i++) {
            int rango = MAX_NUM / NUM_TAREAS;

            // calculamos los rangos de inicio y fin de cada tarea
            int inicio = i * rango + 1;
            int fin = i == NUM_TAREAS - 1? fin = MAX_NUM : (i + 1) * rango;
            
            // lanzamos la tarea en el pool
            Future<Long> tarea = executor.submit(() -> { 
            	long resultado = 0;
                for (long v = inicio; v <= fin; v++) {
                    resultado += v;
                }
                
                return resultado;
            });
            
            // almacenamos la tarea lanzada para esperar a que termine
            tareas.add(tarea);
        }

        // sumamos los resultados parciales
        // ahora cada llamada a get() sobre cada tarea espera
        // a que haya terminado su hilo
        long suma = 0;
        for (Future<Long> tarea : tareas) {
        	try {
				suma += tarea.get();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
        }
        
        // paramos el pool de threads
        executor.shutdown();
        
        System.out.println("El resultado de la suma es: " + suma);

        // imprimimos por pantalla el tiempo total que ha tardado en ejecutarse
        System.out.println("Tiempo total: " + (System.currentTimeMillis() - instanteInicial) + " ms");
    }
}

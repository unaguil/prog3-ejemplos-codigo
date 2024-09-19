package tema4.tema4A;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * En este ejemplo, se muestra el uso de un pool de hilos para garantizar que
 * como máximo hay el número máximo de hilos en paralelo.
 * La tarea se descompone en varios hilos que se ejecutan y suman los resultados 
 * parciales entre dos límites. Cuando todos los hilos han terminado se suman 
 * estos resultados parciales para obtener el resultado final. 
 */
public class EjemploTareaThreadPool {

    // clase que implementa el código que se ejecutará en cada hilo
    private static class TareaSumar implements Runnable {

        private long inicio;
        private long fin;
        private long resultado;

        public TareaSumar(long inicio, long fin) {
            this.inicio = inicio;
            this.fin = fin;
        }

        public long getResultado() {
            return resultado;
        }

        @Override
        public void run() {
            resultado = 0;
            for (long i = inicio; i <= fin; i++) {
                resultado += i;
            }
        }
    }

    // el número máximo hasta el que sumar desde cero
    private static final int MAX_NUM = 1000_000_000;
    // el número de hilos que se van a crear en el pool
    private static final int MAX_HILOS = 10;

    // el número de tareas que se van a crear para procesar la suma
    private static final int NUM_TAREAS = 20;
    
    public static void main(String[] args) {
        // creamos el pool de hilos
        ExecutorService pool = Executors.newFixedThreadPool(MAX_HILOS);

        // creamos un array de tareas
        TareaSumar[] tareas = new TareaSumar[NUM_TAREAS];

        // tiempo actual del sistema
        long instanteInicial = System.currentTimeMillis();

        // creamos las tareas, los hilos y los lanzamos
        for (int i = 0; i < NUM_TAREAS; i++) {
            int rango = MAX_NUM / NUM_TAREAS;

            // calculamos los rangos de inicio y fin de cada tarea
            int inicio = i * rango + 1;
            int fin = i == NUM_TAREAS - 1? fin = MAX_NUM : (i + 1) * rango;
            
            tareas[i] = new TareaSumar(inicio, fin);
            
            // lanzamos la tarea en el pool
            pool.execute(tareas[i]);
        }

        // esperamos a que todos los hilos terminen
        pool.shutdown();
        while (!pool.isTerminated()) {
            // esperamos a que terminen todos los hilos
        }

        // sumamos los resultados parciales
        long suma = 0;
        for (int i = 0; i < NUM_TAREAS; i++) {
            //imprimir resultados parciales
            suma += tareas[i].getResultado();
        }
        
        System.out.println("El resultado de la suma es: " + suma);

        // imprimimos por pantalla el tiempo total que ha tardado en ejecutarse
        System.out.println("Tiempo total: " + (System.currentTimeMillis() - instanteInicial) + " ms");
    }
}

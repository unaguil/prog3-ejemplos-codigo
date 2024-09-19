package tema4.tema4A;

/**
 * En este ejemplo, la tarea se descompone en varios hilos que se ejecutan
 * y suman los resultados parciales entre dos límites. Cuando todos los hilos
 * han terminado se suman estos resultados parciales para obtener el resultado
 * final.
 */
public class EjemploTareaParalelizada {

    // clase que implementa el código que se ejecutará en cada hilo
    private static class TareaSumar implements Runnable {

        private int inicio;
        private int fin;
        private long resultado;

        public TareaSumar(int inicio, int fin) {
            this.inicio = inicio;
            this.fin = fin;
        }

        public long getResultado() {
            return resultado;
        }

        @Override
        public void run() {
            resultado = 0;
            for (int i = inicio; i <= fin; i++) {
                resultado += i;
            }
        }
    }

     // el número máximo hasta el que sumar desde cero
     private static final int MAX_NUM = 1000_000_000;
     // el número de hilos que se van a crear en el pool
     private static final int MAX_HILOS = 20;
   
    public static void main(String[] args) {
        // creamos un array de hilos
        Thread[] hilos = new Thread[MAX_HILOS];

        // creamos un array de tareas
        TareaSumar[] tareas = new TareaSumar[MAX_HILOS];

        // tiempo actual del sistema
        long instanteInicial = System.currentTimeMillis();

        // creamos las tareas, los hilos y los lanzamos
        for (int i = 0; i < MAX_HILOS; i++) {
            int rango = MAX_NUM / MAX_HILOS;

            // calculamos los rangos de inicio y fin de cada tarea
            int inicio = i * rango + 1;
            int fin = i == MAX_HILOS - 1? fin = MAX_NUM : (i + 1) * rango;
            
            tareas[i] = new TareaSumar(inicio, fin);
            hilos[i] = new Thread(tareas[i]);
            hilos[i].start();
        }

        // esperamos a que todos los hilos terminen
        for (int i = 0; i < MAX_HILOS; i++) {
            try {
                hilos[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // sumamos los resultados parciales
        long suma = 0;
        for (int i = 0; i < MAX_HILOS; i++) {
            //imprimir resultados parciales
            suma += tareas[i].getResultado();
        }
        
        System.out.println("El resultado de la suma es: " + suma);

        // imprimimos por pantalla el tiempo total que ha tardado en ejecutarse
        System.out.println("Tiempo total: " + (System.currentTimeMillis() - instanteInicial) + " ms");
    }
}

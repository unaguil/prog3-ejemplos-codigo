package tema1.tema1C;

import java.util.Arrays;
import java.util.Random;


// En este ejemplo se muestra que el acceso a datos compartidos
// debe estar protegido por un método sincronizado.
// En el ejemplo existe un registro que contiene un array de N
// posiciones enteras. Si una posición está a cero significa que está
// vacía, si tiene un valor distinto de cero, que está ocupada.

// Se crean N threads que concurrentemente escogen aleatoriamente posiciones
// libres y las reservan (se suma uno a la posición para reservarla).
// Al finalizar, todas las posiciones deberían estar exactamente a uno.

// En este ejemplo, se han introducido varios threads para aumentar la probabilidad de
// dos o más de ellos modifiquen una misma posición a la vez.

// Para resolver este problema es necesario que aquellos métodos que pueden
// modificar concurrentemente un dato sean marcados como synchronized.
// Eso hace que solamente un thread pueda entrar cada vez en el método.

public class EjemploThreadAccesoConcurrente {

    static class Registry {

        private int[] values;
        private final static int SIZE = 10;

        public Registry() {
            values = new int[SIZE];
        }

        public int size() {
            return SIZE;
        }

        // ESTE MÉTODO DEBE SER synchronized para que dos
        synchronized public void reserve(int pos) {
            if (values[pos] == 0) {
                // Se espera un tiempo entre la comprobación de
                // si la posición está libre y su reserva
                // Esto aumenta la probabilidad de acceso concurrente
                // a la misma posición por varios threads.
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    //
                }
                
                values[pos] += 1;
            }
        }

        @Override
        public String toString() {
            return Arrays.toString(values);
        }

    }    

    // El código de los dos threads se va a implementar
    // en una clase aparte para reutilizarlo fácilmente.
    // Se recibe el registro para poder modificarlo desde
    // la clase.
    static class Processor implements Runnable {

        private Registry r;

        public Processor(Registry r) {
            this.r = r;
        }

        @Override
        public void run() {
            Random random = new Random();
            for (int i = 0; i < r.size(); i++) {
                int pos = random.nextInt(r.size());
                r.reserve(pos);
            }
        }

    }

    private static final int NUM_THREADS = 50;
    
    public static void main(String[] args) {
        Registry registry = new Registry();

        System.out.println("Registro al inicio: " + registry);

        for (int i = 0; i < NUM_THREADS; i++) {
            Thread t = new Thread(new Processor(registry));
            t.start();
        }

        System.out.println("Registro al finalizar: " + registry);
    }
}

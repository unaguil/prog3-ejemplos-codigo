package tema4.tema4A;

// En este ejemplo se muestra que el acceso a datos compartidos
// debe estar protegido por un método sincronizado.
// Tenemos un entero inicializado en cero que va a ser incrementado
// por cada uno de los hilos creados. La operación de incremento
// no es atómica por lo que se pueden producir las llamadas condiciones 
// de carrera (race-condition) cuando uno o más hilos compiten por
// actualizar la misma variable a la vez.

// El modificador synchronized en el método que incrementa la variable
// controla que únicamente un thread pueda utilizar el método cada vez.
public class EjemploThreadAccesoConcurrente {

	// Clase que implementa el contador a incrementar
    static class Counter {

        private int value = 0; // inicializamos en cero
        
        // ESTE MÉTODO DEBE SER synchronized para que dos hilos no puedan 
        // realizar la operación de incremento "a la vez" sobre el mismo
        // contador. Esto es debido a que la operación += en realidad
        // son varias operaciones (no es atómica) leer el valor, sumar 1
        // al valor y actualizar la variable con el nuevo valor. Entre
        // cualquiera de estas operaciones pasa un tiempo mayor que cero
        // durente el que otro hilo puedo intentar modificar la misma variable
        // produciendose la actualización incorrecta.
        synchronized public void increment() {
            value += 1;
        }
        
        public int getValue() {
        	return value;
        }
    }    

    private static final int NUM_THREADS = 20;
    
    public static void main(String[] args) {
    	// instancia compartida por todos los threads
        final Counter counter = new Counter();

        // creamos y lanzamos todos los hilos
        Thread t = null;
        for (int i = 0; i < NUM_THREADS; i++) {
            t = new Thread(() -> counter.increment());
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
    	
        System.out.println("Valor final: " + counter.getValue());    	
    }
}

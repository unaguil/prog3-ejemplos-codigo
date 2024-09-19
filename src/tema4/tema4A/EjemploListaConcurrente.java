package tema4.tema4A;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Este ejemplo muestra el problema de utilizar una lista no concurrente en un
 * entorno concurrente en el que varios hilos acceden a la lista al mismo tiempo.
 * 
 * El primer hilo introduce elementos en la lista y el segundo hilo imprime el
 * contenido de la lista en consola.
 *  
 * Como la implementación básica de ArrayList no es concurrente, el programa no funciona
 * correctamente. Se pueden producir excepciones de tipo ConcurrentModificationException debido
 * a que el segundo hilo intenta acceder a la lista mientras el primer hilo la está modificando. 
 * Sin embargo, el problema puede resolverse utilizando la versión concurrente
 * de la lista, Collections.synchronizedList(new ArrayList<>()), que se encarga de sincronizar
 * los accesos a la lista para que no se produzcan problemas.
 * 
 * Pulsa Ctrl+C para terminar el programa.
 */
public class EjemploListaConcurrente {
	
	// lista de enteros que admite añadir elementos al final
	// se ha creado como una lista sincronizada para que los accesos sean seguros
	// prueba a quitar la llamada a Collections.synchronizedList y verás que el programa falla
	final static private List<Integer> lista = Collections.synchronizedList(new ArrayList<>());
	
	// añadir elementos al final de la lista
	static class IntroduceDatos implements Runnable {
		@Override
		public void run() {
			for (int i = 0; i < 1000; i++) {
				lista.add(i);
			}
		}
	}
	
	// imprime el contenido de la lista a consola en un hilo	
	static class ImprimeLista implements Runnable {
		@Override
		public void run() {
			while (!Thread.interrupted()) {
				System.out.println(lista);
			}
		}
	}

	public static void main(String[] args) {		
		// creamos dos hilos que van a acceder a la lista al mismo tiempo
		Thread hilo1 = new Thread(new IntroduceDatos());
		Thread hilo2 = new Thread(new ImprimeLista());
		
		// y los iniciamos
		hilo1.start();
		hilo2.start();
		
		// esperamos a que el primer hilo termine
		try {
			hilo1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// y paramos el segundo hilo
		hilo2.interrupt();
		
		System.out.println("Fin del programa");
	}

}

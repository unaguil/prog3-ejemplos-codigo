package tema1.tema1C;

import java.util.Random;


// En este ejemplo se muestran diferentes formas de generar
// números aleatorios. Se muestra que los números no son
// aleatorios sino pseudo-aleatorios.

public class EjemploRandom {

    public static void main(String[] args) {
        Random r = new Random();
		System.out.println("Tres enteros aleatorios de 0 a 99:");
		System.out.println(r.nextInt(100) + ", " + r.nextInt(100) + ", " + r.nextInt(100));
		System.out.println("Tres reales aleatorios de 0 a 1:");
		System.out.println(r.nextDouble() + ", " + r.nextDouble() + ", " + r.nextDouble());
                
        r = new Random(15);// ponemos una semilla para forzar la secuencia
		System.out.println("Tres enteros aleatorios con semilla 15, de 0 a 99:");
		System.out.println(r.nextInt(100) + ", " + r.nextInt(100) + ", " + r.nextInt(100));
        
        r = new Random(15);// si elegimos la misma semilla obtenemos la misma secuencia.
		System.out.println("Tres enteros aleatorios con nueva semilla 15, de 0 a 99:");
		System.out.println(r.nextInt(100) + ", " + r.nextInt(100) + ", " + r.nextInt(100));
    }
}
package tema4.tema4A.set;

import java.util.Set;
import java.util.HashSet;

// En este programa se muestra la funcionalidad
// básica de la colección Set.

public class EjemploSet {
    
    public static void main(String[] args) {
        Set<Integer> integers = new HashSet<>();
        
        integers.add(12); // Añadimos objetos al set
        integers.add(5);
        integers.add(3);
        integers.add(5);

        // Iteramos sobre los objetos del set
        for (Integer i : integers) {
            System.out.println(i);
        }

        System.out.println("¿Está vacía?: " + integers.isEmpty());

        // Eliminamos los elementos del set
        integers.clear();
    }
}

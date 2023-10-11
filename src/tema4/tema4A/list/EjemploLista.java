package tema4.tema4A.list;

import java.util.List;
import java.util.ArrayList;


// En este programa se muestran la funcionalidad
// básica de un colección de tipo List en Java.

public class EjemploLista {

    public static void main(String[] args) {
        // Se instancia una lista de enteros
        // Atención al lado izquierdo donde se usa la interfaz
        // en vez del tipo concreto de lista. Esto permite
        // cambiar fácilmente la implementación sin cambiar todo
        // el código.
        List<Integer> integers = new ArrayList<Integer>();

        // Método para añadir objetos al final de la lista
        integers.add(12);
        integers.add(8);
        integers.add(5);

        // Método para obtener el tamaño de la lista
        System.out.println("Tamaño de la lista: " + integers.size());

        // La lista se puede iterar con for-each
        for (Integer e : integers) {
            System.out.println(e);
        }

        // Borrar todos los elementos de la lista
        integers.clear();

        System.out.println("Tamaño de la lista: " + integers.size());
    }
}
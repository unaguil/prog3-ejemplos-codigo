package tema4.tema4B.pila;

import java.util.Deque;
import java.util.LinkedList;

// Ejemplo de uso de la interfaz Deque, que 
// es la interfaz recomendada para Pilas, frente
// a la implementación con Stack más antigua y
// que no es coherente con el resto del framework
// de collectiones de Java.

// La implementación concreta es un LinkedList pero
// se accede a través de la interfaz Deque que 
// proporciona las operaciones propias de una Pila.

public class Pila {

    public static void main(String[] args) {
        Deque<Integer> stack = new LinkedList<>();

        // se apilan los elementos
        for (int i = 0; i < 100; i++) {
            stack.push(i);
        }

        // se desapilan los elementos
        // en orden inverso (LIFO)
        while (!stack.isEmpty()) {
            Integer i = stack.pop();
            System.out.println(i);
        }
    }
}
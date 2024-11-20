package tema6.tema6B;

import java.util.Arrays;
import java.util.Random;

// Este programa implementa el algoritmo de búsqueda
// binaria que se basa en la un algoritmo de divide y
// vencerás.

public class BinarySearch {

    // Método recursivo que implementa la búsqueda binaria recursiva.
    // Recibe el array completo y el elemento a buscar y aplica la 
    // ordenación a los elementos determinados por los indices
    // start-end (ambos incluidos). Se devuelve el indice donde se ha
    // encontrado el elemento en el array y -1 para indicar que no se
    // ha encontrado
    public static int binarySearchRec(int[] array, int e, int start, int end) {
        // caso base array vacío
        if (end < start) 
            return -1;
        
        // caso base array con un elemento.
        // ver si es el elemento buscado
        if (start == end) 
            return array[start] == e ? start : -1;

        // caso recursivo:

        // obtener el punto medio para dividir
        int middle = (end - start) / 2 + start;

        if (e <= array[middle]) {
            // aplicar el algoritmo recursivo a la mitad izquierda (start, middle)
            return binarySearchRec(array, e, start, middle);
        } else {
            // aplicar el algoritmo recursivo a la mitad derecha (middle + 1, end)
            return binarySearchRec(array, e, middle + 1, end);
        }
    }

    // Este método se utiliza para inicar el proceso de búsqueda
    // recursiva utilizando los parámetros adecuados.
    public static int binarySearch(int[] array, int e) {
        return binarySearchRec(array, e, 0, array.length - 1);
    }

    // Método para crear un array de un longitud dada con números positivos aleatorios
    public static int[] randomArray(int size) {
        int[] array = new int[size];

        Random r = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = r.nextInt(100);
        }

        return array;
    }

    public static void main(String[] args) {
        int[] array = { 1, 3, 4, 8, 9, 12, 13, 14, 16, 19, 20, 21, 33, 34, 35, 38, 39, 42, 44, 45, 46, 47, 53, 55, 57, 59, 62, 63, 64, 73, 76, 77, 78, 80, 84, 91, 99 };
        int e = 21;

        System.out.println("Array: " + Arrays.toString(array));
        System.out.println("Index of " + e + ": " + binarySearch(array, 21));
    }
}

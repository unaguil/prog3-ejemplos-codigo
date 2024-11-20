package tema6.tema6B;

import java.util.Arrays;
import java.util.Random;

// Este programa implementa el algoritmo Merge Sort.
// Es un algoritmo de ordenación basado en la estrategia
// divide y vencerás (divide-and-conquer).

public class MergeSort {

    // Método recursivo que implementa el merge sort recursivo.
    // Recibe el array completo y aplica la ordenación a los elementos
    // determinados por los indices start-end (ambos incluidos).
    public static void mergeSortRec(int[] array, int start, int end) {
        // caso base - array de un elemento -> ya está ordenado
        if (end - start < 1) return;

        // caso recursivo:
        
        // obtener el punto medio para dividir
        int middle = (end - start) / 2 + start;

        // aplicar el algoritmo recursivo a la mitad izquierda (start, middle)
        mergeSortRec(array, start, middle);

        // aplicar el algoritmo recursivo a la mitad derecha (middle + 1, end)
        mergeSortRec(array, middle + 1, end);

        // se unen los dos arrays izq. y der. ordenados
        mergeArrays(array, start, middle, middle + 1, end);
    }

    // Este método recibe los datos y el inicio-fin de la parte izq. y de la parte der.
    // Crea un array temporal para mezclar las dos secuencias y realiza de nuevo la
    // copia de los datos ordenados en el array original.
    public static void mergeArrays(int[] array, int lStart, int lEnd, int rStart, int rEnd) {
        // se crea un array temporal para guardar la unión de los dos arrays indicados
        // por lStart-lEnd y rStart-rEnd
        int[] tempArray = new int[lEnd - lStart + rEnd - rStart + 2];

        int tempIndex = 0; // indice para recorrer el array destino
        int lIndex = lStart; // indice para recorrer el array izquierdo
        int rIndex = rStart; // indice para recorrer el array derecho

        // se recorren ambos arrays (izq-der) hasta llegar al final de uno de ellos
        while (lIndex <= lEnd && rIndex <= rEnd) {
            if (array[lIndex] < array[rIndex]) {
                // si el valor actual del array de la izquierda es menor que el valor
                // acual del array de la derecha copiar el valor de la izquierda en el
                // array de destino.
                tempArray[tempIndex] = array[lIndex];
                lIndex++; // incrementar el indice que recorre el array de la izquierda. 
            } else {
                // si el valor de la derecha es mayor o igual que la izq, entonces usar este
                tempArray[tempIndex] = array[rIndex];
                rIndex++; // incrementar el indice que recorre el array de la derecha.
            }
            tempIndex++; // en cada vuelta incrementar el indice que recorre el array destino
        }
        
        // terminar de copiar los elementos restantes de izq. y de der. al temporal
        while (lIndex <= lEnd)
            tempArray[tempIndex++] = array[lIndex++];

        while (rIndex <= rEnd)
            tempArray[tempIndex++] = array[rIndex++];
            
        // Copiar el array temporal ya ordenado sobre el array a ordenar desde lStart a lEnd (incluidos)
        for (int i = 0; i < tempArray.length; i++) {
            array[lStart + i] = tempArray[i];
        }
    }

    // Este método se utiliza para inicar la recursividad
    // con los parámetros adecuados (shell).
    public static void mergeSort(int[] array) {
        mergeSortRec(array, 0, array.length - 1);
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
    
    private static final int ARRAY_SIZE = 10;

    public static void main(String[] args) {
        int[] array = randomArray(ARRAY_SIZE);

        System.out.println("Antes de ordenar: ");
        System.out.println(Arrays.toString(array));

        mergeSort(array);

        System.out.println("Ordenado: ");
        System.out.println(Arrays.toString(array));
    }
}

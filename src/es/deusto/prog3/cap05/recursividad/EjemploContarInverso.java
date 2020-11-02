package es.deusto.prog3.cap05.recursividad;

// Este ejemplo muestra la implementación 
// mediante una función/método recursivo de
// una función que cuenta (imprime) valores
// desde un número inicial (start) hasta un
// número final (end), ambos incluidos.
// Se supone que start <= end.

// La posición de la llamada recursiva (no final)
// modifica el funcionamiento. Los valores son almacenados
// en las distintas llamadas y se imprimen durante el camino
// de retorno de las mismas y, por lo tanto, en orden inverso.

public class EjemploContarInverso {

    // El método imprime el número actual (current), lo
    // imprime y llama de nuevo al método contar(...) si
    // todavía no se ha llegado al máximo

    // Atención al caso base. Si el número actual es igual al
    // máximo no se continúa con la llamada.
    public static void contar(int current, int end) {
        // Si no se ha llegado al límite, se continua la llamada
        if (current != end) {
            contar(current + 1, end);
        } // else {
            // Si se ha llegado current == end
            // no se hacen más llamadas y se termina
            // la recursividad.
        // }

        // Antes de terminar la llamada se imprime el valor actual
        System.out.println(current);
    }
    
    public static void main(String[] args) {
        int start = 0;
        contar(start, 10);
    }
}

package es.deusto.prog3.cap05.recursividad;

// Este programa implementa el cálculo del factorial
// de un número utilizando recursividad.

// La recursividad lineal pero no es final, ya que el
// retorno de la función recursiva se combina antes de
// retornarlo en la función.

public class EjemploFactorial {

    // Este método implementa el factorial de un número >= 0
    // de forma recursiva de acuerdo a la definición:
    // factorial(N) = N * factorial (N - 1).
    // El caso base es que, por definición, se tiene que:
    // factorial(0) = 1
    public static int factorial(int n) {
        // Este es el caso base, por definición
        // tenemos el valor de factorial(0) = 1
        // No depende de una llamada recursiva
        // para calcularlo
        if (n == 0) return 1;
        
        // este caso utiliza la definición recursiva
        // no es recursividad final porque el resultado
        // de la llamada recursiva se combina con un valor,
        // en este caso multiplicando.
        return n * factorial(n - 1);
    }
    
    public static void main(String[] args) {
        System.out.println(factorial(5));
    }
}

package es.deusto.prog3.cap05.recursividad;

// Este programa muestra el cálculo de un valor
// de la secuencia de Fibonacci:

// F(0) = 0
// F(1) = 1
// F(N) = F(N - 1) + F(N - 2)

public class EjemploFibonacci {
    
    public static int fib(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;

        return fib(n - 1) + fib(n - 2);
    }

    public static void main(String[] args) {
        System.out.println("Fib(6) = " + fib(6));
        System.out.println("Fib(8) = " + fib(8));
    }
}

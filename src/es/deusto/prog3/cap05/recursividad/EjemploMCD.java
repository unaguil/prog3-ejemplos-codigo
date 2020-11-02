package es.deusto.prog3.cap05.recursividad;

// Este programa muestra el cálculo del máximo
// común divisor de dos números utilizando la
// definición recursiva del mismo:

// mcd(n, m) = n si n = m
// mcd(n, m) = mcd(n - m, m) si n > m
// mcd(n, m) = mcd(n, m - n) si m > n

public class EjemploMCD {

    // Esta función implementa el cálculo del 
    // máximo común divisor de forma recursiva.
    // Es lineal y además final porque el resultado
    // de la llamada recursiva se devuelve directamente
    // como retorno
    public static int mcd(int n, int m) {
        // caso base
        if (n == m) return n;

        // n > m: caso recursivo
        if (n > m) return mcd(n - m, n);
        
        // m > n: caso recursivo
        return mcd(n, m - n);

    }
    
    public static void main(String[] args) {
        System.out.println("MCD(60, 60) = " + mcd(60, 60));
        System.out.println("MCD(60, 48) = " + mcd(60, 48));
        System.out.println("MCD(13, 7) = " + mcd(13, 7));
    }
}

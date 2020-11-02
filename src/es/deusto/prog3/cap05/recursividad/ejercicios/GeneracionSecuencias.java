package es.deusto.prog3.cap05.recursividad.ejercicios;

// Este programa implementa un algoritmo recursivo
// para generar las cadenas de longitud N obtenidas
// con los elementos indicados.
public class GeneracionSecuencias {
    
    private static final char[] ELEMENTS = { 'A', 'B', 'C' };
    private static final int N = 5;
    
    private static void generate(int n, String sequence) {
        // caso base. imprimir el resultado
        if (n == 0) {
            System.out.println(sequence);
        } else {
            // concatenar un caracter
            // se realiza una llamada por cada caracter
            // disponible
            for (char c : ELEMENTS) {
                generate(n - 1, sequence + c);
            }
        }
    }

    public static void main(String[] args) {
        generate(N, "");

        // número de cadenas distintas
        System.out.println(String.format("Num. cadenas: %.0f", Math.pow(ELEMENTS.length, N)));
    }
}

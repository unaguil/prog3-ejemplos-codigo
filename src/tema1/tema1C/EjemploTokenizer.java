package tema1.tema1C;

import java.util.Scanner;
import java.util.StringTokenizer;


// En este ejemplo se pide al usuario que introduzca un texto
// y se separa en elementos (tokens). Se usa como separador
// los caracteres: espacio, punto y la coma.

public class EjemploTokenizer {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Escribe algo: ");
            String input = sc.nextLine();

            // Se crea una instancia del tokenizer y se pasa el string
            // a partir y los caracteres por los que partir"
            StringTokenizer st = new StringTokenizer(input, " ,.");

            // se itera sobre las partes que nos da el tokenizer
            while (st.hasMoreTokens()) {
                // se obtiene el token siguiente y se imprime
                String token = st.nextToken();
                System.out.println(token);
            }
        }
    }
}
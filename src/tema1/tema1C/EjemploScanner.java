package tema1.tema1C;

import java.util.Scanner;

// Este programa lee datos de la consola utilizando
// la clase Scanner que facilita la conversión de datos.

public class EjemploScanner {

    public static void main(String[] args) {
        // Creamos una instance de la clase y le pasamos
        // el stream de entrada de la consola
        try(Scanner sc = new Scanner(System.in)) {
            
            System.out.print("Nombre: ");
            String name = sc.nextLine();

            System.out.print("Edad: ");
            int age = sc.nextInt();

            System.out.println(
                String.format("Te llamas %s y tienes %d años", name, age)
            );
        }
    }
}
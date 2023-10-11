package tema1.tema1E;

// Este ejemplo muestra cómo se pueden utilizar
// los argumentos variables en un método.

public class EjemploArgVariables {


    public static int sumar(int ...values) {
        int total = 0;
        for (int v : values) {
            total += v;
        }

        return total;
    }
    
    public static void main(String[] args) {
        System.out.println(sumar());
        System.out.println(sumar(2));
        System.out.println(sumar(2, 5));
        System.out.println(sumar(2, 5, 3, 7, 12));
    }
}

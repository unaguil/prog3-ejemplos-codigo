package tema1.tema1E;

// Este ejemplo muestra como Java hace operaciones
// implícitas de autoboxing que además pueden impactar
// en el rendimiento.

public class EjemploAutoboxing {
    
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        Integer total = 0; // cambiando a int el programa es más rápido
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            total += i; // se realiza una operación de boxing
        }

        System.out.println("Total: " + total);

        long elapsedTime = System.currentTimeMillis() - startTime;
        System.out.println("Time (ms): " + elapsedTime);
    }
}

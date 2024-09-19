package tema4.tema4A;

/**
 * Este ejemplo muestra una tarea larga que no aprovecha 
 * la posibilidad de paralelismo que ofrece una CPU con varios núcleos. 
 * La tarea consiste en sumar todos los números de 0 a 10_000_000. 
 * La tarea se ejecuta en un solo hilo (principal).
 */
public class EjemploTareaSecuencial {

     // el número máximo hasta el que sumar desde cero
     private static final int MAX_NUM = 1000_000_000;
    
    public static void main(String[] args) {
        // tiempo actual del sistema
        long tiempoInicial = System.currentTimeMillis();

        long suma = 0;
        for (int i = 1; i <= MAX_NUM; i++) {
            suma += i;
        }
        System.out.println("El resultado de la suma es: " + suma);

        // imprimimos por pantalla el tiempo total que ha tardado en ejecutarse
        System.out.println("Tiempo total: " + (System.currentTimeMillis() - tiempoInicial) + " ms");
    }
}

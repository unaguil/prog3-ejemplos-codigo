package tema1.tema1C;

// En este ejemplo se muestra como se puede esperar a que
// termine la ejecución de otro hilo antes de continuar.

public class EjemploThreadJoin {

    public static void main(String[] args) {      
        // Vamos a crear un thread para contar desde 10 a 0.
        // El hilo se va a dormir después de imprimir cada
        // valor durante 1000 ms.
        // En cada vuelta del bucle se comprueba si debemos parar
        // consultando el flag interrupted.
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 10; !Thread.interrupted() && i >= 0; i--) {
                    System.out.println(Thread.currentThread().getName() + " -> " + i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // El sleep nos ha capturado el interrumpir
                        // volvemos a ponerlo para detener el bucle y que muera el thread.
                        Thread.currentThread().interrupt(); 
                    }
                }

                System.out.println(Thread.currentThread().getName() + " finalizado");
            }
        });

        // Se debe iniciar el thread.
        t.start();

        // // Aquí el programa principal empieza a contar hasta 5
        // // lo que se realizará de forma concurrente al hilo creado.
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " -> " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                //
            }
        }

        // Se espera a que el hilo creado termine para continuar.
        System.out.println("main esperando a que el otro hilo termine..");
        try {
            t.join();
        } catch (InterruptedException e) {
            //
        }
        
        System.out.println("El otro hilo ha terminado. Sigue main");

        // Hasta que no terminen el hilo principal (main) y el hilo que
        // hemos creado, el programa sigue en ejecución.

        System.out.println("Hilo main terminado");
    }
}

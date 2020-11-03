package es.deusto.prog3.cap05.recursividad.problemas.fractales;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;

// Este programa recrea la figura fractal obtenida a 
// partir del conjunto de Mandelbrot.

// También contiene código para mostrar el conjunto de Julia

public class Mandelbrot extends JFrame {

    // Esta clase se utiliza para hacer el pintado de los
    // la figura generada.
    class Canvas extends JPanel {

        /**
         *
         */
        private static final long serialVersionUID = 1L;

        public Canvas() {}

        // Implementación recursiva de las iteraciones en el
        // conjunto de Mandelbrot
        private int mandelbrotRec(Complex z, Complex c, int n, int max_iter) {
            // Caso base
            if (n == max_iter || z.abs() > 2) 
                return n;

            // caso recursivo
            return mandelbrotRec(z.mult(z).add(c), c, n + 1, max_iter);
        }


        // este método inicia la iteración recursiva dando los valores
        // de inicio al proceso.
        private int mandelbrot(Complex z, int max_iter) {
            return mandelbrotRec(z, z, 0, max_iter);
        }

        // Implementación recursiva de las iteraciones en el
        // conjunto de Julia
        private int juliaRec(Complex z, Complex c, int n, int max_iter) {
            // Caso base
            if (n == max_iter || z.abs() > 10) 
                return n;

            // caso recursivo
            return mandelbrotRec(z.mult(z).add(c), c, n + 1, max_iter);
        }

        private int julia(Complex z, int max_iter) {
            return juliaRec(z, new Complex(-0.1, 0.65), 0, max_iter);
        }

        private final static float X_POS = -0.5f; // zona a mostrar
        private final static float Y_POS = 0.0f; // 

        private final static float SIZE = 2; // zoom

        private final static int MAX = 255;

        // Este método lleva a cabo el dibujado del panel.
        @Override
        public void paintComponent(Graphics g) {
            Dimension d = getSize();

            // Se recorren todos los pixeles de la imagen
            for (int xPixel = 0; xPixel < d.width; xPixel++) {
                for (int yPixel = 0; yPixel < d.height; yPixel++) {
                    // El pixel se transforma para mostrar una zona concreta
                    double x = X_POS - SIZE / 2 + SIZE * xPixel / d.width;
                    double y = Y_POS - SIZE / 2 + SIZE * yPixel / d.height;
    
                    // Se itera el punto actual para obtener el fractal
                    // Se puede cambiar por el conjunto de Julia usando
                    // el otro método disponible
                    int n = mandelbrot(new Complex(x, y), MAX);

                    // se dibuja un pixel en la posición actual con el
                    // color determinado por el número de iteraciones
                    int gray = MAX - n;
                    g.setColor(new Color(gray, gray, gray));
                    g.fillRect(xPixel, yPixel, 1, 1);
                }
            }
        }
    }

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public Mandelbrot() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(640, 640);

        add(new Canvas());

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new Mandelbrot();
            }

        });
    }
}
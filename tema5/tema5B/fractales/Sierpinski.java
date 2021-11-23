import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Point;

// Este programa recrea la figura fractal de Sierpinski
// dibujando triángulos de manera recursiva en un panel.

public class Sierpinski extends JFrame {

    // Esta clase se utiliza para hacer el pintado de los
    // triángulos en un JPanel modificando el método 
    // paintComponent(...).
    class Canvas extends JPanel {

        /**
         *
         */
        private static final long serialVersionUID = 1L;

        public Canvas() {}

        private void drawRec(Graphics g, int depth, Point a, Point b, Point c) {
            // Se comprueba el caso base, si se ha llegado a la profundidad cero
            // se dibuja el triángulo con los vertices correspondientes.
            if (depth == 0) {
                g.fillPolygon(new int[] {a.x, b.x, c.x}, new int[] {a.y, b.y, c.y}, 3);
            } else {
                // para cualquier profundidad distinta de cero
                // se hacen tres llamadas recursivas para cada uno de
                // los posibles triángulos (arriba, izq. y der.).
                drawRec(g, depth - 1, middle(a, b), b, middle(b, c));
                drawRec(g, depth - 1, a, middle(a, c), middle(a, b));
                drawRec(g, depth - 1, middle(a, c), middle(b, c), c);
            }
        }

        // Este método calcula el punto medio de dos puntos
        private Point middle(Point a, Point b) {
            return new Point((a.x + b.x) / 2, (a.y + b .y) / 2);
        }

        private static final int MAX_DEPTH = 7;

        // Este método lleva a cabo el dibujado del panel
        // llamando al método que dibuja el Triángulo de Sierpinski.
        @Override
        public void paintComponent(Graphics g) {
            g.setColor(Color.RED);

            // Se obtiene la dimensión real del panel.
            // Es un poco más pequeño que la ventana ya que
            // esta tiene bordes y título. Pero esto no importa
            // ya que todo el cálculo se hace relativo a su
            // dimensión
            Dimension panelSize = getSize();

            drawRec(
                g, // objeto para pintar
                MAX_DEPTH, // proundidad máxima de la recursividad
                new Point(0, panelSize.height), // punto A
                new Point(panelSize.width / 2, 0), // punto B
                new Point(panelSize.width, panelSize.height) // punto C
            );
        }
    }

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public Sierpinski() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(640, 480);

        add(new Canvas());

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new Sierpinski();
            }

        });
    }
}
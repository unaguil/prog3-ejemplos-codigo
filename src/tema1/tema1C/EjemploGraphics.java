package tema1.tema1C;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Timer;


// Este ejemplo muestra como utilizar el nuevo API para
// la gestión de fechas y tiempos.

// Este ejemplo utiliza un temporizador de Swing, que crea
// lleva a cabo la actualización periódica de la posición
// del círculo cada 25 ms. 
// Esto garantiza que el repintado se lleva a cabo
// dentro del hilo de Swing.

public class EjemploGraphics extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    
    private Timer timer;
    private Canvas canvas;

    public EjemploGraphics() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(640, 480);
        setTitle("Ejemplo Graphics");

        canvas = new Canvas();
        add(canvas);

        setVisible(true);

        timer = new Timer(40, new ActionListener() {
           
            @Override
            public void actionPerformed(ActionEvent e) {
                canvas.updatePos();
                canvas.repaint();
            }
        });

        timer.start();
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {
            
            @Override
            public void run() {
                new EjemploGraphics();
            }
        });   
    }

    class Canvas extends JComponent {

        /**
         *
         */
        private static final long serialVersionUID = 1L;
        private int x = 0;
        private int y = 0;

        private int xSpeed = 5;
        private int ySpeed = 5;

        private final static int RADIUS = 10;

        public Canvas() {
            setDoubleBuffered(true);
            setBackground(Color.BLACK);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            Graphics2D graphics2D = (Graphics2D)g;

            graphics2D.setPaint(new Color(150, 0, 0));
            graphics2D.fillOval(x, y, RADIUS * 2, RADIUS * 2);
        }

        public void updatePos() {
            int rightSide = x + RADIUS * 2;
            int leftSide = x;
            int upSide = y;
            int bottomSide = y + RADIUS * 2;

            if (rightSide > getWidth() || leftSide < 0) {
                xSpeed = -xSpeed;
            }

            if (upSide < 0 || bottomSide > getHeight()) {
                ySpeed = -ySpeed;
            }

            x += xSpeed;
            y += ySpeed;
        }       
    }
}
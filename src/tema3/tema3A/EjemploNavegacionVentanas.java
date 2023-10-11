package tema3.tema3A;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Este ejemplo muestra la navegación entre ventanas. La ventana inicial crea
 * otra ventana y se hace invisible para volver a ser mostrada al cerrar la
 * segunda ventana.
 */
public class EjemploNavegacionVentanas extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    // Clase interna usada en el ejemplo para mostrar la
    // navegación con otras ventanas
    // La clase recibe en el constructor la referencia a
    // la ventana anterior para poder volver a mostrar al
    // cerrarse
    class AnotherWindow extends JFrame implements WindowListener {

        /**
         *
         */
        private static final long serialVersionUID = 1L;

        private JFrame previous;

        public AnotherWindow(JFrame previous) {
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            this.previous = previous;
            addWindowListener(this);

            JLabel someLabel = new JLabel("Segunda ventana");
            add(someLabel, BorderLayout.CENTER);

            pack();
            setVisible(true);
        }

        // este evento se lanza cuando se cierra esta
        // segunda ventana
        @Override
        public void windowClosed(WindowEvent e) {
            // se vuelve a hacer visible la ventana anterior
            previous.setVisible(true);
        }

        // no se utilizan en este ejemplo
        @Override
        public void windowActivated(WindowEvent e) {
        }

        @Override
        public void windowClosing(WindowEvent e) {
        }

        @Override
        public void windowDeactivated(WindowEvent e) {
        }

        @Override
        public void windowDeiconified(WindowEvent e) {
        }

        @Override
        public void windowIconified(WindowEvent e) {
        }

        @Override
        public void windowOpened(WindowEvent e) {
        }
    }

    public EjemploNavegacionVentanas() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JButton showButton = new JButton("Mostrar otra ventana");

        showButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // se hace invisible la ventana actual
                setVisible(false);

                // se crea la segunda ventana
                // pasando la referencia a la actual
                new AnotherWindow(EjemploNavegacionVentanas.this);
            }
            
        });
        
        JPanel mainPanel = new JPanel();
        mainPanel.add(showButton);

        add(mainPanel, BorderLayout.CENTER);

        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new EjemploNavegacionVentanas();
			}

        });
    }
}

package tema3.tema3A;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;


/**
 * Este ejemplo muestra distintos diálogos que pueden
 * ser mostrados al usuario 
 */
public class EjemploDialogos extends JFrame {
    
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public EjemploDialogos() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Ejemplo Swing");

        JPanel centerPanel = new JPanel();

        JButton errorButton = new JButton("Mensaje de error");
        centerPanel.add(errorButton);
        errorButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // para mostrar mensajes de error
                JOptionPane.showMessageDialog(EjemploDialogos.this, "Se ha producido un error", "Error", JOptionPane.ERROR_MESSAGE);
            }

        });

        JButton infoButton = new JButton("Mensaje de información");
        centerPanel.add(infoButton);
        infoButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // para mostrar mensajes de información.
                JOptionPane.showMessageDialog(EjemploDialogos.this, "Mensaje al usuario", "Información", JOptionPane.INFORMATION_MESSAGE);
            }

        });

        JButton confirmationButton = new JButton("Mensaje de confirmación");
        centerPanel.add(confirmationButton);
        confirmationButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // un mensaje de confirmación con botones si/no
                // en este caso es necesario comprobar la respuesta que
                // ha seleccionado el usuario
                int response = JOptionPane.showConfirmDialog(EjemploDialogos.this, "¿Está seguro?", "Confirmar", JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                    System.out.println("El usuario ha elegido: SI");
                } else {
                    System.out.println("El usuario ha elegido: NO");
                }
            }

        });

        add(centerPanel, BorderLayout.CENTER);

        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new EjemploDialogos();
            }

        });
    }
}

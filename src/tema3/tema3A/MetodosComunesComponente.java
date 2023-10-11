package tema3.tema3A;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;

/**
 * Este ejmplo muestra el uso de distintos métodos comunes
 * a todos los componentes visuales al heredar de Component.
 */
public class MetodosComunesComponente extends JFrame {
    
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public MetodosComunesComponente() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Ejemplo Swing");

        JPanel mainPanel = new JPanel(new BorderLayout());
        add(mainPanel);
        
        // borde del panel principal de la ventana (linea + titulo)
        Border lineBorder = BorderFactory.createLineBorder(Color.RED);
        Border titledBorder = BorderFactory.createTitledBorder(lineBorder, "Ejemplo");
        mainPanel.setBorder(titledBorder);

        JButton actionButton = new JButton("Cambiar estado");
        actionButton.setToolTipText("Habilita/deshabilita el campo de texto");

        JPanel topPanel = new JPanel();
        topPanel.add(actionButton);
        mainPanel.add(topPanel, BorderLayout.NORTH);

        JTextField textField = new JTextField(20);
        textField.setToolTipText("Un campo de texto");

        JPanel middlePanel = new JPanel();
        middlePanel.add(textField);
        mainPanel.add(middlePanel, BorderLayout.CENTER);

        // listener del boton
        actionButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // se habilita/deshabilita el textfield
                textField.setEnabled(!textField.isEnabled());
            }

        });

        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new MetodosComunesComponente();
            }

        });
    }
}

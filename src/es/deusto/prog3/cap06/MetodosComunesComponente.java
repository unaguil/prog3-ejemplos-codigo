package es.deusto.prog3.cap06;

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


public class MetodosComunesComponente extends JFrame {
    
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public MetodosComunesComponente() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(320, 240);
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

        actionButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setEnabled(!textField.isEnabled());
            }

        });

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

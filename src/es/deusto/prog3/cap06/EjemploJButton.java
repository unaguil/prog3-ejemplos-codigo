package es.deusto.prog3.cap06;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;


public class EjemploJButton extends JFrame {
    
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public EjemploJButton() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Ejemplo Swing");

        JPanel bottomPanel = new JPanel();
        JButton mainButton = new JButton("Principal");
        bottomPanel.add(mainButton);
        
        // iconos para distintos estados del boton
        mainButton.setIcon(new ImageIcon(getClass().getResource("/es/deusto/prog3/cap06/res/coffee-icon.png")));
        mainButton.setDisabledIcon(new ImageIcon(getClass().getResource("/es/deusto/prog3/cap06/res/pingus-icon.png")));

        JButton buttonA = new JButton("Botón A");

        // tecla rápida pra el boton
        // es necesario pulsar ALT + el caracter del mnemónico (ALT + A)
        buttonA.setMnemonic(KeyEvent.VK_A);
        buttonA.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Boton A click!");
            }

        });

        JButton buttonB = new JButton("Botón B");
        buttonB.setMnemonic(KeyEvent.VK_B);
        buttonB.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                mainButton.setEnabled(!mainButton.isEnabled());
            }

        });
        
        JPanel centerPanel = new JPanel();
        centerPanel.add(buttonA);
        centerPanel.add(buttonB);

        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new EjemploJButton();
            }

        });
    }
}

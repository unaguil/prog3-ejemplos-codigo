package tema3.tema3A;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Este ejemplo muestra cómo se utiliza un JTextArea
 */
public class EjemploJTextArea extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public EjemploJTextArea() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Ejemplo Swing");

        JTextArea textArea = new JTextArea();
        textArea.setRows(15);
        textArea.setColumns(30);
        textArea.setLineWrap(true);

        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        JButton printButton = new JButton("Imprimir texto seleccionado");
        printButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                String selectedText = textArea.getSelectedText();
                System.out.println(selectedText);
            }

        });

        add(printButton, BorderLayout.SOUTH);

        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new EjemploJTextArea();
            }

        });
    }
}

package tema3.tema3A;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.ActionListener;
import java.io.File;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

/**
 * Este ejemplo muestra un diálogo de selección de fichero.
 */
public class EjemploFileChooser extends JFrame {
    
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public EjemploFileChooser() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Ejemplo Swing");

        JPanel mainPanel = new JPanel(new BorderLayout());
        add(mainPanel);
        
        JButton saveFileButton = new JButton("Guardar fichero...");
        mainPanel.add(saveFileButton);

        saveFileButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
               JFileChooser fileChooser = new JFileChooser();
               
               // solo se admiten ficheros con extensión ".txt"
               FileFilter filter = new FileNameExtensionFilter("Fichero TXT", "txt");
               fileChooser.setFileFilter(filter);

               // en este caso se muestra un dialogo de selección de fichero de
               // guardado.
               int result = fileChooser.showSaveDialog(EjemploFileChooser.this);
               if (result == JFileChooser.APPROVE_OPTION) {
                   // el usuario ha pulsado el boton aceptar
                   // se obtiene el fichero seleccionado -> File
                   File file = fileChooser.getSelectedFile();
                   System.out.println("Fichero seleccionado: " + file.toString());
               }
               
            }

        });

        add(mainPanel, BorderLayout.CENTER);

        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new EjemploFileChooser();
            }

        });
    }
}

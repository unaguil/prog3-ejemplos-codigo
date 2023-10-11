package tema3.tema3A;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JRadioButtonMenuItem;
import java.awt.event.KeyEvent;


/**
 * Este ejemplo muestra el uso de los menús
 * de aplicación
 */
public class EjemploMenu extends JFrame {
    
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public EjemploMenu() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Ejemplo Swing");

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu fileMenu = new JMenu("Fichero");
        menuBar.add(fileMenu);
        fileMenu.setMnemonic(KeyEvent.VK_F);

        JMenuItem openMenuItem = new JMenuItem("Abrir");
        fileMenu.add(openMenuItem);
        openMenuItem.setMnemonic(KeyEvent.VK_S);

        fileMenu.addSeparator();

        JMenuItem importMenuItem = new JMenuItem("Importar...");
        fileMenu.add(importMenuItem);
        importMenuItem.setMnemonic(KeyEvent.VK_S);

        JMenuItem exportMenuItem = new JMenuItem("Exportar...");
        fileMenu.add(exportMenuItem);
        exportMenuItem.setMnemonic(KeyEvent.VK_S);

        fileMenu.addSeparator();

        JMenuItem exitMenuItem = new JMenuItem("Salir");
        fileMenu.add(exitMenuItem);
        exitMenuItem.setMnemonic(KeyEvent.VK_S);

        JMenu optionsMenu = new JMenu("Opciones");
        menuBar.add(optionsMenu);
        optionsMenu.setMnemonic(KeyEvent.VK_O);

        JCheckBoxMenuItem checkBoxMenuItem = new JCheckBoxMenuItem("Opción A", new ImageIcon(getClass().getResource("/coffee-icon.png")));
        optionsMenu.add(checkBoxMenuItem);

        JMenu subOptions = new JMenu("Más opciones");
        optionsMenu.add(subOptions);

        JRadioButtonMenuItem radioButton1 = new JRadioButtonMenuItem("Opción 1", new ImageIcon(getClass().getResource("/pingus-icon.png")));
        subOptions.add(radioButton1);

        JRadioButtonMenuItem radioButton2 = new JRadioButtonMenuItem("Opción 2", new ImageIcon(getClass().getResource("/usb-icon.png")));
        subOptions.add(radioButton2);

        ButtonGroup radioButtonGroup = new ButtonGroup();
        radioButtonGroup.add(radioButton1);
        radioButtonGroup.add(radioButton2);
        
        setSize(320, 240);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new EjemploMenu();
            }

        });
    }
}

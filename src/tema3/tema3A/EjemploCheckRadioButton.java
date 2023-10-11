package tema3.tema3A;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import javax.swing.border.Border;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Este ejemplo muestra las funcionalidades de los
 * componentes de tipo check button y de tipo
 * radio button. 
 */
public class EjemploCheckRadioButton extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public EjemploCheckRadioButton() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Ejemplo Swing");

        // los radio buttons se añaden a un grupo para que sean
        // exluyentes entre sí
        JRadioButton radioButton1 = new JRadioButton("Opcion 1");
        JRadioButton radioButton2 = new JRadioButton("Opcion 2");
        JRadioButton radioButton3 = new JRadioButton("Opcion 3");

        ButtonGroup radioButtonGroup = new ButtonGroup();
        radioButtonGroup.add(radioButton1);
        radioButtonGroup.add(radioButton2);
        radioButtonGroup.add(radioButton3);

        // se crea un panel con borde y titulo y se organizan los radio buttons
        // en vertical con un BoxLayout
        JPanel radioButtonPanel = new JPanel();
        radioButtonPanel.setLayout(new BoxLayout(radioButtonPanel, BoxLayout.Y_AXIS));
        Border radioButtonsBorder = BorderFactory.createTitledBorder("Radio buttons");
        radioButtonPanel.setBorder(radioButtonsBorder);

        radioButtonPanel.add(radioButton1);
        radioButtonPanel.add(radioButton2);
        radioButtonPanel.add(radioButton3);

        // los check box no son excluyentes -> no van en un grupo
        JCheckBox checkBox1 = new JCheckBox("Opcion 1");
        JCheckBox checkBox2 = new JCheckBox("Opcion 2");
        JCheckBox checkBox3 = new JCheckBox("Opcion 3");

        JPanel checkBoxPanel = new JPanel();
        Border checkButtonsBorder = BorderFactory.createTitledBorder("Check buttons");
        checkBoxPanel.setBorder(checkButtonsBorder);

        checkBoxPanel.add(checkBox1);
        checkBoxPanel.add(checkBox2);
        checkBoxPanel.add(checkBox3);

        add(radioButtonPanel, BorderLayout.NORTH);
        add(checkBoxPanel, BorderLayout.SOUTH);

        // se va a añadir a todos los botones el mismos listener
        ActionListener actionListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JToggleButton toggleButton = (JToggleButton) e.getSource();
                System.out.println("Cambio de estado en " + toggleButton.getText() + ". Seleccionado: " + toggleButton.isSelected());
            }

        };

        radioButton1.addActionListener(actionListener);
        radioButton2.addActionListener(actionListener);
        radioButton3.addActionListener(actionListener);
        
        checkBox1.addActionListener(actionListener);
        checkBox2.addActionListener(actionListener);
        checkBox3.addActionListener(actionListener);

        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new EjemploCheckRadioButton();
            }

        });
    }
}

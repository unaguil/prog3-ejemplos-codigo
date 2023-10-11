package tema3.tema3A;

import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Este ejemplo muestra cómo se utiliza un componente
 * de tipo combo box y un jlist para seleccionar entre distintos
 * valores de una lista. El componente utiliza un modelo
 * de datos que contiene la información a visualizar.
 * 
 * Esta clase de modelo puede ser extendida para adaptarla
 * a las necesidades de los datos que se quieran mostrar.
 */
public class EjemploComboBoxJList extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public EjemploComboBoxJList() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Ejemplo Swing");

        // datos de ejemplo
        Person[] persons = { 
            new Person("Enrico", "Fermi", LocalDate.of(1901, 9, 29)), 
            new Person("Albert", "Einstein", LocalDate.of(1879, 4, 14)),
            new Person("Marie", "Curie", LocalDate.of(1867, 11, 07))
        };

        // se debe especificar el tipo de dato que se asocia
        // no tiene que ser string, puede ser cualquier clase
        // se visualiza el toString() de cada objeto
        // Los datos se añaden mediante un modelo
        ComboBoxModel<Person> comboBoxModel = new DefaultComboBoxModel<>(persons);
        JComboBox<Person> comboBox = new JComboBox<>(comboBoxModel);

        JPanel comboBoxPanel = new JPanel();
        Border comboBoxPanelBorder = BorderFactory.createTitledBorder("JComboBox");
        comboBoxPanel.setBorder(comboBoxPanelBorder);
        comboBoxPanel.add(comboBox);

        // listener de selección en el combobox
        comboBox.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                // se comprueba si se ha seleccionado o deseleccionado
                // un elemento de la lista
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    System.out.println("Seleccionado: " + e.getItem());
                }

                if (e.getStateChange() == ItemEvent.DESELECTED) {
                    System.out.println("Deseleccionado: " + e.getItem());
                }
            }

        });

        // se crea el modelo de la lista usando la clase existente en el API
        // el modelo de datos en Java 8 no tienen métodos para añadir una
        // lista de datos de una vez, por eso se hace elemento a elemento.
        // se podría extender el modelo para añadir estos métodos de utilidad
        DefaultListModel<Person> listModel = new DefaultListModel<>();
        for (Person p : persons) {
            listModel.addElement(p);
        }

        JList<Person> list = new JList<>(listModel);
        
        //en este caso 
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // listener de seleccion en el jlist
        list.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                // comprobar si hay elementos seleccionados
                // se puede recibir el evento también al deseleccionados
                // se comprueba con isAdjusting si es el ultimo evento de una
                // cadena de eventos -> evita eventos repetidos.
                if (list.getSelectedIndex() != -1 && !e.getValueIsAdjusting()) {
                    Person p = list.getSelectedValue();
                    System.out.println("Seleccionado: " + p);
                }
            }
            
        });


        JPanel listPanel = new JPanel();
        Border listPanelBorder = BorderFactory.createTitledBorder("JList");
        listPanel.setBorder(listPanelBorder);
        listPanel.add(list);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(comboBoxPanel);
        mainPanel.add(listPanel);

        add(mainPanel, BorderLayout.CENTER);

        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new EjemploComboBoxJList();
            }

        });
    }
}

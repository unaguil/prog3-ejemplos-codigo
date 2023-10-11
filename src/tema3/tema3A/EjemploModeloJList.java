package tema3.tema3A;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;
import java.awt.BorderLayout;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;

/**
 * Este ejemplo muestra cómo se puede crear un modelo
 * de datos propio para un JList.
 */
public class EjemploModeloJList extends JFrame {
    
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    // este modelo de lista proporciona un método
    // addAll para añadir una lista de personas directamente
    // DefaultListModel no contiene este método en Java 8
    class MyListModel extends DefaultListModel<Person> {

        /**
         *
         */
        private static final long serialVersionUID = 1L;

        public MyListModel(Collection<Person> persons) {
            add(persons);
        }

        public void add(Collection<Person> persons) {
            for (Person p : persons) {
                addElement(p);
            }
        }        

    }

    public EjemploModeloJList() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Ejemplo Swing");

        // datos de ejemplo
        Person[] persons = { 
            new Person("Enrico", "Fermi", LocalDate.of(1901, 9, 29)), 
            new Person("Albert", "Einstein", LocalDate.of(1879, 4, 14)),
            new Person("Marie", "Curie", LocalDate.of(1867, 11, 07))
        };

        // se utiliza el nuevo model implementado que admite los datos
        // en el constructor
        MyListModel listModel = new MyListModel(Arrays.asList(persons));
        JList<Person> list = new JList<>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JPanel mainPanel = new JPanel();
        mainPanel.add(list);

        add(mainPanel, BorderLayout.CENTER);

        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new EjemploModeloJList();
            }

        });
    }
}

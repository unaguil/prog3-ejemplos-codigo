package es.deusto.prog3.cap06;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.SwingUtilities;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.ListSelectionModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.util.Arrays;
import java.util.Collection;

public class EjemploRendererJList extends JFrame {

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

    class MyCellRenderer extends JLabel implements ListCellRenderer<Person> {

        /**
         *
         */
        private static final long serialVersionUID = 1L;
        private ImageIcon selectedIcon;
        private ImageIcon unselectedIcon;

        private Color defaultBackground;

        public MyCellRenderer() {
            selectedIcon = new ImageIcon(getClass().getResource("/es/deusto/prog3/cap06/res/coffee-icon.png"));
            unselectedIcon = new ImageIcon(getClass().getResource("/es/deusto/prog3/cap06/res/pingus-icon.png"));

            setOpaque(true);
            defaultBackground = getBackground();
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends Person> list, Person value, int index,
                boolean isSelected, boolean hasFocus) {
            setText(value.toString());

            if (isSelected) {
                setIcon(selectedIcon);
                setBackground(Color.LIGHT_GRAY);
            } else {
                setIcon(unselectedIcon);
                setBackground(defaultBackground);
            }

            return this;
        }

    }

    public EjemploRendererJList() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Ejemplo Swing");

        // datos de ejemplo
        Person[] persons = { 
            new Person("Enrico", "Fermi", "29/09/1901"), 
            new Person("Albert", "Einstein", "14/03/1879"),
            new Person("Marie", "Curie", "07/11/1867") 
        };

        // se utiliza el nuevo model implementado que admite los datos
        // en el constructor
        MyListModel listModel = new MyListModel(Arrays.asList(persons));
        JList<Person> list = new JList<>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setCellRenderer(new MyCellRenderer());

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
                new EjemploRendererJList();
            }

        });
    }
}

package tema3.tema3A;

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
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;

/**
 * Este ejemplo muestra cómo se crea un renderer
 * propio para los elementos de un JList.
 */
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

    // Esta clase define el renderer para los elementos visuales de la tabla
    // El renderer debe implementar la interfaz ListCellRender.
    // En este caso se extiende un JLabel como componente a visualizar
    class MyCellRenderer extends JLabel implements ListCellRenderer<Person> {

        /**
         *
         */
        private static final long serialVersionUID = 1L;
        private ImageIcon selectedIcon;
        private ImageIcon unselectedIcon;

        private Color defaultBackground;

        // constructor del renderer que carga los iconos a utilizar
        public MyCellRenderer() {
            selectedIcon = new ImageIcon(getClass().getResource("/es/deusto/prog3/cap06/res/coffee-icon.png"));
            unselectedIcon = new ImageIcon(getClass().getResource("/es/deusto/prog3/cap06/res/pingus-icon.png"));

            // el JLabel se configura para que sea opaco y se pueda ver
            // el color de selección
            setOpaque(true);
            
            // se guarda el color por defecto para utilizarlo
            // cuando el componente no está seleccionado
            defaultBackground = getBackground();
        }

        // este es el método que se llama para obtener el componente
        // que se debe pintar en cada elemento de la lista
        @Override
        public Component getListCellRendererComponent(JList<? extends Person> list, Person value, int index,
                boolean isSelected, boolean hasFocus) {
            // se establece el valor del texto mostrado en el JLabel de la celda
            setText(value.toString());

            // el icono y el color en función si está o no seleccionado
            if (isSelected) {
                setIcon(selectedIcon);
                setBackground(Color.LIGHT_GRAY);
            } else {
                setIcon(unselectedIcon);
                setBackground(defaultBackground);
            }

            // se devuelve el componente visual. siempre se devuelve this
            // porque se evita crear uno nuevo para cada elemento de la lista
            return this;
        }

    }

    public EjemploRendererJList() {
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

        // la lista solamente admite selección sencilla
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // se establece el renderer para los elementos de la lista
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

package tema3.tema3A;

import javax.swing.AbstractCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import org.jdatepicker.JDatePicker;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Este ejemplo muestra el uso del componente visual JTable
 * con las diferentes opciones de configuración que tiene:
 * uso de un modelo de datos propio para obtener y establecer datos,
 * uso de renderers específicos para algunas columnas,
 * uso de editor para la columna de fecha.
 * La edición de la columna de fecha se hace con un componente
 * de tipo JDatePicker y requiere la librería externa incluida
 * en el directorio 'lib'.
 */
public class EjemploJTable extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * Esta clase es el modelo de datos específico de la tabla.
     * Hereda de la clase AbstractTableModel para redefinir los
     * métodos propios del comportamiento que se necesita para 
     * los datos que van a ser mostrados.
     */
    class MyTableModel extends AbstractTableModel {

        /**
         *
         */
        private static final long serialVersionUID = 1L;

        // array con los nombres de las columnas
        private String[] headers = { "Nombre", "Apellido", "Nacimiento" };
        private List<Person> persons;

        // Este constructor recibe una lista con los objetos Persona
        // que van a ser mostrados en la tabla
        public MyTableModel(List<Person> persons) {
            this.persons = persons;
        }

        // este método es usado por la tabla para obtener los nombres
        // de cada columna. En este caso se obtienen del array interno
        @Override
        public String getColumnName(int index) {
            return headers[index];
        }

        // este método devuelve la clase de cada columna
        // es utilizado por el JTable para elegir el componente visual
        // o el renderer/editor adecuado para cada tipo de datos
        @Override
        public Class<?> getColumnClass(int column) {
            // en este caso, las dos primeras columnas es String
            // String, mientras que el dato de la última es LocalDate
            if (column == 2) {
                return LocalDate.class;
            } else {
                return String.class;
            }
        }

        // este método es utilizado por el JTable para saber el total
        // de filas que tiene que mostrar en la tabla
        @Override
        public int getRowCount() {
            // en este caso es la longitud de la lista de datos
            return persons.size();
        }

        // este método es utilizado por el JTable para obtener el valor
        // de una celda (row, column) concreta
        @Override
        public Object getValueAt(int row, int column) {
            // como el modelo de datos es una lista de objetos en este caso
            // se obtiene la fila/objeto correspondiente
            // en función del número de columna se obtiene la propiedad
            Person p = persons.get(row);
            switch (column) {
                case 0:
                    return p.getName();
                case 1:
                    return p.getSurname();
                case 2:
                    return p.getBirthDate();
            }

            return null;
        }

        // utilizado por el JTable para saber el número de columnas
        // a representar
        @Override
        public int getColumnCount() {
            // en este caso el número de columnas es la longitud
            // del array de títulos de columnas
            return headers.length;
        }

        // el JTable utiliza este método para determinar si una
        // celda concreta de la tabla es editable
        @Override
        public boolean isCellEditable(int row, int column) {
            // para este ejemplo, son editables todas menos
            // la primera columna
            return column >= 1;
        }

        // este método es utilizado por el JTable para actualizar
        // el modelo de datos asociado. 
        // Recibe un Object por lo que, sabiendo el dato interno
        // es necesario hacer un cast
        @Override
        public void setValueAt(Object value, int row, int column) {
            // Obtenemos el objeto del modelo interno (lista) que
            // que se debe actualizar en función del valor de fila recibido
            Person p = persons.get(row);

            // teniendo en cuenta el valor de la columna recibida
            // actualizamos la propiedad correspondiente de la Persona
            // teniendo en cuenta el tipo concreto de dato
            switch (column) {
                case 0:
                    p.setName((String) value);
                    break;
                case 1:
                    p.setSurname((String) value);
                    break;
                case 2:
                    p.setBirthdate((LocalDate) value);
                    break;
            }

            // este método se utiliza para notificar que el modelo de datos
            // se ha actualizado y se debe repintar la celda visual
            fireTableCellUpdated(row, column);
        }

    }

    // esta clase implementa un renderer concreto para la tabla
    // este renderer se va a utilizar solamente para las celdas de
    // la columna de nombre, para ello se implementa la interfaz TableCellRenderer
    // además se extiende un JLabel, ya que la propia clase va a ser el componente
    // visual utilizado.
    class NameCellRenderer extends JLabel implements TableCellRenderer {

        /**
         *
         */
        private static final long serialVersionUID = 1L;
        private ImageIcon selectedIcon;
        private ImageIcon unselectedIcon;

        // en el constructor se cargan los iconos
        // que se van a mostrar según el estado de la celda
        public NameCellRenderer() {
            selectedIcon = new ImageIcon(getClass().getResource("/coffee-icon-16.png"));
            unselectedIcon = new ImageIcon(getClass().getResource("/pingus-icon-16.png"));

            // esta propiedad permite que se vea el color de fondo del JLabel
            // por defecto el componente es transparente y no se vería el color
            // de selección
            setOpaque(true); 
        }

        // este método es el método que devuelve al JTable el componente
        // que debe utilizar para visualizar la celda indicada (row, column)
        // dice tambien si la celda esta seleccionada por el usuario o no
        @Override
        public Component getTableCellRendererComponent(JTable table, Object color, boolean isSelected, boolean hasFocus,
                int row, int column) {

            // se va a mostrar un icono distinto si la celda está seleccionada o no
            setIcon(isSelected ? selectedIcon : unselectedIcon);

            // el valor de la celda mostrado se obtiene del modelo de datos
            // directamente, aunque podría hacerse alguna transformación antes
            // de visualizarlo
            String value = (String) table.getValueAt(row, column);
            setText(value);

            // se devuelve el componente visual que se va a ver en la celda
            // se devuelve el propio objeto ya que extiende de JLabel.
            // esto se hace para evitar crear distintas instancias de componentes
            // por cada celta, reutilizandose el mismo componente visual
            return this;
        }

    }

    // esta clase imprementa otro renderer pero en este caso 
    // uno específico para la columna de fechas
    // en este caso se extiende una clase ya existente porque
    // vale con el componente de texto usual para la celda.
    // Sin embargo, se hace una conversión del dato interno
    // del modelo de datos (LocalDate) con un formatter antes
    // de visualizar el componente
    class DateCellRenderer extends DefaultTableCellRenderer {

        /**
         *
         */
        private static final long serialVersionUID = 1L;

        private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");

        // se reimplementa el comportamiento del renderer por defecto
        // para convertir el dato interno (Local Date) a un string con
        // un formato visual adecuado
        @Override
        public void setValue(Object value) {
            LocalDate localTime = (LocalDate) value;
            if (localTime != null) {
            	setText(localTime.format(formatter));
            }
        }
    }

    // esta clase implementa un editor específico para la columna de fechas de la tabla
    // en concreto se va a utilizar un JDatePicker para que se muestre un calendario
    // cuando el usuario quiere editar una celda de fecha.
    // para implementar este editor se extendiende una clase con funcionalidad ya
    // existente (AbstractCellEditor) y se implementa una interfaz necesaria (TableCellEditor).
    class DateCellEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {

        /**
         *
         */
        private static final long serialVersionUID = 1L;

        private LocalDate currentValue;
        private JDatePicker datePicker;

        // este método debe devolver el valor que se va a mostrar en tabla
        // como resultado de la edición
        @Override
        public Object getCellEditorValue() {
            return currentValue;
        }

        // este método debe devolver el componente visual que se va a utilizar
        // cuando el usuario quiera editar la celda indicada
        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            // en este caso se devuelve un selector de fecha
            // como componente visual de edición
            datePicker = new JDatePicker();
            datePicker.addActionListener(this);
            return datePicker;
        }

        // este método es utilizado por el selector de fecha
        // cuando el usuario selecciona una fecha
        @Override
        public void actionPerformed(ActionEvent e) {
            // cuando el usuario selecciona la fecha en el componente visual
            // se obtiene la fecha seleccionada
            GregorianCalendar calendar = (GregorianCalendar) datePicker.getModel().getValue();
            ZonedDateTime zonedDateTime = calendar.toZonedDateTime();

            // se actualiza el valor que será devuelto por getCellEditorValue()
            // para mostrarlo como resultado de la edición
            currentValue = zonedDateTime.toLocalDate();

            // se debe indicar que la edición ha terminado para lanzar
            // la actualización de la tabla y el modelo
            fireEditingStopped();
        }

    }

    public EjemploJTable() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Ejemplo Swing");

        // datos de ejemplo
        Person[] persons = { 
            new Person("Enrico", "Fermi", LocalDate.of(1901, 9, 29)), 
            new Person("Albert", "Einstein", LocalDate.of(1879, 4, 14)),
            new Person("Marie", "Curie", LocalDate.of(1867, 11, 07))
        };

        // se crea el modelo de la tabla con los datos
        MyTableModel tableModel = new MyTableModel(Arrays.asList(persons));

        // se crea la tabla y se asigna el modelo/datos
        JTable table = new JTable(tableModel);

        // se establecen el renderer de la columna de nombres
        TableColumn nameColumn = table.getColumnModel().getColumn(0);
        nameColumn.setCellRenderer(new NameCellRenderer());

        // se establece el renderer y el editor de la columna de fechas
        TableColumn birthdateColumn = table.getColumnModel().getColumn(2);
        birthdateColumn.setCellRenderer(new DateCellRenderer());
        birthdateColumn.setCellEditor(new DateCellEditor());

        // la tabla se añade en un scroll pane para poder
        // navegar por las filas
        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane, BorderLayout.CENTER);

        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new EjemploJTable();
            }

        });
    }
}

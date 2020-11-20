package es.deusto.prog3.cap06;

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

public class EjemploJTable extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    class MyTableModel extends AbstractTableModel {

        /**
         *
         */
        private static final long serialVersionUID = 1L;

        private String[] headers = { "Nombre", "Apellido", "Nacimiento" };
        private List<Person> persons;

        public MyTableModel(List<Person> persons) {
            this.persons = persons;
        }

        // este método devuelve los nombres
        // de cada columna a la tabla
        @Override
        public String getColumnName(int index) {
            return headers[index];
        }

        @Override
        @SuppressWarnings("all")
        public Class getColumnClass(int column) {
            if (column == 2) {
                return LocalDate.class;
            } else {
                return String.class;
            }
        }

        // devuelve el número de filas en el modelo de datos
        @Override
        public int getRowCount() {
            return persons.size();
        }

        // obtiene el valor de una celda de la tabla
        @Override
        public Object getValueAt(int row, int column) {
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

        // obtiene el número de columnas
        @Override
        public int getColumnCount() {
            return headers.length;
        }

        // determina si cierta celda es editable
        // en este caso queremos poder editar solamente
        // la columna del apellido y la fecha, pero no
        // la del nombre
        @Override
        public boolean isCellEditable(int row, int column) {
            return column >= 1;
        }

        public void setValueAt(Object value, int row, int column) {
            Person p = persons.get(row);
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

            // se actualiza la tabla visual
            fireTableCellUpdated(row, column);
        }

    }

    // renderer propio usado en la columna del nombre
    class NameCellRenderer extends JLabel implements TableCellRenderer {

        /**
         *
         */
        private static final long serialVersionUID = 1L;
        private ImageIcon selectedIcon;
        private ImageIcon unselectedIcon;

        public NameCellRenderer() {
            selectedIcon = new ImageIcon(getClass().getResource("/es/deusto/prog3/cap06/res/coffee-icon-16.png"));
            unselectedIcon = new ImageIcon(getClass().getResource("/es/deusto/prog3/cap06/res/pingus-icon-16.png"));
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object color, boolean isSelected, boolean hasFocus,
                int row, int column) {
            setIcon(isSelected ? selectedIcon : unselectedIcon);

            String value = (String) table.getValueAt(row, column);
            setText(value);

            return this;
        }

    }

    class DateCellRenderer extends DefaultTableCellRenderer {

        /**
         *
         */
        private static final long serialVersionUID = 1L;

        private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");

        public void setValue(Object value) {
            LocalDate localTime = (LocalDate) value;
            setText(localTime.format(formatter));
        }
    }

    class DateCellEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {

        /**
         *
         */
        private static final long serialVersionUID = 1L;

        private LocalDate currentValue;
        private JDatePicker datePicker;

        public DateCellEditor() {
            currentValue = LocalDate.of(2020, 11, 20);
        }

        @Override
        public Object getCellEditorValue() {
            return currentValue;
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            datePicker = new JDatePicker();
            datePicker.addActionListener(this);
            return datePicker;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            GregorianCalendar calendar = (GregorianCalendar) datePicker.getModel().getValue();
            ZonedDateTime zonedDateTime = calendar.toZonedDateTime();
            currentValue = zonedDateTime.toLocalDate();

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

        MyTableModel tableModel = new MyTableModel(Arrays.asList(persons));
        JTable table = new JTable(tableModel);

        TableColumn nameColumn = table.getColumnModel().getColumn(0);
        nameColumn.setCellRenderer(new NameCellRenderer());

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

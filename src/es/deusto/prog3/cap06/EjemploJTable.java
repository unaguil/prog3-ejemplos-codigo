package es.deusto.prog3.cap06;

import javax.swing.AbstractCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.BorderLayout;
import java.awt.Component;
import java.util.Arrays;
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

        private String[] headers = { "Nombre", "Apellido", "Nacimiento"};
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
        public Class getColumnClass(int c) {
            return String.class;
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
                case 0: return p.getName();
                case 1: return p.getSurname();
                case 2: return p.getBirthDate();
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
            String s = (String) value;
            
            Person p = persons.get(row);
            switch (column) {
                case 0: p.setName(s);
                        break;
                case 1: p.setSurname(s);
                        break;
                case 2: p.setBirthdate(s);
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
            selectedIcon = new ImageIcon(getClass().getResource("/es/deusto/prog3/cap06/res/coffee-icon.png"));
            unselectedIcon = new ImageIcon(getClass().getResource("/es/deusto/prog3/cap06/res/pingus-icon.png"));
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object color, boolean isSelected, boolean hasFocus, int row, int column) {
            setIcon(isSelected ? selectedIcon : unselectedIcon);
            
            String value = (String) table.getValueAt(row, column);
            setText(value);

            return this;
        }

    }

    public EjemploJTable() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Ejemplo Swing");

        // datos de ejemplo
        Person[] persons = { 
            new Person("Enrico", "Fermi", "29/09/1901"), 
            new Person("Albert", "Einstein", "14/03/1879"),
            new Person("Marie", "Curie", "07/11/1867") 
        };

        MyTableModel tableModel = new MyTableModel(Arrays.asList(persons));
        JTable table = new JTable(tableModel);

        TableColumn nameColumn = table.getColumnModel().getColumn(0);
        nameColumn.setCellRenderer(new NameCellRenderer());

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

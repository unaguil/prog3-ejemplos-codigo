package tema3.tema3A;

import javax.swing.SwingUtilities;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Ejemplo de uso de JFormattedTextField con distintos formateadores
 * para validad la entrada del usuario.
 */
public class EjemploJFormattedTextField extends JFrame {

	private static final long serialVersionUID = 1L;

	public EjemploJFormattedTextField() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Ejemplo Swing");

        // panel con un campo de texto formateado para números
        JPanel panelNumero = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel labelNumero = new JLabel("Número:");
        panelNumero.add(labelNumero);

        // JFormattedTextField con un formateador para números enteros
        // que únicamente admite números enteros entre 0000 y 5000
        DecimalFormat formatoVisual = new DecimalFormat("####");
        // la validación de la entrada se hace en el método stringToValue
        // este método se llama cada vez que se introduce un carácter en el campo
        NumberFormatter formatoEntrada = new NumberFormatter(formatoVisual) {
            
			private static final long serialVersionUID = 1L;

			@Override
            public Object stringToValue(String text) throws ParseException {

                if (text == null || text.length() == 0) {
                    return null;
                }
                // Validar que el texto que introduce el usuario tenga máximo 4 caracteres
                if (text.length() > 4) {
                    throw new ParseException("Máximo 4 dígitos", 0);
                }
                return super.stringToValue(text);
            }
        };
        
        // configuración del formateador de entrada para dígitos
        formatoEntrada.setValueClass(Integer.class); // el valor devuelto es un Integer
        formatoEntrada.setMinimum(0); // valor mínimo permitido
        formatoEntrada.setMaximum(5000); // valor máximo permitido
        formatoEntrada.setAllowsInvalid(false); // no admite caracteres inválidos
        formatoEntrada.setOverwriteMode(true); // sobreescribe el texto si es inválido
        formatoEntrada.setCommitsOnValidEdit(true); // se confirma la edición al validar
        
        JFormattedTextField txtFieldNumero = new JFormattedTextField(formatoEntrada);
        txtFieldNumero.setColumns(3);
        panelNumero.add(txtFieldNumero);

        // ahora se crea un campo de texto formateado para números reales
        // con un máximo de 5 dígitos enteros y 2 decimales
        JPanel panelNumeroReal = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel labelNumeroReal = new JLabel("Número real:");
        panelNumeroReal.add(labelNumeroReal);

        // configuración del campo de texto formateado para números reales con 5 enteros y 2 decimales
        NumberFormatter formateadorNumeroReal = new NumberFormatter(new DecimalFormat("####0.00")) {
        	
			private static final long serialVersionUID = 1L;

			@Override
            public Object stringToValue(String text) throws ParseException {
                if (text == null || text.length() == 0) {
                    return null;
                }
                // Validar que el texto que introduce el usuario tenga máximo 8 caracteres
                // incluido el punto decimal
                if (text.length() > 7) {
                    throw new ParseException("Máximo 7 dígitos", 0);
                }
                return super.stringToValue(text);
            }
        };
        formateadorNumeroReal.setAllowsInvalid(false); // no admite caracteres inválidos
        formateadorNumeroReal.setOverwriteMode(true); // sobreescribe el texto si es inválido
        JFormattedTextField txtFieldNumerosReales = new JFormattedTextField(formateadorNumeroReal);
        txtFieldNumerosReales.setColumns(5);
        txtFieldNumerosReales.setHorizontalAlignment(JTextField.RIGHT);
        txtFieldNumerosReales.setValue(0.0);
        panelNumeroReal.add(txtFieldNumerosReales);

        // ejemplo de formateo de fecha con un campo de texto formateado
        JPanel panelFecha = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel labelFecha = new JLabel("Fecha:");
        panelFecha.add(labelFecha);

        // formateador de fecha con el formato dd/MM/yyyy que valida la entrada del usuario
        // utilizando un SimpleDateFormat para parsear la fecha y devolver un objeto Date
        MaskFormatter formateadorMascara = null;
        try {
           formateadorMascara = new MaskFormatter("##/##/####") {

				private static final long serialVersionUID = 1L;

				// este método convierte la cadena de texto que introduce el usuario
                // en un objeto Date que será devuelto por el campo de texto cuando
                // se llame al método getValue
                @Override
                public Object stringToValue(String text) throws ParseException {

                    if (text == null || text.length() == 0) {
                        return null;
                    }
                    // Validar que el texto que introduce el usuario tenga 10 caracteres
                    if (text.length() != 10) {
                        throw new ParseException("Formato dd/MM/yyyy", 0);
                    }

                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    sdf.setLenient(false); // no admite fechas inválidas
                    return sdf.parse(text);
                }

                // este método convierte el objeto Date que maneja el campo de texto
                // en una cadena con el formato dd/MM/yyyy
                @Override
                public String valueToString(Object value) throws ParseException {
                    if (value == null || !(value instanceof Date)) {
                        return super.valueToString(value);
                    }
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    return sdf.format(value);
                }

            };
        } catch (ParseException e) {
            System.out.println("Error en el formateador de fecha");
            e.printStackTrace();
        }

        formateadorMascara.setPlaceholderCharacter('_'); // caracter de relleno
        formateadorMascara.setAllowsInvalid(false); // no admite caracteres inválidos
        formateadorMascara.setOverwriteMode(true); // sobreescribe el texto si es inválido

        JFormattedTextField txtFieldFecha = new JFormattedTextField(formateadorMascara);
        txtFieldFecha.setColumns(6);
        panelFecha.add(txtFieldFecha);
        
        // configuramos el panel de la ventana con un BoxLayout vertical y
        // añadimos los paneles principales
        BoxLayout boxLayout = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS);
        setLayout(boxLayout);

        add(panelNumero);
        add(panelNumeroReal);
        add(panelFecha);

        // botón en la parte inferior de la ventana para imprimir por consola
        // los valores de los campos de texto formateados
        JPanel panelBoton = new JPanel();
        add(panelBoton, BorderLayout.SOUTH);

        JButton boton = new JButton("Imprimir valores");
        panelBoton.add(boton);
        // imprime la clase y el valor de los campos de texto formateados
        // comprobando previamente que los valores sean validos
        boton.addActionListener(e -> {
            if (txtFieldNumero.getValue() != null) {
                System.out.format("Valor: %s, Clase: %s%n", txtFieldNumero.getValue(), txtFieldNumero.getValue().getClass());
            } else {
                System.out.println("El valor del campo de texto formateado para números no es válido");
            }
            
            if (txtFieldNumerosReales.getValue() != null) {
                System.out.format("Valor: %s, Clase: %s%n", txtFieldNumerosReales.getValue(), txtFieldNumerosReales.getValue().getClass());
            } else {
                System.out.println("El valor del campo de texto formateado para números reales no es válido");
            }
            
            if (txtFieldFecha.getValue() != null) { 
                System.out.format("Valor: %s, Clase: %s%n", txtFieldFecha.getValue(), txtFieldFecha.getValue().getClass());
            } else {
                System.out.println("El valor del campo de texto formateado para fechas no es válido");
            }
        });

        pack();
        setVisible(true);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EjemploJFormattedTextField();
            }
        });
    }
}

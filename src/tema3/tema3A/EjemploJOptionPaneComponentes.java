package tema3.tema3A;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class EjemploJOptionPaneComponentes {
	
	public static void main(String[] args) {
		// Crear los campos de texto
		JTextField campoTexto1 = new JTextField();
		JTextField campoTexto2 = new JTextField();

		// Crear las etiquetas
		JLabel etiqueta1 = new JLabel("Primer campo:");
		JLabel etiqueta2 = new JLabel("Segundo campo:");

		// Colocar las etiquetas y los campos en un array de objetos
		Object[] message = { etiqueta1, campoTexto1, etiqueta2, campoTexto2 };
		
		ImageIcon icono = null;
		try {
			icono = new ImageIcon(EjemploJOptionPaneComponentes.class.getResource("/coffee-icon.png"));
		} catch (Exception e) {
			System.out.println("No se pudo cargar la imagen");
		}

		// Mostrar el JOptionPane con los componentes en un array
		int resultado = JOptionPane.showConfirmDialog(null, 
			message, 
			"Introduce los datos", 
			JOptionPane.OK_CANCEL_OPTION,
			JOptionPane.PLAIN_MESSAGE,
			icono
		);

		// Verificar si se presionó OK
		if (resultado == JOptionPane.OK_OPTION) {
			// Obtener los valores ingresados
			String valor1 = campoTexto1.getText();
			String valor2 = campoTexto2.getText();

			System.out.format("Valor 1: %s, Valor 2: %s%n", valor1, valor2);
		} else {
			System.out.println("Operación cancelada");
		}
	}
}

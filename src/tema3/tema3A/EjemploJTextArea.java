package tema3.tema3A;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * Este ejemplo muestra cómo se utiliza un JTextArea
 */
public class EjemploJTextArea extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public EjemploJTextArea() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Ejemplo Swing");

		JTextArea textArea = new JTextArea();
		textArea.setRows(15);
		textArea.setColumns(30);
		textArea.setLineWrap(true);

		textArea.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				System.out.println("Se ha borrado texto");
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				System.out.println("Se ha insertado texto");
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				System.out.println("Se ha cambiado texto");
			}
		});

		JScrollPane scrollPane = new JScrollPane(textArea);
		add(scrollPane, BorderLayout.CENTER);

		JButton printButton = new JButton("Imprimir texto seleccionado");
		printButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String selectedText = textArea.getSelectedText();
				System.out.println(selectedText);
			}

		});

		add(printButton, BorderLayout.SOUTH);

		pack();
		setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new EjemploJTextArea();
			}

		});
	}
}

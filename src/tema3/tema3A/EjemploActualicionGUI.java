package tema3.tema3A;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class EjemploActualicionGUI extends JFrame {

	public EjemploActualicionGUI() {
		
		// Configuración de la ventana
		setTitle("Selección de Aeropuertos");
		setSize(400, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null); // Centrar la ventana

		// Datos que relacionan aeropuertos de origen y desitno
		Map<String, List<String>> mapaAeropuertos = new HashMap<>();

        // Añadir aeropuertos de origen y sus destinos
        mapaAeropuertos.put("Madrid - Barajas", Arrays.asList("New York - JFK", "Los Ángeles - LAX", "Barcelona - El Prat"));
        mapaAeropuertos.put("Barcelona - El Prat", Arrays.asList("Madrid - Barajas", "Tokyo - Haneda", "London - Heathrow"));
        mapaAeropuertos.put("New York - JFK", Arrays.asList("Los Ángeles - LAX", "Madrid - Barajas", "Paris - Charles de Gaulle"));
        mapaAeropuertos.put("Tokyo - Haneda", Arrays.asList("New York - JFK", "Los Ángeles - LAX", "Paris - Charles de Gaulle"));

        // Inicializar los datos 
        String[] origenes = mapaAeropuertos.keySet().toArray(new String[mapaAeropuertos.size()]);
		JComboBox<String> comboOrigen = new JComboBox<>(origenes);
		
		List<String> destinos = mapaAeropuertos.get(comboOrigen.getSelectedItem());
		JComboBox<String> comboDestino = new JComboBox<>(destinos.toArray(new String[destinos.size()]));

		// Crear etiquetas para los JComboBox
		JLabel etiquetaOrigen = new JLabel("Aeropuerto de origen:");
		JLabel etiquetaDestino = new JLabel("Aeropuerto de destino:");

		// Botón para mostrar la selección
		JButton botonMostrar = new JButton("Mostrar selección");

		// Panel para colocar los componentes
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 2, 10, 10)); // 3 filas, 2 columnas, 10px de margen

		// Añadir componentes al panel
		panel.add(etiquetaOrigen);
		panel.add(comboOrigen);
		panel.add(etiquetaDestino);
		panel.add(comboDestino);
		panel.add(new JLabel()); // Espacio vacío
		panel.add(botonMostrar);

		// Acción para mostrar la selección
		botonMostrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String origen = (String) comboOrigen.getSelectedItem();
				String destino = (String) comboDestino.getSelectedItem();

				// Mostrar un cuadro de diálogo con la selección
				JOptionPane.showMessageDialog(null, "Origen: " + origen + "\nDestino: " + destino);
			}
		});
		
		// añadir un escuchador al evento de selección del desplegable de origen
		comboOrigen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// obtenemos el origen seleccionado actualmente
				String origenSeleccionado = (String) comboOrigen.getSelectedItem();
				
				// obtenemos la lista de destinos para ese origen
				List<String> destinos = mapaAeropuertos.get(comboOrigen.getSelectedItem());

				// actualizamos el combobox de destinos cambiando su modelo de datos
				comboDestino.setModel(new DefaultComboBoxModel<String>(destinos.toArray(new String[destinos.size()])));
			}
		});

		// Añadir el panel a la ventana
		add(panel);

		// Hacer la ventana visible
		setVisible(true);
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			System.out.println("No se pudo establecer el Look & Feel");
		}
		
		// Crear y mostrar la ventana
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new EjemploActualicionGUI();
			}
		});
	}
}

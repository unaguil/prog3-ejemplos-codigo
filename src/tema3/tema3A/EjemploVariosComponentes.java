package tema3.tema3A;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerModel;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

/**
 * Este ejemplo muestra el uso de distintos componentes
 */
public class EjemploVariosComponentes extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public EjemploVariosComponentes() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Ejemplo Swing");

        // se indican los valores máximos, minimos y actual del slider
        JSlider slider = new JSlider(-10, 10, 0);
        slider.setMajorTickSpacing(5);
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        // lista de valores contenidos en el spinner
        // es necesario asignar los valores a traves de un modelo
        String[] days = { "Lunes", "Martes", "Miercoles", "Jueves", "Viernes" };
        SpinnerModel spinnerModel = new SpinnerListModel(days);
        JSpinner spinner = new JSpinner(spinnerModel);

        // se crea una barra de progreso de 0 a 100
        // que será actualizada por el thread
        JProgressBar progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true); // muestra el número y porcentaje

        JButton startButton = new JButton("Iniciar thread");
        startButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                Thread t = new Thread(new Runnable() {

                    @Override
                    public void run() {
                        startButton.setEnabled(false);

                        for (int i = 0; i < 100; i++) {
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                //
                            }

                            final int value = i;
                            // actualizar la barra de progreso
                            // cuidado se accede a Swing desde otro thread
                            // hay que usar SwingUtilities
                            SwingUtilities.invokeLater(new Runnable() {

                                @Override
                                public void run() {
                                    progressBar.setValue(value);
                                }

                            });
                        }

                        startButton.setEnabled(true);
                    }

               });

               t.start();
            }

        });

        JPanel centerPanel = new JPanel();
        centerPanel.add(spinner);
        add(centerPanel, BorderLayout.CENTER);
        
        JPanel topPanel = new JPanel();
        topPanel.add(slider);
        add(topPanel, BorderLayout.NORTH);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(startButton);
        bottomPanel.add(progressBar);
        add(bottomPanel, BorderLayout.SOUTH);

        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new EjemploVariosComponentes();
            }

        });
    }
}

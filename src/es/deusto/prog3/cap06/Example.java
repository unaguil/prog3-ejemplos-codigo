package es.deusto.prog3.cap06;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Example extends JFrame {

    public Example() {
        JButton showDatePicker = new JButton();

        pack();
        setVisible(true);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            
            @Override
            public void run() {
                new Example();
            }            

        });
    }
}

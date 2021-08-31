import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;


// Este programa muestra cómo se lee un fichero que
// contiene datos serializados en el formato de Java.

public class EjemploSerializacionEntrada {
    
    public static void main(String[] args) {
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream("datos.bin"))) {
            Persona p = (Persona) is.readObject();
            System.out.println(p);
        } catch (IOException e) {
            System.out.println("Error. No se pudo deserializar el objeto. " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Error. No se pudo encontrar la clase asociada. " + e.getMessage());
        }
    }
}

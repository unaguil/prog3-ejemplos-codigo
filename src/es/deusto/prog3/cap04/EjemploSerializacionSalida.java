package es.deusto.prog3.cap04;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import es.deusto.prog3.cap04.serializable.Persona;

public class EjemploSerializacionSalida {
    
    public static void main(String[] args) {
        Persona p = new Persona("John", "Smith", 20);

        // Creamos un stream de salida de objetos a fichero
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("datos.bin"))) {
            os.writeObject(p);
            System.out.println("Datos serializados correctamente");
        } catch (IOException e) {
            System.out.println("Error al serializar los datos al fichero");
        }
    }
}

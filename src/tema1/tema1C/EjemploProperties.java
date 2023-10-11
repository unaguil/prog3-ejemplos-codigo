package tema1.tema1C;

import java.util.Properties;
import java.io.FileReader;
import java.io.IOException;


// Este ejemplo muestra como leer un fichero de propiedades
// que puede contener cierta configuración del programa.

public class EjemploProperties {

    public static void main(String[] args) {
        try (FileReader reader = new FileReader("ficheros/ejemplo.properties")) {
            // Se crea el objeto y se leen las propiedades del fichero
            Properties properties = new Properties();
            properties.load(reader);

            // Se puede acceder al valor de las propiedades por nombre
            String database = properties.getProperty("database");
            String dataDir = properties.getProperty("datadir");
            String permission = properties.getProperty("permission");

            System.out.println("Database: " + database);
            System.out.println("Datadir: " + dataDir);
            System.out.println("Permission: " + permission);

        } catch (IOException e) {
            System.out.println("No se pudo leer el fichero de propiedades");
        }
    }
}
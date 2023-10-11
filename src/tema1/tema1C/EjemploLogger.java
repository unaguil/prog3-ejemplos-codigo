package tema1.tema1C;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;


// En este ejemplo se muestra la utilización de las classes
// de logging de Java para sacar información de traza. Se recomienda
// utilizar esta opción para proporcionar información del programa en vez
// de utilizar la salida directa por consola con System.out.

public class EjemploLogger {

    // Creamos el logger con el pasando como parámetro el nombre de la clase actual
    private static Logger logger = Logger.getLogger(EjemploLogger.class.getName());

    // Niveles de importancia para el log:
	// FINEST / FINER / FINE / CONFIG / INFO / WARNING / SEVERE
	// Por defecto se muestran en consola de error solo INFO-WARNING-SEVERE
    
    public static void main(String[] args) {
        // Cambios el nivel de salida del log para sacar los mensajes
        // FINE-CONFIG-INFO-WARNING-SEVERE
        // Aunque se puede configurar por código el nivel del logger, lo usual es 
        // hacerlo en un fichero externo 
        // Logger.getLogger("").setLevel(Level.SEVERE);
        // Logger.getLogger("").getHandlers()[0].setLevel(Level.SEVERE);
    	
        try (FileInputStream fis = new FileInputStream("src/tema1/tema1C/logger.properties")) {
            LogManager.getLogManager().readConfiguration(fis);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "No se pudo leer el fichero de configuración del logger");
        }

        // Empieza el programa y el los mensajes de log que indican
        // lo que va ocurriendo.
        logger.info("Programa comenzado");

        // Sacamos esta información con un nivel más detallado
        for (int i = 0; i < 10; i++) {
            logger.log(Level.FINE, "Voy por la iteración " + i);
        }

        // Vamos a intentar abrir un fichero que no existe para producir
        // un fallo y sacarlo por el logger.
        try (FileInputStream fis = new FileInputStream("noexiste.txt")) {

        } catch (FileNotFoundException e) {
            logger.log(Level.SEVERE, "No se ha encontrado el fichero 'noexiste.txt");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "No se ha encontrado el fichero 'noexiste.txt");
        }

        logger.info("Programa finalizado");
    }
}

import java.util.GregorianCalendar;
import java.util.List;

public class DBExample {

    public static void main(String[] args) {
        // Se crea la clase que implementa la funcionalidad de base de datos
        // y conectamos con el fichero de base de datos

        // Este programa supone que el fichero manager.db se encuentra vacío
        // y crea las tablas al comenzar.
        // Si el fichero SQLIte no existe se crea vacío
        DBManager dbManager = new DBManager();
        try {
            System.out.println("Conectando con la base de datos...");
            dbManager.connect("ficheros/manager.db");

            // Creamos la tabla de usuario al iniciar el programa.
            dbManager.createUserTable();

            // Vamos a insertar 5 usuarios con nombres, apellidos y fechas.
            System.out.println("Introduciendo usuarios ...");
            for (int i = 1; i <= 5; i++) {
                User user = new User();
                user.setName("Nombre_" + i);
                user.setSurname("Apellido_" + i);
                
                GregorianCalendar birthDate = new GregorianCalendar(1900 + 20 * i, i * 3, i);
                user.setBirthdate(birthDate.toZonedDateTime());

                // Se guarda el usuario en la base de datos y
                // el id es generado automáticamente.
                dbManager.store(user);
            }

            // Recuperamos un usuario de la base de datos.
            // En este ejemplo podemos adivinar el id porque la tabla
            // se crea de cero al iniciar el programa y los ids generados
            // son consecutivos.
            System.out.println("Recuperando usuario con id '2'");
            User user = dbManager.getUser(2);
            System.out.println("Usuario recuperado: " + user);

            // Se borra el usuario recuperado.
            dbManager.delete(user);

            // Recuperamos el usuario '3' para modificar sus datos.
            user = dbManager.getUser(3);
            user.setName("John");
            user.setSurname("Smith");

            // Usamos el método para que se actualicen los datos en la
            // base de datos
            dbManager.update(user);

            // Se recuperan todos los usuarios de la base de datos
            // en una lista. Comprobamos que se ha borrado el usuario '2'
            // y que se ha modificado el usuario '3'.
            List<User> users = dbManager.getAllUsers();
            for (User u : users) {
                System.out.println("Usuarios en la base de datos");
                System.out.println(u);
            }

            // Al terminar se borra la tabla de usuarios de la base de datos.
            // Esto no es lo normal ya que queremos que los datos se mantengan
            // entre ejecuciones del programa, pero sirve como ejemplo.
            dbManager.dropUserTable();


            // Se cierra la conexión a la base de datos.
            dbManager.disconnect();

        } catch (DBException e) {
            System.out.println("Error. Se ha producido un error al acceder a la base de datos.");
            // Imprimimos la pila de llamadas para poder depurar los posibles errores.
            e.printStackTrace();
        }
    }
}

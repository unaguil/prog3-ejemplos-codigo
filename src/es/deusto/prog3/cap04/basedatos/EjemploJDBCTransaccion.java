package es.deusto.prog3.cap04.basedatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

// En este programa se muestran los pasos para
// conectar a una base de datos SQLite ya existente.
// En SQLite la base de datos es un fichero que
// contiene una única tabla de "usuarios".
// Una vez conectado a la base de datos, se hace una
// consulta básica para obtener el "nombre" y "apellido"
// de todos los usuarios.

// Este ejemplo hace uso de las transacciones para introducir
// 3 usuarios. Se le pregunta al usuario si quiere confirmar
// o deshacer la transacción.

public class EjemploJDBCTransaccion {

	public static void main(String[] args) { 
		// Antes de conectar con la BD se debe cargar el driver.
		// Este paso solamente se hace una vez en el programa.
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			// Puede ser que no encuentre la clase a cargar.
			// Esto puede deberse a que el .jar necesario no está referenciado.
			System.out.println("No se ha podido cargar el driver de la base de datos");
		}
		
		// Ahora se usa JDBC para conectar con la base de datos.
		// Este proceso es común a todas las bases de datos compatibles
		// con JDBC. Pero cambiará el string de conexión según el modelo
		// de base de datos utilizado. Es necesario consultar la documentación
		// del driver y/o de la base de datos.
		
		// Para SQLite es suficiente con indicarle la ruta al fichero que
		// contiene la base de datos completa.
		// El formato de este string de conexión para SQLIte es:
		// jdbc:sqlite:ruta_al_fichero
		
		try (Connection conn = DriverManager.getConnection("jdbc:sqlite:ficheros/ejemplo.db")) {

            // Se cambia el modo para permitir transacciones
            conn.setAutoCommit(false);

			// A partir de una conexión activa obtenemos el objeto para ejecutar
			// sentencias SQL en la base de datos.
			try (Statement stmt = conn.createStatement()) {
			
				// Como vamos a realizar una consulta usamos el método para queries.
				// La base de datos usada en este ejemplo contiene una tabla "usuarios".
				// Esta tabla contiene dos columnas: nombre y apellido
				
				// Si la consulta se ejecuta correctamente el resultado se devuelve
				// en un objeto de tipo ResultSet sobre el que podemos iterar para obtener
				// cada fila devuelta como resultado de la consulta.
				try (ResultSet rs = stmt.executeQuery("SELECT nombre, apellido FROM usuarios")) {
					while (rs.next()) {
						// Mientras tenga filas
						// Cogemos cada columna contenida en la fila
						
						String name = rs.getString("nombre");
						String surname = rs.getString("apellido");
						
						// y hacemos algo con esos datos: imprimir, crear un objeto, etc
						System.out.println(name + " " + surname);
					}
				} catch (SQLException e) {
					System.out.println("No se ha podido ejecutar la sentencia SQL." + e.getMessage());
				}

			} catch (SQLException e) {
				// No se ha podido obtener la conexión a la base de datos
				System.out.println("Error. No se ha podido crear el statement " + e.getMessage());
			}

			// Insertamos una nueva fila en la base de datos. Leemos los datos de la entrada.
			try (Scanner sc = new Scanner(System.in)) {
                // Se prepara la plantilla que se va a reutilizar
                try (PreparedStatement insert = conn.prepareStatement("INSERT INTO usuarios VALUES (?, ?, ?)")) {

                    for (int i = 0; i < 3; i++) {
                        System.out.println();
                        System.out.println();
                        System.out.println("Datos del nuevo usuario:");
                        System.out.print("Nombre: ");
                        String name = sc.nextLine();

                        System.out.print("Apellido: ");
                        String surname = sc.nextLine();

                        System.out.print("Edad: ");
						int age = sc.nextInt();
						sc.nextLine();
                    
                        // Se rellenan los huecos de la PreparedStatement con los datos
                        insert.setString(1, name);
                        insert.setString(2, surname);
                        insert.setInt(3, age);

                        // se ejecuta la prepared statement
                        insert.executeUpdate();
                    }

                    System.out.print("¿Quiere guardar todos los datos introducidos? (s/N): ");
                    String response = sc.nextLine();

                    if (response.toLowerCase().equals("s")) {
                        System.out.println("Datos guardados...");
                        conn.commit();
                    } else {
                        System.out.println("Deshaciendo cambios...");
                        conn.rollback();
                    }

				} catch (SQLException e) {
					System.out.println("Error. No se han podido insertar los datos. " + e.getMessage());
				}
			}
			
		} catch (SQLException e) {
			// No se ha podido obtener la conexión a la base de datos
			System.out.println("Error. No se ha podido conectar a la base de datos. " + e.getMessage());
		}
	}
}

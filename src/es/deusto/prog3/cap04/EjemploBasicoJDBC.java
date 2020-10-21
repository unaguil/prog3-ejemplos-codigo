package es.deusto.prog3.cap04;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// En este programa se muestran los pasos para
// conectar a una base de datos SQLite ya existente.
// En SQLite la base de datos es un fichero que
// contiene una única tabla de "usuarios".
// Una vez conectado a la base de datos, se hace una
// consulta básica para obtener el "nombre" y "apellido"
// de todos los usuarios.

public class EjemploBasicoJDBC {

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
		
		try {
			Connection conn = DriverManager.getConnection("jdbc:sqlite:ejemplo.db");
			
			// A partir de una conexión activa obtenemos el objeto para ejecutar
			// sentencias SQL en la base de datos.
			Statement stmt = conn.createStatement();
			
			// Como vamos a realizar una consulta usamos el método para queries.
			// La base de datos usada en este ejemplo contiene una tabla "usuarios".
			// Esta tabla contiene dos columnas: nombre y apellido
			
			// Si la consulta se ejecuta correctamente el resultado se devuelve
			// en un objeto de tipo ResultSet sobre el que podemos iterar para obtener
			// cada fila devuelta como resultado de la consulta.
			ResultSet rs = stmt.executeQuery("SELECT nombre, apellido FROM usuarios");
			while (rs.next()) {
				// Mientras tenga filas
				// Cogemos cada columna contenida en la fila
				
				String name = rs.getString("nombre");
				String surname = rs.getString("apellido");
				
				// y hacemos algo con esos datos: imprimir, crear un objeto, etc
				System.out.println(name + " " + surname);
			}
			
			rs.close(); // es necesario cerrar el resultado al terminar de usarlo
			
			stmt.close(); // Es necesario cerrar el statement si no se usa más
			
			conn.close(); // Es necesario cerrar la conexión si no se usa más 
		} catch (SQLException e) {
			// No se ha podido obtener la conexión a la base de datos
			System.out.println("Error. No se ha podido conectar a la base de datos. " + e.getMessage());
		}
	}
}

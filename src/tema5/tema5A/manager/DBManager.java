package tema5.tema5A.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Esta clase gestiona el acceso a la base de datos proporcionando métodos para conectar/desconectar y
 * para la realización de operaciones sobre las distintas tablas.
 * En este ejemplo se utilizan PreparedStatements para parametrizar las consultas SQL (opción recomendada).
 */
public class DBManager {

	private Connection conn = null; 
	
	/**
	 * Crea una conexión con la base de datos.
	 * @throws DBException Se produce cuando existe un problema con la creación de la conexión a la BD.
	 */
	public void connect(String dbPath) throws DBException {
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
		} catch (ClassNotFoundException e) {
			throw new DBException("Error cargando el driver de la BD", e);
		} catch (SQLException e) {
			throw new DBException("Error conectando a la BD", e);
		}
	}
	
	/**
	 * Cierra una conexión con la BD.
	 * @throws DBException Se produce cuando existe un error a la hora de conectar con la BD.
	 */
	public void disconnect() throws DBException {
		try {
			conn.close();
		} catch (SQLException e) {
			throw new DBException("Error cerrando la conexión con la BD", e);
		}
	}
		
	/**
	 * Obtiene la lista de todos los usuarios.
	 * @return lista con todos los usuarios
	 * @throws DBException Se produce si existe un error al obtener la lista de usuarios de la BD.
	 */
	public List<User> getAllUsers() throws DBException {
		List<User> users = new ArrayList<User>();
		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery("SELECT id, name, surname, birthdate FROM user");

			while(rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setSurname(rs.getString("surname"));
				user.setBirthdate(ZonedDateTime.parse(rs.getString("birthdate")));
				users.add(user);
			}
			
			return users;
		} catch (SQLException | DateTimeParseException e) {
			throw new DBException("Error obteniendo todos los usuarios'", e);
		}
	}
	
	/**
	 * Obtiene el usuario con el id indicado como parámetro.
	 * @param id ID del usuario a obtener de la BD
	 * @return El usuario cuyo ID se encuentra en la BD. Devuelve un usuario vacio con id -1 si no se ha encontrado el usuario.
	 * @throws DBException
	 */
	public User getUser(int id) throws DBException {
		try (PreparedStatement stmt = conn.prepareStatement("SELECT id, name, surname, birthdate FROM user WHERE id = ?")) {
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setSurname(rs.getString("surname"));
				user.setBirthdate(ZonedDateTime.parse(rs.getString("birthdate")));
				return user;
			} else {
				return new User();
			}
		} catch (SQLException | DateTimeParseException e) {
			throw new DBException("Error obteniendo el usuario con id " + id, e);
		}
	}
	
	/**
	 * Obtiene todos los usuarios nacidos antes de la fecha indicada
	 * @param date
	 * @return
	 * @throws DBException 
	 */
	public List<User> getUsersBirthBeforeDate(Date date) throws DBException {
		List<User> users = new ArrayList<User>();
		try (PreparedStatement stmt = conn.prepareStatement("SELECT id, name, surname, birthdate FROM user WHERE birthdate <= ?")) {
			stmt.setString(1, date.toString());
			
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setSurname(rs.getString("surname"));
				user.setBirthdate(ZonedDateTime.parse(rs.getString("birthdate")));
				users.add(user);
			}
		} catch (SQLException | DateTimeParseException e) {
			throw new DBException("Error obteniendo todos los usuarios nacidos antes de " + date, e);
		}
		
		return users;
	}
	
	/**
	 * Guarda el usuario por primera vez en la BD generando automáticamente un id.
	 * @param user el objeto que debe ser guardado en la BD (el id es ignorado ya que se genera uno nuevo).
	 * @throws DBException Si se produce un error al guardar el usuario en la BD.
	 */
	public void store(User user) throws DBException {
		try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO user (name, surname, birthdate) VALUES (?, ?, ?)");
			Statement stmtForId = conn.createStatement()) {
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getSurname());
			stmt.setString(3, user.getBirthdate().toString());
			
			stmt.executeUpdate();
			
			//obtiene el id que se acaba de autogenerar
			ResultSet rs = stmtForId.executeQuery("SELECT last_insert_rowid() AS id FROM user");
			if (rs.next()) {
				int newId = rs.getInt("id");
				user.setId(newId);
			} else {
				throw new DBException("Error generando el id autoincremental");
			}
		} catch (SQLException e) {
			throw new DBException("No se pudo guardar el usuario en la BD", e);
		}
	}
	
	/**
	 * Actualiza un usuario que ya existe en la base de datos (el id debe existir y no cambia)
	 * @param user Usuario cuyos datos deben ser actualizados en la base de datos
	 * @throws DBException Si se produce un error durante la actualización de los datos
	 */
	public void update(User user) throws DBException {
		try (PreparedStatement stmt = conn.prepareStatement("UPDATE user SET name=?, surname=?, birthdate=? WHERE id=?")) {
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getSurname());
			stmt.setString(3, user.getBirthdate().toString());
			stmt.setInt(4, user.getId());
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new DBException("No se pudo guardar el usuario en la BD", e);
		}
	}
	
	/**
	 * Elimina un usuario de la base de datos
	 * @param user Usuario que debe ser eliminado de la base de datos
	 * @throws DBException Si se produce un error al eliminar el usuario de la BD.
	 */
	public void delete(User user) throws DBException {
		try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM user WHERE id=?")) {
			stmt.setInt(1, user.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new DBException("No se pudo elimiar el usuario con id " + user.getId(), e);
		}
	}

	// Estos métodos se incluyen aquí para mostrar que también se puede gestionar la BD desde
	// el programa para crear y/o borrar tablas

	/**
	 * Crea la tabla 'user' si no existe
	 * @throws DBException Se produce cuando existe un error en la creación de la tabla 'user'
	 */
	public void createUserTable() throws DBException {
		try (Statement stmt = conn.createStatement()) {
			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS user (id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, surname VARCHAR, birthdate VARCHAR)");
		} catch (SQLException e) {
			throw new DBException("Error creando la tabla 'user' en la BD", e);
		}
	}

	/**
	 * Borra la tabla 'user' si existe
	 * @throws DBException Se produce cuando existe un error al realizar el borrado de la tabla 'user'
	 */
	public void dropUserTable() throws DBException {
		try (Statement stmt = conn.createStatement()) {
			stmt.executeUpdate("DROP TABLE IF EXISTS user");
		} catch (SQLException e) {
			throw new DBException("Error borrando la tabla 'user' en la BD", e);
		}
	}
}

package tema7.manager;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Esta clase representa un usuario y contiene métodos 
 * y hace uso de métodos de utilidad para guardar la
 * fecha de nacimiento en la base de datos como un
 * String en formato ISO.
 */
public class User {

	private int id = -1;
	private String name;
	private String surname;
	private ZonedDateTime birthdate; 
	
	public User() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ZonedDateTime getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(ZonedDateTime birthdate) {
		this.birthdate = birthdate;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Override
	public String toString() {
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-YYYY");
		return String.format(
			"%s, %s -> Nacimiento: %s",
			name,
			surname,
			dateFormatter.format(birthdate)
		);
	}
	
}

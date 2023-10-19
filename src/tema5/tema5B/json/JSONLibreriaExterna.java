package tema5.tema5B.json;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import tema3.tema3A.Person;

// Ejemplo que muestra como escribir y leer desde fichero un conjunto de datos
// en formato JSON utilizando la librería externa json simple.
// https://code.google.com/archive/p/json-simple/

public class JSONLibreriaExterna {

	public static void main(String[] args) {
		// datos de ejemplo
		List<Person> persons = Arrays.asList(
			new Person("Enrico", "Fermi", LocalDate.of(1901, 9, 29)),
			new Person("Albert", "Einstein", LocalDate.of(1879, 4, 14)),
			new Person("Marie", "Curie", LocalDate.of(1867, 11, 07))
		);
		
		// abrir un fichero de texto para escribir la serialización de cada objeto en JSON
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("example.json"))) {
			JSONArray jsonArray = toJSON(persons, writer);
			writer.write(jsonArray.toJSONString()); // se escribe el array como JSON
		} catch (IOException e) {
			System.out.println("Error escribiendo fichero JSON." + e.getLocalizedMessage());
		}
		
		// leemos el contenido del fichero JSON
		try (FileReader reader = new FileReader("example.json")) {
			JSONArray jsonArray = (JSONArray) JSONValue.parse(reader);
		
			// iteramos el array para obtener cada objecto y procesarlo
			int index = 0;
			while (index < jsonArray.size()) {
				JSONObject jsonObject = (JSONObject) jsonArray.get(index);
				System.out.println(String.format("Name: %s", jsonObject.get("name")));
				System.out.println(String.format("Surname: %s", jsonObject.get("surname")));
				System.out.println(String.format("Birthdate: %s", jsonObject.get("birthdate")));
				
				index++;
			}
		} catch (IOException e) {
			System.out.println("Error leyendo fichero JSON." + e.getLocalizedMessage());
		}	
	}

	@SuppressWarnings("unchecked")
	private static JSONArray toJSON(List<Person> persons, BufferedWriter writer) throws IOException {
		JSONArray jsonArray = new JSONArray(); // array en formato json para contener cada objeto JSON
		for (Person p : persons) {
			// se convierten los datos al objeto JSON
			JSONObject jsonObject = toJSON(p);
			jsonArray.add(jsonObject); // se añade al array Json
		}
		return jsonArray;
	}
	
	@SuppressWarnings("unchecked")
	public static JSONObject toJSON(Person p ) {
		// serializa una persona a JSON
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("name", p.getName());
		jsonObj.put("surname", p.getSurname());
		jsonObj.put("birthdate", p.getBirthDate().toString());
		return jsonObj;
	}
}

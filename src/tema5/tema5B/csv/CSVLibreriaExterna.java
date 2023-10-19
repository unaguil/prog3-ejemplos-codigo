package tema5.tema5B.csv;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.supercsv.io.CsvMapReader;
import org.supercsv.io.CsvMapWriter;
import org.supercsv.io.ICsvMapReader;
import org.supercsv.io.ICsvMapWriter;
import org.supercsv.prefs.CsvPreference;

import tema3.tema3A.Person;

// Ejemplo que muestra como escribir y leer desde fichero un conjunto de datos
// en formato CSV utilizando la librería externa SuperCSV.
// https://super-csv.github.io/super-csv/
public class CSVLibreriaExterna {

	public static void main(String[] args) {
		// datos de ejemplo
		List<Person> persons = Arrays.asList(
			new Person("Enrico", "Fermi", LocalDate.of(1901, 9, 29)),
			new Person("Albert", "Einstein", LocalDate.of(1879, 4, 14)),
			new Person("Marie", "Curie", LocalDate.of(1867, 11, 07))
		);
		
		// cabecera del fichero CSV
		String[] headers = {"Name", "Surname", "Birthdate" };
		
		// se crea el writer para escribir en el CSV
		try (ICsvMapWriter csvWriter = new CsvMapWriter(new FileWriter("example.csv"), CsvPreference.STANDARD_PREFERENCE)) {
			csvWriter.writeHeader(headers); // se escribe la cabecera
			
			for (Person p : persons) {
				// se crea el mapa de la fila
				Map<String, String> row = new HashMap<>();
				row.put(headers[0], p.getName());
				row.put(headers[1], p.getSurname());
				row.put(headers[2], p.getBirthDate().toString());
				
				// se escribe la fila en el fichero CSV
				csvWriter.write(row, headers);
			}
		} catch (IOException e) {
			System.out.println("Error escribiendo fichero CSV." + e.getLocalizedMessage());
		}
		
		// se crea el reader para leer desde un CSV
		try (ICsvMapReader csvReader = new CsvMapReader(new FileReader("example.csv"), CsvPreference.STANDARD_PREFERENCE)) {
			Map<String, String> row;

			csvReader.getHeader(false);
			while ((row = csvReader.read(headers)) != null) {
				System.out.println(String.format("Name: %s", row.get("Name")));
				System.out.println(String.format("Surname: %s", row.get("Surname")));
				System.out.println(String.format("Birthdate: %s", row.get("Birthdate")));
			}
		} catch (FileNotFoundException e) {
			System.out.println("Error leyendo fichero CSV." + e.getLocalizedMessage());
		} catch (IOException e) {
			System.out.println("Error leyendo fichero CSV." + e.getLocalizedMessage());
		}
	}
}

package tema4.tema4A.map;

import java.util.Map.Entry;
import java.util.HashMap;
import java.util.Map;

// Este ejemplo muestra cómo utilizar un Map
// que guarda relaciones clave-valor.

public class EjemploMap {
    
    public static void main(String[] args) {
        /// En este caso se utiliza un mapa de String->Integer
        // Como ambos son clases que pertenencen al lenguaje Java 
        // no es necesario definir nada para que funcione.
        Map<String, Integer> map = new HashMap<String, Integer>();

        map.put("John", 20);
        map.put("Jenny", 24);

        System.out.println(map.get("John"));

        // se itera sobre las claves
        for (String s : map.keySet()) {
            System.out.println(s);
        }

        // se itera sobre los valores
        for (Integer i : map.values()) {
            System.out.println(i);
        }

        // se itera sobre la clave-valor directamente
        for (Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // determinar si una clave está en el mapa
        System.out.println(map.containsKey("John"));

        // determinar si un valor está en el mapa
        System.out.println(map.containsValue(20));

        // borrado del mapa
        map.clear();


        // En este caso se crea un mapa que asocia objetos PhoneNumber con objetos Person
        // Como la implementación es un HashMap, los objetos de la clave deben definir también
        // un hashCode ya que implementan un equals.
        Map<PhoneNumber, Person> phoneNumbers = new HashMap<PhoneNumber, Person>();
        phoneNumbers.put(new PhoneNumber("3333", "5555", "6666", "Home"), new Person("123456789X", "John", "Doe"));
        phoneNumbers.put(new PhoneNumber("4231", "2121", "2423", "Home"), new Person("345612343A", "Albert", "Einstein"));
        phoneNumbers.put(new PhoneNumber("6484", "3333", "8575", "Home"), new Person("999999999W", "Richard", "Strauss"));

        // Se obtiene el objeto asociado a la clave pasada
        // El nombre asociado al teléfono no participa en la implementación del equals/hashcode.
        // Si se comenta el método hashCode de PhoneNumber, no se encuentra la clave en el map y devuelve null
        System.out.println(phoneNumbers.get(new PhoneNumber("4231", "2121", "2423", "Other")));

        // Este método comprueba si la clave existe ya en el mapa.
        // Es muy eficiente ya que utiliza la organización en base al hashcode.
        System.out.println(phoneNumbers.containsKey(new PhoneNumber("4231", "2121", "2423", "Other")));

        // En este caso la búsqueda se hace sobre las claves, por lo que se usa el equals de Person
        // Este método de búsqueda sobre los valores no es tan eficiente como la búsqueda sobre claves
        // porque los valores no están organizados por hash.
        System.out.println(phoneNumbers.containsValue(new Person("999999999W", "Richard", "Strauss")));
    }
}

package tema4.tema4A.map.tree;

import java.util.Comparator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.Map.Entry;

/**
 * Este ejemplo muestra cómo utilizar un TreeMap para obtener un Map
 * en el que las claves se encuentran ordenadas y pueden ser recorridas
 * en orden. 
 */
public class EjemploTreeMap {

    // Define un comparator entre instancias de Person que se basa
    // en determinar el orden comparando su apellido
    static class SurnameComparator implements Comparator<Person> {

        @Override
        public int compare(Person a, Person b) {
            return a.getSurname().compareTo(b.getSurname());
        }
        
    }
    
    public static void main(String[] args) {
        // En este caso se usa el orden natural de la clase
        // utilizada en la clave del map y que está
        // definido por la implementación de la interfaz
        // Comparable<T> de la clase Person
        Map<Person, String> personsMap = new TreeMap<>();

        // los valores se auto-ordenan en el mapa por su clave
        // no puede haber elementos con clave repetida
        personsMap.put(new Person(7, "Albert", "Einstein"), "10");
        personsMap.put(new Person(1, "Marie", "Curie"), "15");
        personsMap.put(new Person(2, "Enrico", "Fermi"), "30"); 

        // al añadir este valor, como la clave ya existe en el mapa,
        // de acuerdo al Comparable de Person, el valor asociado
        // a la clave ya existente, se substituye por el nuevo valor
        personsMap.put(new Person(1, "Richard", "Feynman"), "2");

        System.out.println("Orden natural de las claves (por id):");
        // se imprimen las claves del mapa, que están ordenadas
        // se observa en la salida que la clave no ha sido duplicada
        // al insertar el último elemento "1, Richard, Feynman"
        for (Person p : personsMap.keySet()) {
            System.out.println(p);
        }

        System.out.println();

        System.out.println("Orden de entradas por id:");
        // también se puede recorrer la pareja clave-valor en orden
        for (Entry<Person, String> entry : personsMap.entrySet()) {
            System.out.println(String.format("%s -> %s", entry.getKey(), entry.getValue()));
        }

        System.out.println();

        // los datos se pasan a otro TreeMap construido para ordenar un
        // comparador especifico
        Map<Person, String> personsMapBySurname = new TreeMap<>(new SurnameComparator());
        personsMapBySurname.putAll(personsMap);

        System.out.println("Orden de entradas por apellido:");

        // ahora al recorrer en orden, el treemap está ordenado por otro criterio
        for (Entry<Person, String> entry : personsMapBySurname.entrySet()) {
            System.out.println(String.format("%s -> %s", entry.getKey(), entry.getValue()));
        }

        // como el TreeMap implementa la interfaz NavigableMap se puede usar para
        // obtener el recorrido inverso directamente
        NavigableMap<Person, String> navigableMap = (NavigableMap<Person, String>) personsMapBySurname;
     
        System.out.println("Orden de entradas por apellido inverso:");

        // ahora al recorrer en orden, el treemap está ordenado por otro criterio
        for (Entry<Person, String> entry : navigableMap.descendingMap().entrySet()) {
            System.out.println(String.format("%s -> %s", entry.getKey(), entry.getValue()));
        }
    }
}

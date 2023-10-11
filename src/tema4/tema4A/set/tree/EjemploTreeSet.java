package tema4.tema4A.set.tree;

import java.util.Comparator;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Este ejemplo muestra el uso de un TreeSet que ordena los elementos
 * automáticamente.
 * 
 * Se puede usar el orden natural de la clase o especificar un comparador
 */
public class EjemploTreeSet {

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
        // definido por la implementación de la interfaz
        // Comparable<T> de Person
        Set<Person> persons = new TreeSet<>();

        // se añaden las personas de ejemplo que son ordenadas
        // por el treeset según su orden natural
        persons.add(new Person(7, "Albert", "Einstein"));
        persons.add(new Person(1, "Marie", "Curie"));
        persons.add(new Person(2, "Enrico", "Fermi")); 
        persons.add(new Person(1, "Richard", "Feynman"));

        // el set no puede contener elementos repetidos
        // por lo que aunque hemos añadido 4 personas solamente
        // contiene 3, ya que la última tiene una "clave" repetida
        // y no ha sido añadida al TreeSet
        System.out.println("Personas: " + persons.size());
        System.out.println();

        // Se recorren las personas en el orden establecido
        // por el TreeSet.
        System.out.println("Orden natural (por id):");
        for (Person p : persons) {
            System.out.println(p);
        }

        System.out.println();

        // Se puede crear un TreeSet definiendo un comparador
        // específico, en vez de utilizar el orden natural.
        // en este caso se va a utilizar un comparador para
        // ordenar por apellido.
        Set<Person> personsBySurname = new TreeSet<>(new SurnameComparator());
        
        // se añaden todos los objetos del set anterior a este nuevo
        personsBySurname.addAll(persons);

        // ahora las personas se recorren por orden de apellido
        System.out.println("Orden por apellido:");
        for (Person p : personsBySurname) {
            System.out.println(p);
        }

        System.out.println();

        // si queremos recorrer el set en orden inverso existen
        // dos opciones: definir un nuevo comparador que invierta el
        // orden de comparación o utilizar que un TreeSet implementa
        // la interfaz NavigableSet que proporciona un método para
        // invertir el orden
        NavigableSet<Person> navigableSet = (NavigableSet<Person>) personsBySurname;
        
        // ahora las personas se recorren por orden inverso de apellido
        System.out.println("Orden por apellido inverso:");
        for (Person p : navigableSet.descendingSet()) {
            System.out.println(p);
        }
    }
}

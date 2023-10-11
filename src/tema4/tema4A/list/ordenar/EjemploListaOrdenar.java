package tema4.tema4A.list.ordenar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Este método contiene un ejemplo de cómo
// se lleva a cabo la ordenación de una lista
// con objetos de una clase propia

public class EjemploListaOrdenar {
    
    public static void main(String[] args) {
        // Se crea la lista de personas con datos de prueba
        List<Person> persons = new ArrayList<>();

        persons.add(new Person("345612343A", "Albert", "Einstein"));
        persons.add(new Person("999999999W", "Richard", "Strauss"));
        persons.add(new Person("123456789X", "John", "Doe"));
        
        System.out.println("Datos sin ordenar: ");
        // Se imprimen los datos antes de ordenar
        for (Person p : persons) {
            System.out.println(p);
        }

        System.out.println("");

        // Se ordena la lista utilizando el comparador implementado
        persons.sort(new PersonComparator());

        System.out.println("Datos ordenados: ");
        // Se imprimen los datos después de ordenar
        for (Person p : persons) {
            System.out.println(p);
        }

        // Ahora ordenamos pero usando el orden natural
        // es decir, la implementación de Comparable en
        // la clase Persona. Pero en orden inverso.
        Collections.sort(persons, Collections.reverseOrder());
    }
}

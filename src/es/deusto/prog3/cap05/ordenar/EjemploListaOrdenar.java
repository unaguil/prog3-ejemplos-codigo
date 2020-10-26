package es.deusto.prog3.cap05.ordenar;

import java.util.ArrayList;
import java.util.List;

import es.deusto.prog3.cap05.contiene.Person;

// Este método contiene un ejemplo de cómo
// se lleva a cabo la ordenación de una lista
// con objetos de una clase propia

public class EjemploListaOrdenar {
    
    public static void main(String[] args) {
        // Se crea la lista de personas con datos de prueba
        List<Person> personas = new ArrayList<Person>();

        personas.add(new Person("345612343A", "Albert", "Einstein"));
        personas.add(new Person("999999999W", "Richard", "Strauss"));
        personas.add(new Person("123456789X", "John", "Doe"));
        
        System.out.println("Datos sin ordenar: ");
        // Se imprimen los datos antes de ordenar
        for (Person p : personas) {
            System.out.println(p);
        }

        System.out.println("");

        // Se ordena la lista utilizando el comparador implementado
        personas.sort(new PersonComparator());

        System.out.println("Datos ordenados: ");
        // Se imprimen los datos después de ordenar
        for (Person p : personas) {
            System.out.println(p);
        }
    }
}

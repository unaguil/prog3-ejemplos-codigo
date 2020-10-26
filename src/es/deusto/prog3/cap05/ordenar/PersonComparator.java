package es.deusto.prog3.cap05.ordenar;

import es.deusto.prog3.cap05.contiene.Person;
import java.util.Comparator;

public class PersonComparator implements Comparator<Person> {

    @Override
    public int compare(Person a, Person b) {
        return a.getId().compareTo(b.getId());
    }
    
}

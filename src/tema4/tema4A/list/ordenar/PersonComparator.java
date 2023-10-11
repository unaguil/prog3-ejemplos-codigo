package tema4.tema4A.list.ordenar;

import java.util.Comparator;

public class PersonComparator implements Comparator<Person> {

    @Override
    public int compare(Person a, Person b) {
        return a.getId().compareTo(b.getId());
    }
    
}

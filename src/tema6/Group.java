package tema6;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase representa un grupo de personas Tiene métodos para añadir, quitar
 * y buscar personas en el grupo.
 * 
 * Esta clase se usa como ejemplo para demostrar cómo utilizar tests con
 * colecciones.
 */
public class Group {
    
    private List<Person> persons = new ArrayList<Person>();

    /**
     * Añade una persona la grupo.
     * Lanza una excepción IllegalArgumentException si la persona
     * añadida es null. Lanza una excepción de tipo GroupException
     * si la persona ya estaba añadida al grupo anteriormente.
     * @param p la persona a añadir al grupo
     */
    public void addPerson(Person p) throws GroupException {
        if (p == null)
            throw new NullPointerException("Received person is null");

        if (persons.contains(p)) 
            throw new GroupException("Person already added to the group");

        persons.add(p);
    }

    /**
     * Obtiene el número de personas que hay en el grupo
     * @return el número de personas que hay en el grupo 
     */
    public int size() {
        return persons.size();
    }
    

    /**
     * Elimina una persona del grupo. Si la persona
     * no existia en el grupo no hace nada
     * @param p la persona a eliminar del grupo
     */
    public void removePerson(Person p) {
        persons.remove(p);
    }

    /**
     * Obtiene la lista con los nombres completos 
     * de las personas del grupo
     * @return la lista de nombres completos del grupo
     */
     public List<String> getFullNames() {
         List<String> fullNames = new ArrayList<String>();
         for (Person p : persons) {
            fullNames.add(p.getFullName());
         }
         return fullNames;
     }
}

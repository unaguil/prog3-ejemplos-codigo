package es.deusto.prog3.cap03.testunitarios;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Ejemplo de test unitario de la clase Group. 
 */
public class GroupTest {
    
    private Group g;

    /**
     * Este método se inicializa antes de
     * cada test creando un grupo vacío para
     * set utilizado en los distintos tests
     */
    @Before
    public void setUp() {
        g = new Group();
    }

    /**
     * Comprueba si el grupo se encuentra inicialmente
     * vacio
     */
    @Test
    public void testEmpty() {
        assertEquals(0, g.size());
    }

    /**
     * Comprueba si tras añadir una persona al grupo el tamaño del mismo aumenta
     * @throws GroupException
     */
    @Test
    public void testAddPerson() throws GroupException {
        g.addPerson(new Person("John", "Smith", "23-05-1994"));
        assertEquals(1, g.size());
    }

    @SuppressWarnings("deprecation")
    @Rule
    public ExpectedException expected = ExpectedException.none();

    /**
     * Comprueba que al añadir una persona al grupo que ya se encuentra se lanza una
     * excepción de tipo GroupException y que el grupo no cambia de tamaño.
     * También comprueba que si se añade una persona null, el tamaño del grupo no
     * cambia y se lanza una excepcion de tipo NullPointerException
     *
     */
    @Test
    public void testAddPersonErrors() throws GroupException {
        g.addPerson(new Person("John", "Smith", "23-05-1994"));
        assertEquals(1, g.size());

        expected.expect(GroupException.class);
        g.addPerson(new Person("John", "Smith", "23-05-1994"));
        assertEquals(1, g.size());

        expected.expect(NullPointerException.class);
        g.addPerson(null);
        assertEquals(1, g.size());
    }

    /**
     * Comprueba que al eliminar una persona existente de un grupo, se decrementa el
     * número de personas en el grupo.
     */
    @Test
    public void testRemovePerson() throws GroupException {
        g.addPerson(new Person("John", "Smith", "23-05-1994"));
        assertEquals(1, g.size());

        g.removePerson(new Person("John", "Smith", "23-05-1994"));
        assertEquals(0, g.size());
    }

    /**
     * Comprueba que la lista de nombres es obtenida con el formato correcto
     * 
     * @throws GroupException
     */
    @Test
    public void testGetFullNames() throws GroupException {
        g.addPerson(new Person("Enrico", "Fermi", "29-09-1901"));
        g.addPerson(new Person("Albert", "Einstein", "14-04-1879"));
        g.addPerson(new Person("Marie", "Curie", "07-11-1867"));
        assertEquals(3, g.size());

        List<String> expected = Arrays.asList(
            "Fermi, Enrico",
            "Einstein, Albert",
            "Curie, Marie"
        );

        assertEquals(expected, g.getFullNames());
    }
}

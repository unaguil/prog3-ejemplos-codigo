package es.deusto.prog3.cap03.testunitarios;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Ejemplo de test unitario para probar la funcionalidad
 * de la clase Person
 */
public class PersonTest {
    
    private Person p; // guarda el objeto de prueba

    /**
     * Este método se ejecuta "antes" de cada @test.
     * Inicializa el objeto de prueba usado en cada
     * test. De esta forma se garantiza que cada test
     * es independiente a los demas, ya que parte de un
     * objeto nuevo.
     */
    @Before
    public void setUp() {
        p = new Person("John", "Smith", "22-03-1994");
    }

    /**
     * Comprueba que el nombre devuelto por el método
     * es igual al nombre establecido en el constructor
     */
    @Test
    public void testGetName() {
        assertEquals("John", p.getName());
    }

    /**
     * Comprueba que el apellido devuelto por el método
     * es igual al apellido establecido en el constructor
     */
    @Test
    public void testGetSurname() {
        assertEquals("Smith", p.getSurname());
    }

    /**
     * Comprueba que el string devuelve es el nombre 
     * completo de la persona en el formato "apellido, nombre"
     */
    @Test
    public void testFullName() {
        assertEquals("Smith, John", p.getFullName());
    }

    /**
     * Comprueba que la fecha de nacimiento devuelta es un
     * objeto LocalDate que contiene los datos correctos
     * obtenidos al parsear el String fecha pasado en el 
     * constructor
     */
    @Test
    public void testGetBirthDate() {
        assertEquals(LocalDate.of(1994, 03, 22), p.getBirthdate());
    }

    /**
     * Comprueba que la edad de la persona se ha calculado
     * correctamente a partir de la fecha de nacimiento
     * pasada en el constructor
     */
    @Test
    public void testGetAge() {
        assertEquals(26, p.getAge());
    }

    /**
     * Se define una regla de JUnit 4 utiliza para capturar
     * excepciones esperadas en los tests.
     */
    @SuppressWarnings("deprecation")
    @Rule
    public ExpectedException expected = ExpectedException.none();

    /**
     * El siguiente test comprueba que se lanzan las excepción
     * esperada cuando se llama al constructor de la clase con
     * los parámetros inválidos (nulos o vacíos).
     */
    @Test
    public void testInvalidArguments() {
        expected.expect(IllegalArgumentException.class);
        new Person(null, "Smith", "22-03-1994");
        new Person("John", null, "22-03-1994");
        new Person("John", "Smith", null);
        new Person("", "Smith", "22-03-1994");
        new Person("John", "", "22-03-1994");
        new Person("John", "Smith", "");
    }

    /**
     * Este test comprueba que el método equals se ha
     * implementado de la forma esperada
     */
    @Test
    public void testEquals() {
        Person samePerson = new Person("John", "Smith", "22-05-1990");
        assertTrue(p.equals(samePerson));

        Person differentPerson = new Person("Juan", "Smith", "22-05-1990");
        assertFalse(p.equals(differentPerson));

        differentPerson = new Person("John", "Doe", "22-05-1990");
        assertFalse(p.equals(differentPerson));
    }
}

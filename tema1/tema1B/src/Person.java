import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Esta clase sirve de ejemplo para demostrar cómo
 * realizar un test unitario con JUnit 4.
 */
public class Person {
    
    private String name;
    private String surname;
    private LocalDate birthdate;

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    /**
     * Construye una instancia de Persona. Si alguno de los parámetros es nulo o
     * el String vacio se lanza una excepción de tipo IllegalArgumentException.
     * @param name el nombre de la persona
     * @param surname el apellido de la persona
     * @param birthdate la fecha de nacimiento de la persona en formato dd-MM-yyyy
     */
    public Person(String name, String surname, String birthdate) {
        if (name == null || surname == null || birthdate == null ||
                name.isEmpty() || surname.isEmpty() || birthdate.isEmpty())
            throw new IllegalArgumentException("Invalid parameters");

        this.name = name;
        this.surname = surname;
        this.birthdate = LocalDate.parse(birthdate, formatter);        
    }

    /**
     * Obtiene el nombre de la persona
     * @returns el nombre de la persona
     */
    public String getName() {
        return name;
    }

    /**
     * Obtiene el apellido de la persona
     * @return el apellido de la persona
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Obtiene el nombre completo de la persona
     * con el formato "apellido, nombre"
     * @return el nombre completo de la persona
     */
    public String getFullName() {
        return String.format("%s, %s", surname, name);
    }

    /**
     * Obtiene la fecha de nacimiento de la persona
     * @return
     */
    public LocalDate getBirthdate() {
        return birthdate;
    }

    /**
     * Obtiene la edad de la persona
     * @return la edad de la persona
     */
    public int getAge() {
        return (int) ChronoUnit.YEARS.between(birthdate, LocalDate.of(2020, 12, 31));
    }

    /**
     * Dos personas son iguales si tienen el mismo nombre
     * y el mismo apellido.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Person))
            return false;

        Person p = (Person)o;
        return this.name.equals(p.name) &&
            this.surname.equals(p.surname);
    }
}

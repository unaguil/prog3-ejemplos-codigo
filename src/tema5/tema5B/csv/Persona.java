package tema5.tema5B.csv;

public class Persona {
    
    private String name;
    private String surname;
    private int age;

    public Persona(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return String.format("Nombre: %s, Apellido: %s, Edad: %d", name, surname, age); 
    }
}

package es.deusto.prog3.cap04.serializacion;

import java.io.Serializable;

// Ejemplo de clase serializable por Java
// Implementa la interfaz necesaria

public class Persona implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -1339142877488259479L;
    
    // Los tipos primitivos son siempre serializables
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

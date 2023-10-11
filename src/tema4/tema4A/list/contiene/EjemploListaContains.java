package tema4.tema4A.list.contiene;

import java.util.List;
import java.util.ArrayList;

// En este ejemplo se muestra cómo el método contains se
// basa en el método equals de la clase Persona para
// determinar si dos personas son iguales o no.

// Para probar qué ocurre si no existe el método, comentar
// el método equals en la clase Persona.

public class EjemploListaContains {
    
    public static void main(String[] args) {
        // Se crea la lista de personas con datos de prueba
        List<Person> personas = new ArrayList<>();

        personas.add(new Person("123456789X", "John", "Doe"));
        personas.add(new Person("345612343A", "Albert", "Einstein"));
        personas.add(new Person("999999999W", "Richard", "Strauss"));

        // Se determina si la lista contiene una persona concreta
        // Es importa fijarse en que estamos buscando con una instancia
        // distinta a la que se había introducido en la lista.
        // En este caso es cuando es importante tener el método equals()
        // de Persona correctamente implementado.
        if (personas.contains(new Person("123456789X", "John", "Doe"))) {
            System.out.println("La persona ya existe en la lista");
        } else {
            System.out.println("La persona NO existe en la lista");
        }
    }
}

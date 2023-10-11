package tema4.tema4A.set.hash;

import java.util.HashSet;
import java.util.Set;

// En este programa se muestra cómo funciona el
// cálculo del HashCode para colecciones que se
// basan en el uso del hash de las instancias.
// Como es el caso de HashSet.

// Se puede comprobar la necesidad de implementar el
// hashCode comentando el método en la clase PhoneNumber
// y viendo que entonces el método contains no encuentra
// el objeto en el HashSet.

public class EjemploHashCode {
    
    public static void main(String[] args) {
        Set<PhoneNumber> phones = new HashSet<>();

        phones.add(new PhoneNumber("707", "867", "5309", "Jenny"));
        phones.add(new PhoneNumber("123", "456", "2323", "John"));

        System.out.print("¿Encontrado?: ");
        System.out.println(phones.contains(new PhoneNumber("707", "867", "5309", "Jenny")));
    }
}

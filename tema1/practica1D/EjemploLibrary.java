import es.deusto.prog3.Student;

// En este ejemplo se muestra cómo se utiliza una librería externa
// que contiene clases.

// En concreto, este ejemplo utiliza el fichero example-lib.jar
// El código no puede ser compilcado si no se le dice a java dónde
// se encuentra la librería que contiene las clases que faltan.

// En la línea de comandos esto significa que para compilar hay
// que indicar que añada al classpath (con el argumento -cp) el fichero
// example-lib.jar que contiene las clases que faltan.

// javac -cp external_library/example-lib.jar es/deusto/prog3/cap03/EjemploLibrary.java

// Para lanzar el programa en la JVM se debe indicar también.
// java -cp external_library/example-lib.jar es.deusto.prog3.cap03.api.EjemploLibrary


public class EjemploLibrary {
    
    public static void main(String[] args) {
        Student student = new Student("John", "Smith", 20);
        
        System.out.println(student);
    }
}

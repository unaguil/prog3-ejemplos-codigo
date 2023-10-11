package tema1.tema1C;

import java.util.regex.Pattern;


// En este ejemplo se muestra el uso de las expresiones regulares
// para filtrar una lista de strings, detectando aquellos que cumplen
// cierto patrón

public class EjemploRegex {

    private static String[] testNombres = {
        "prueba.txt", "prueba2.jpg", "otro.dat", "pedro.doc", "andoni.txt", "dir", "prueba3."
    };

    public static void main(String[] args) {
        // Significado de la expresión regular:
        // 1. Empieza por: p
        // 2. Le siguen cero o más caracteres: .*
        // 3. Después viene un punto: \. ¡Cuidado! La barra hay que escaparla por eso se pone \\.
        // 4. Después vienen uno o más caracteres: .+
        String regex = "p.*\\..+";           
        
        System.out.println("Patrón: " + regex);
        Pattern pattern = Pattern.compile(regex);  // Se compila el patrón de búsqueda
        for (String s : testNombres) {
            if (pattern.matcher(s).matches())  // Se comprueba si pasa el filtro
                System.out.println("Cumple: " + s);
            else 
                System.out.println("No cumple: " + s);
        }
    }
}
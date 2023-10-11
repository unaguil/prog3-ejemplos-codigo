package tema1.tema1E;

// En este ejemplo se muestra el uso de un enum
// para representar un grupo de constantes relacionadas.

public class EjemploEnum {
    
    public enum DayOfWeek {
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY,
        SUNDAY
    }

    public static boolean isWeekend(DayOfWeek day) {
        // Comparar el valor de la variable con la constante
        return day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY;
    }

    public static void main(String[] args) {
        // Podemos declarar variables con el valor de la constante
        DayOfWeek day = DayOfWeek.WEDNESDAY;

        // Pasar la variable a un método que reciba el enum
        System.out.println("¿Es fin de semana?: " + isWeekend(day));

        // Iterar sobre los valores de la constante
        for (DayOfWeek d : DayOfWeek.values()) {
            System.out.println(d);
        }

        // Se pueden usar en los switch
        switch (day) {
            case MONDAY:    System.out.println("Es el primer día");
                            break;

            case TUESDAY:   System.out.println("Es el segundo día");
                            break;

            case WEDNESDAY: System.out.println("Es el tercer día");
                            break;

            case THURSDAY:  System.out.println("Es el cuarto día");
                            break;
            
            default:        System.out.println("Es el resto de días");
                            break;
        }

        // Convertir a su representación en String
        System.out.println("Representación string: " + day.toString());

        // Obtener su orden en el grupo de constantes
        System.out.println("Ordinal: " + day.ordinal());

        // Obtener la constante asociada a partir de su valor String
        DayOfWeek newDay = DayOfWeek.valueOf("TUESDAY");
        System.out.println(newDay == DayOfWeek.TUESDAY);
    }
}

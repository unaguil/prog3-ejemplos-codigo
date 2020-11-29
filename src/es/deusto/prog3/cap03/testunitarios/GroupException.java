package es.deusto.prog3.cap03.testunitarios;

/**
 * Clase utilizara para representar los errores
 * que pueden producirs en los métodos de la clase
 * Group
 */
public class GroupException extends Exception {
    
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public GroupException(String message) {
        super(message);
    }
}

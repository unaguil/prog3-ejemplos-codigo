package tema5.tema5A.manager;

// Esta excepción personalizada es utilizada por la
// clase DBManager para indicar distintos errores.

public class DBException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DBException(String message) {
		super(message);
	}
	
	public DBException(String message, Throwable e) {
		super(message, e);
	}
}

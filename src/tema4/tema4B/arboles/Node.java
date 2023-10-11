package tema4.tema4B.arboles;

/**
 * Clase que representa a los nodos del árbol
 * La clase es pública porque puede ser usada fuera del árbol
 * para añadir métodos al nodo.
**/
public class Node<T> {
	
	private T value; // valor asociado al nodo.
	private Node<T> left; // hijo izq. del nodo
	private Node<T> right; // hijo der. del nodo
	
	/**
	 * Constructor de un nodo del árbol
	 * @param value objeto almacenado como valor en el nodo
	 */ 
	public Node(T value) {
		this.value = value;
	}
	
	/**
	 * Establece el hijo izquierdo del nodo
	 * @param node el nodo a establecer como hijo izquierdo
	 * @return el nodo establecido anteriormente como hijo izquierdo
	 */
	public Node<T> setLeft(Node<T> node) {
		Node<T> oldNode = this.left;
		this.left = node;
		return oldNode;
	}
	
	/**
	 * Obtiene el hijo izquierdo del nodo
	 * @return el hijo izquierdo del nodo
	 */
	public Node<T> getLeft() {
		return left;
	}
	
	/**
	 * Establece el hijo derecho del nodo
	 * @param node el nodo a establecer como hijo derecho
	 * @return el nodo establecido anteriormente como hijo derecho
	 */
	public Node<T> setRight(Node<T> node) {
		Node<T> oldNode = this.right;
		this.right = node;
		return oldNode;
	}
	
	/**
	 * Obtiene el hijo derecho del nodo
	 * @return el hijo derecho del nodo
	 */
	public Node<T> getRight() {
		return right;
	}
	
	/**
	 * Obtiene el valor almacenado en el nodo
	 * @return valor almacenado en el nodo
	 */
	public T get() {
		return value;
	}
	
	/**
	 * Establece el valor almacenado en el nodo al pasado
	 * como parámetro
	 * @param nuevo valor del nodo
	 * @return el antiguo valor almacenado en el nodo
	 */
	public T set(T value) {
		T oldValue = this.value;
		this.value = value;
		return oldValue;
	}
}
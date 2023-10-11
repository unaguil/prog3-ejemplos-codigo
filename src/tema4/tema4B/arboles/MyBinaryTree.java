package tema4.tema4B.arboles;

/**
 * Esta clase implementa una estructura de tipo árbol binario.
 * Cada nodo del árbol puede tener como máximo dos hijos.
 * Contiene la raíz del árbol y las operaciones sobre el
 * árbol. 
**/
public class MyBinaryTree<T> {
	
	private Node<T> root = null; // Nodo raíz del árbol
	
	/**
	 * Establece el nodo raíz del árbol
	 * @param root la nueva raíz del árbol
	 */
	public void setRoot(Node<T> root) {
		this.root = root;
	}
	
	/**
	 * Obtiene el nodo raíz del árbol
	 * @return el nodo raíz del árbol
	 */
	public Node<T> getRoot() {
		return root;
	}
	
	/**
	 * Elimina todos los nodos del árbol
	 */
	public void clear() {
		root = null; 
	}
	
	/**
	 * Indica si el árbol está vacío
	 */
	public boolean isEmpty() {
		return root == null;
	}
}

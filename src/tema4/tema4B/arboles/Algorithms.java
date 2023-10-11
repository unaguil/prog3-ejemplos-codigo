package tema4.tema4B.arboles;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Esta clase contiene métodos estáticos con algoritmos
 * sobre árboles.
 */
public class Algorithms {
	
	/**
	 * Interfaz que define un visitador de nodo
	 * @param <T> la clase de los elementos guardados en el árbol
	 */
	public interface Visitor<T> {
		
		/**
		 * Este método es llamado cada vez que el algoritmo de
		 * recorrido procesa un nodo.
		 * @param value valor asociado al nodo procesado
		 */
		public void visit(T value);
		
	}

	/**
	 * Recorrido primero en profundidad del árbol (Depth-First) in-orden
	 * @param <T> la clase de los elementos guardados en el árbol
	 * @param tree árbol que debe ser recorrido por el álgoritmo
	 * @param visitor el visitador que procesa cada nodo visitado
	 * durante el recorrido del algoritmo.
	 */
	public static <T> void depthFirst(MyBinaryTree<T> tree, Visitor<T> visitor) {
		if (!tree.isEmpty())
			depthFirstRec(tree.getRoot(), visitor);
	}
	
	// método privado que implementa el algoritmo recursivo DFS (in-orden)
	private static <T> void depthFirstRec(Node<T> node, Visitor<T> visitor) {
		if (node.getLeft() != null) // procesar sub-árbol izquierdo
			depthFirstRec(node.getLeft(), visitor);
		
		// procesar el valor del nodo actual llamado al método del visitador
		visitor.visit(node.get());
		
		if (node.getRight() != null) // procesar sub-árbol derecho
			depthFirstRec(node.getRight(), visitor);
	}
	
	/**
	 * Recorrido primero en profundidad del árbol (Depth-First) pre-orden
	 * @param <T> la clase de los elementos guardados en el árbol
	 * @param tree árbol que debe ser recorrido por el álgoritmo
	 * @param visitor el visitador que procesa cada nodo visitado
	 * durante el recorrido del algoritmo.
	 */
	public static <T> void depthFirstPre(MyBinaryTree<T> tree, Visitor<T> visitor) {
		if (!tree.isEmpty())
			depthFirstPreRec(tree.getRoot(), visitor);
	}
	
	// método privado que implementa el algoritmo recursivo DFS (pre-orden)
	private static <T> void depthFirstPreRec(Node<T> node, Visitor<T> visitor) {
		// procesar el valor del nodo actual llamado al método del visitador
		visitor.visit(node.get());
		
		if (node.getLeft() != null) // procesar sub-árbol izquierdo
			depthFirstPreRec(node.getLeft(), visitor);
		
		if (node.getRight() != null) // procesar sub-árbol derecho
			depthFirstPreRec(node.getRight(), visitor);
	}
	
	/**
	 * Recorrido primero en profundidad del árbol (Depth-First) post-orden
	 * @param <T> la clase de los elementos guardados en el árbol
	 * @param tree árbol que debe ser recorrido por el álgoritmo
	 * @param visitor el visitador que procesa cada nodo visitado
	 * durante el recorrido del algoritmo.
	 */
	public static <T> void depthFirstPost(MyBinaryTree<T> tree, Visitor<T> visitor) {
		if (!tree.isEmpty())
			depthFirstPostRec(tree.getRoot(), visitor);
	}
	
	// método privado que implementa el algoritmo recursivo DFS (post-orden)
	private static <T> void depthFirstPostRec(Node<T> node, Visitor<T> visitor) {		
		if (node.getLeft() != null) // procesar sub-árbol izquierdo
			depthFirstPostRec(node.getLeft(), visitor);
		
		if (node.getRight() != null) // procesar sub-árbol derecho
			depthFirstPostRec(node.getRight(), visitor);
		
		// procesar el valor del nodo actual llamado al método del visitador
		visitor.visit(node.get());
	}
	
	/**
	 * Recorrido primero en anchura del árbol (Breadth-First)
	 */
	public static <T> void breadthFirst(MyBinaryTree<T> tree, Visitor<T> visitor) {
		if (tree.isEmpty())
			return;

		// Cola de nodos encontrados pero no visitados. Esta lista permite recorrer el árbol
		// en profundidad iterando de cada nivel en el orden en el que han sido añadidos
		Queue<Node<T>> nodes = new LinkedList<>();
		nodes.add(tree.getRoot()); // se añade la raíz
		
		// mientras hay nodos para procesar
		while(!nodes.isEmpty()) {
			Node<T> node = nodes.poll(); // sacar el primer nodo de la cola
			visitor.visit(node.get()); // visitar el nodo
			
			// añadir sus hijos a la cola para procesarlos (si no son nulos)
			if (node.getLeft() != null)
				nodes.add(node.getLeft());
			
			if (node.getRight() != null)
				nodes.add(node.getRight());
		}
	}
	
	/**
	 * Busquedad primero en profundidad del árbol (Depth-First) in-orden
	 * @param <T> la clase de los elementos guardados en el árbol
	 * @param tree árbol que debe ser recorrido por el álgoritmo
	 * @param value el valor a buscar en el árbol
	 * @return el nodo que cumple 
	 */
	public static <T> Node<T> depthFirstSearch(MyBinaryTree<T> tree, T value) {
		if (!tree.isEmpty())
			return depthFirstSearchRec(tree.getRoot(), value);
		
		return null;
	}
	
	// método privado que implementa el algoritmo de búsqueda recursiva DFS (in-orden)
	private static <T> Node<T> depthFirstSearchRec(Node<T> node, T value) {
		if (node.getLeft() != null)  {
			// buscar en sub-árbol izquierdo
			Node<T> leftNode = depthFirstSearchRec(node.getLeft(), value);
			
			// comprobar si ya se ha encontrado en el sub-árbol izquierdo
			if (leftNode != null) 
				return leftNode;
		}
		
		// comparar el nodo actual para ver si es el buscado (o son nulos ambos)
		if (node.get() == null ? value == null : node.get().equals(value)) {
			return node;
		}
		
		// buscar en el sub-árbol derecho (se devuelve directamente el resultado)
		if (node.getRight() != null) {
			return depthFirstSearchRec(node.getRight(), value);
		}
		
		return null;
	}
}

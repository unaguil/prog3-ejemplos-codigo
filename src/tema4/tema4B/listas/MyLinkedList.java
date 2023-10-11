package tema4.tema4B.listas;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

// Implementación de la interfaz List de Java como
// una lista enlazada simple, frente a la que proporciona
// Java que es doblemente enlazada.

// La clase MyLinkedList hace uso de los tipos genéricos
// de Java para que pueda contener cualquier tipo de objeto.

// No se han implementado todos los métodos, solamente aquellos
// básicos para el uso de una lista. Los métodos no implementados
// lanza una excepción cuando son llamados.

public class MyLinkedList<T> implements List<T> {

    // Esta clase representa un elemento de la lista
    // enlazada. Es interna a la clase porque es para
    // uso interno de la lista.
    class Node {
        T value;
        Node next = null;

        public Node(T value) {
            this.value = value;
        }

        public Node(T value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    private Node head = null; // Referencia al primer elemento de la lista
    private int size = 0; // Número de elementos de la lista

    @Override
    public boolean add(T v) {
        // Si la lista está vacía se añade el primer nodo
        if (head == null) {
            head = new Node(v);
        } else {
            // Si la lista tiene algún nodo
            // se recorre hasta encontrar el
            // último (aquel que next == null).
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            // se añade el nuevo nodo
            current.next = new Node(v);
        }

        size++; // se incrementa el número de elementos
        return true;
    }

    @Override
    public void add(int index, T v) {
        if (index == 0) {
            // si el nodo debe añadirse al principio
            // se crea un nuevo nodo como cabeza
            // que referencia al antiguo primer elemento
            // de la lista
            head = new Node(v, head);
        } else {
            // si el nodo debe añadirse en otra posición
            // se obtiene el nodo anterior y se incluye
            // el nuevo nodo con enlaces de previo -> nuevo
            // y de nuevo -> siguiente
            Node prev = getNode(index - 1);
            prev.next = new Node(v, prev.next);
        }
        size++; // se incrementa el tamaño de la lista
    }

    // Este es un método auxiliar utilizado por varios
    // otros métodos. Obtiene el elemento que se encuentra
    // en el índice pasado. Si el índice es incorrecto lanza
    // una excepción IndexOutofBoundsException. Esta excepción
    // es de tipo unchecked y no hace falta que sea declarada
    // en el throws de los métodos.
    private Node getNode(int index) {
        // Si el indice no es válido se lanza una excepción
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        
        // se busca el indice indicado en la lista
        Node current = head;
        int counter = 0;
        while (counter < index) {
            current = current.next;
            counter++;
        }
        
        return current;
    }
    
    // Este método se utiliza para llevar a cabo la comparación
    // de igualdad permitiendo la existencia de valores nulos en
    // los nodos.
    private boolean equals(Node node, Object value) {
    	return node.value == null ? value == null : node.value.equals(value);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        // se elimina la referencia al primer elemento de la lista
        // el resto se borran en cascada
        head = null;
        size = 0; // se actualiza el tamaño de la lista
    }

    @Override
    public boolean contains(Object o) {
        // Se recorre la lista hasta el final o 
        // hasta encontrar el nodo con el valor
        Node current = head;
        while (current != null) {
            // comprobar cada nodo
            if (equals(current, o)) {
                return true;
            }
            current = current.next; // se avanza en la lista
        }

        return false; // no se ha encontrado
    }

    @Override
    public T get(int index) {
        // se usa el método auxiliar implementado
        Node node = getNode(index);
        return node.value; // se devuelve el valor del nodo
    }

    @Override
    public T set(int index, T v) {
        Node node = getNode(index);
        T value = node.value; // valor previo
        node.value = v;
        return value;
    }

    @Override
    public int indexOf(Object o) {
        // se recorre la lista desde el primer
        // elemento hasta encontrar el buscado
        // o llegar al final de la lista 
        int index = 0;
        Node current = head;
        while(current != null) {
            if (equals(current, o)) {
                // se devuelve el primer elemento encontrado
                return index;
            }
            current = current.next; // se avanza en la lista
            index++; // se incrementa el índice actual
        }

        return -1; // no encontrado 
    }

    @Override
    public int lastIndexOf(Object o) {
        // se recorre la lista desde el primer
        // elemento hasta encontrar el buscado
        // o llegar al final de la lista
        int lastIndex = -1;
        int index = 0;
        Node current = head;
        while(current != null) {
            if (equals(current, o)) {
                lastIndex = index;
            }
            current = current.next; // se avanza en la lista
            index++; // se incrementa el índice actual
        }

        return lastIndex;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public T remove(int index) {
        if (index < 0)
            throw new IndexOutOfBoundsException();

        T value = null; // guarda el valor del nodo borrado

        // si es el primero elemento se conecta
        // la cabeza de la lista al siguiente
        if (index == 0) {
            value = head.value;
            head = head.next;
        } else {
            // usamos el método auxiliar para obtener el nodo
            // anterior al que se quiere borrar
            Node node = getNode(index - 1);
            value = node.value;

            // borramos el nodo haciendo que el anterior
            // referencie al siguiente al que quiere ser
            // eliminado
            node.next = node.next.next;
        }

        size--; // se reduce el tamaño
        return value; // se retorna el valor borrado
    }

    @Override
    public boolean remove(Object o) {
        // Comprobar si el elemento buscado es el primero
        if (head != null) {
        	if (equals(head, o)) {
	            head = head.next;
	            size--;
	            return true;
        	} else {
		        // recorrer la lista buscando el elemento anterior
		        // al que se pide ser borrado
		        Node current = head;
		        while (current.next != null) {
		            // se comprueba el nodo siguiente al actual
		            if (equals(current.next, o)) {
		                current.next = current.next.next;
		                size--;
		                return true;
		            }
		            current = current.next; // se avanza en la lista
		        }
        	}
        }
        
        return false;
    }

    //////////////////////////////////////////////////////////////
    /// Métodos no implementados
    //////////////////////////////////////////////////////////////

    @Override
    public boolean addAll(Collection<? extends T> arg0) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public boolean containsAll(Collection<?> arg0) {
        throw new UnsupportedOperationException("Not implemented"); 
    }

    @Override
    public boolean addAll(int arg0, Collection<? extends T> arg1) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public ListIterator<T> listIterator() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public ListIterator<T> listIterator(int arg0) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public boolean removeAll(Collection<?> arg0) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public boolean retainAll(Collection<?> arg0) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public List<T> subList(int arg0, int arg1) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    @SuppressWarnings("all")
    public <T> T[] toArray(T[] arg0) {
        throw new UnsupportedOperationException("Not implemented");
    }
    
}

package tema4.tema4B.tests.arboles;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import tema4.tema4B.arboles.Algorithms;
import tema4.tema4B.arboles.MyBinaryTree;
import tema4.tema4B.arboles.Node;

public class AlgorithmsTest {

	private MyBinaryTree<Integer> tree;
	
	@Before
	public void setUp() {
		// preparar el árbol de prueba para los tests
		tree = new MyBinaryTree<>();
		
		//			     15
		//			  /	     \ 
		//			 5        20
		//		   /   \     /   \
		//		  3     7 	16   30
		//       /    /  \ 
		//		1	 6    9
		
		// los indices en el nombre de las variables 
		// hacen referencia al nivel y posición 
		// dentro del nivel
		Node<Integer> root = new Node<>(15);
		Node<Integer> node11 = new Node<>(5);
		Node<Integer> node12 = new Node<>(20);
		Node<Integer> node21 = new Node<>(3);
		Node<Integer> node22 = new Node<>(7);
		Node<Integer> node23 = new Node<>(16);
		Node<Integer> node24 = new Node<>(30);
		Node<Integer> node31 = new Node<>(1);
		Node<Integer> node32 = new Node<>(6);
		Node<Integer> node33 = new Node<>(9);
		
		root.setLeft(node11);
		root.setRight(node12);
		
		node11.setLeft(node21);
		node11.setRight(node22);
		node12.setLeft(node23);
		node12.setRight(node24);
		
		node21.setLeft(node31);
		
		node22.setLeft(node32);
		node22.setRight(node33);
		
		tree.setRoot(root);
	}
	
	@Test
	public void testClear() {
		tree.clear();
		assertNull(tree.getRoot());
	}
	
	/**
	 * Este visitador guarda los valores visitados en una lista.
	 * Esto permite que esta lista pueda ser comparada luego con el
	 * orden esperado de recorrido/visita
	 */
	class MyVisitor implements Algorithms.Visitor<Integer> {

		private List<Integer> visited = new ArrayList<>();
		
		@Override
		public void visit(Integer value) {
			visited.add(value);		
		}
		
		public List<Integer> getVisited() {
			return visited;
		}
		
	}
	
	@Test
	public void testDepthFirst() {
		MyVisitor visitor = new MyVisitor();
		Algorithms.depthFirst(tree, visitor);
		
		List<Integer> expected = Arrays.asList(1, 3, 5, 6, 7, 9, 15, 16, 20, 30);
		assertEquals(expected, visitor.getVisited());
	}
	
	@Test
	public void testDepthFirstPre() {
		MyVisitor visitor = new MyVisitor();
		Algorithms.depthFirstPre(tree, visitor);
		
		List<Integer> expected = Arrays.asList(15, 5, 3, 1, 7, 6, 9, 20, 16, 30);
		assertEquals(expected, visitor.getVisited());
	}
	
	@Test
	public void testDepthFirstPost() {
		MyVisitor visitor = new MyVisitor();
		Algorithms.depthFirstPost(tree, visitor);
		
		List<Integer> expected = Arrays.asList(1, 3, 6, 9, 7, 5, 16, 30, 20, 15);
		assertEquals(expected, visitor.getVisited());
	}
	
	
	@Test
	public void testBreadthFirst() {
		MyVisitor visitor = new MyVisitor();
		Algorithms.breadthFirst(tree, visitor);
		
		List<Integer> expected = Arrays.asList(15, 5, 20, 3, 7, 16, 30, 1, 6, 9);
		assertEquals(expected, visitor.getVisited());
	}
	
	@Test
	public void testDepthFirstSearch() {
		Node<Integer> node = Algorithms.depthFirstSearch(tree, 7);
		assertEquals(Integer.valueOf(7), node.get());
	}
	
	@Test
	public void testDepthFirstSearchNotFound() {
		Node<Integer> node = Algorithms.depthFirstSearch(tree, 100);
		assertNull(node);
	}
	
	@Test
	public void testDepthFirstSearchNull() {
		MyBinaryTree<Integer> treeWithNull = new MyBinaryTree<>();
		Node<Integer> root = new Node<>(5);
		treeWithNull.setRoot(root);	
		root.setLeft(new Node<>(null));
		
		Node<Integer> node = Algorithms.depthFirstSearch(treeWithNull, 7);
		assertNull(node);
		
		Node<Integer> nodeWithNullValue = Algorithms.depthFirstSearch(treeWithNull, null);
		assertNull(nodeWithNullValue.get());
	}
	
	@Test
	public void testDepthFirstEmpty() {
		MyVisitor visitor = new MyVisitor();
		MyBinaryTree<Integer> emptyTree = new MyBinaryTree<>();
		
		Algorithms.depthFirst(emptyTree, visitor);
		
		List<Integer> expected = Collections.emptyList();
		assertEquals(expected, visitor.getVisited());
	}
	
	@Test
	public void testDepthFirstPreEmpty() {
		MyVisitor visitor = new MyVisitor();
		MyBinaryTree<Integer> emptyTree = new MyBinaryTree<>();
		
		Algorithms.depthFirstPre(emptyTree, visitor);
		
		List<Integer> expected = Collections.emptyList();
		assertEquals(expected, visitor.getVisited());
	}
	
	@Test
	public void testDepthFirstPostEmpty() {
		MyVisitor visitor = new MyVisitor();
		MyBinaryTree<Integer> emptyTree = new MyBinaryTree<>();
		
		Algorithms.depthFirstPost(emptyTree, visitor);
		
		List<Integer> expected = Collections.emptyList();
		assertEquals(expected, visitor.getVisited());
	}
	
	@Test
	public void testBreadthFirstEmpty() {
		MyVisitor visitor = new MyVisitor();
		MyBinaryTree<Integer> emptyTree = new MyBinaryTree<>();
		
		Algorithms.breadthFirst(emptyTree, visitor);
		
		List<Integer> expected = Collections.emptyList();
		assertEquals(expected, visitor.getVisited());
	}
	
	@Test
	public void testDepthFirstSearchEmpty() {
		MyBinaryTree<Integer> emptyTree = new MyBinaryTree<>();
		
		Node<Integer> node = Algorithms.depthFirstSearch(emptyTree, 7);
		assertNull(node);
	}
}

package tema4.tema4B.tests.listas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import tema4.tema4B.listas.MyLinkedList;

public class MyLinkedListTest {

    private List<String> list;

    @Before
    public void setUp() {
        list = new MyLinkedList<String>();
    } 

    @Test
    public void testAdd() {
        assertTrue(list.isEmpty());
        
        list.add("First value");
        list.add("Second value");
        list.add("Third value");

        assertFalse(list.isEmpty());
        assertEquals(3, list.size());

        assertEquals("First value", list.get(0));
        assertEquals("Second value", list.get(1));
        assertEquals("Third value", list.get(2));
    }
    
    @Test
    public void testAddIndex() {
    	assertTrue(list.isEmpty());
    	
    	list.add(0, "First added value");
    	list.add(0, "Second added value");
    	list.add(1, "Third added value");
    	
    	assertEquals("Second added value", list.get(0));
    	assertEquals("Third added value", list.get(1));
    	assertEquals("First added value", list.get(2));
    }
    
    @Test
    public void testIsEmpty() {
        assertTrue(list.isEmpty());

        addSomeData(list, 10);
        assertFalse(list.isEmpty());
    }

    @Test
    public void testSize() {
        assertEquals(0, list.size());

        addSomeData(list, 5);    
        assertEquals(5, list.size());
    }

    @Test
    public void testClear() {
        assertEquals(0, list.size());

        addSomeData(list, 10);
        assertEquals(10, list.size());

        list.clear();

        assertEquals(0, list.size());
    }

    @Test
    public void testGet() {
        list.add("First value");
        list.add("Second value");
        list.add("Third value");

        assertEquals("First value", list.get(0));
        assertEquals("Second value", list.get(1));
        assertEquals("Third value", list.get(2));
    }
    
    @Test(expected=IndexOutOfBoundsException.class)
    public void testGetNegativeIndex() {       
    	list.get(-1);
    }
    
    @Test(expected=IndexOutOfBoundsException.class)
    public void testGetGreaterThanSize() {
    	list.get(1);
    }

    @Test
    public void testSet() {
        list.add("First value");
        list.add("Second value");
        list.add("Third value");

        assertEquals("First value", list.get(0));
        assertEquals("Second value", list.get(1));
        assertEquals("Third value", list.get(2));

        list.set(0, "New first value");
        list.set(1, "New second value");
        list.set(2, "New third value");

        assertEquals("New first value", list.get(0));
        assertEquals("New second value", list.get(1));
        assertEquals("New third value", list.get(2));
    }

    @Test
    public void testContains() {
        list.add("First value");
        list.add("Second value");
        list.add("Third value");

        assertTrue(list.contains("Second value"));
        assertFalse(list.contains("Another value"));
    }
    
    @Test
    public void testContainsNull() {
    	list.add("First value");
    	list.add(null);
    	list.add("Third value");

        assertTrue(list.contains(null));
        assertFalse(list.contains("Another value"));
    }

    @Test
    public void testIndexOf() {
        list.add("First value");
        list.add("Second value");
        list.add("Third value");

        assertEquals(0, list.indexOf("First value"));
        assertEquals(1, list.indexOf("Second value"));
        assertEquals(2, list.indexOf("Third value"));
        assertEquals(-1, list.indexOf("Another value"));
    }

    @Test
    public void testLastIndexOf() {
        list.add("First value");
        list.add("Second value duplicated");
        list.add("Second value duplicated");
        list.add("Third value");

        assertEquals(0, list.lastIndexOf("First value"));
        assertEquals(2, list.lastIndexOf("Second value duplicated"));
        assertEquals(3, list.lastIndexOf("Third value"));
        assertEquals(-1, list.lastIndexOf("Another value"));
    }

    @Test
    public void testRemove() {
        list.add("First value");
        list.add("Second value");
        list.add("Third value");

        assertEquals(3, list.size());
        
        list.remove(1);
        assertEquals(2, list.size());

        list.remove(1);
        assertEquals(1, list.size());

        list.remove(0);
        assertEquals(0, list.size());
    }
    
    @Test(expected=IndexOutOfBoundsException.class)
    public void testRemoveNegativaIndex() {
    	list.remove(-1);
    }

    @Test
    public void testRemoveObject() {
        list.add("First value");
        list.add("Second value");
        list.add("Third value");

        assertEquals(3, list.size());
        
        assertFalse(list.remove("Another value"));
        assertEquals(3, list.size());
        
        assertTrue(list.remove("Second value"));
        assertEquals(2, list.size());

        assertTrue(list.remove("Third value"));
        assertEquals(1, list.size());

        assertTrue(list.remove("First value"));
        assertEquals(0, list.size());
        
        assertFalse(list.remove("Another value"));
    }
        
    private void addSomeData(List<String> list, int numElements) {
        for (int i = 0; i < numElements; i++) {
            list.add("Some data");
        }
    }
}

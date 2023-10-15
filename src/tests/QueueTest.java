package tests;

import exceptions.QueueIsEmptyException;
import model.structures.Queue;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class QueueTest {
    private Queue<Integer> queue;

    @Before
    public void setUp() {
        queue = new Queue<>();
    }

    @Test
    public void testAdd() {
        assertTrue(queue.add(5));
        assertFalse(queue.isEmpty());
        assertEquals(Integer.valueOf(5), queue.peek());
    }

    @Test
    public void testRemove() throws QueueIsEmptyException {
        queue.add(10);
        assertEquals(Integer.valueOf(10), queue.remove());
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testPeek() throws QueueIsEmptyException {
        queue.add(8);
        assertEquals(Integer.valueOf(8), queue.peek());
        assertFalse(queue.isEmpty());
    }

    @Test
    public void testPoll() {
        assertNull(queue.poll());
        queue.add(3);
        queue.add(7);
        assertEquals(Integer.valueOf(3), queue.poll());
        assertEquals(Integer.valueOf(7), queue.poll());
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(queue.isEmpty());
        queue.add(1);
        assertFalse(queue.isEmpty());
        queue.remove();
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testRemoveSpecificElement() throws QueueIsEmptyException {
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.remove(2);
        assertEquals(Integer.valueOf(1), queue.remove());
        assertEquals(Integer.valueOf(3), queue.remove());
        assertTrue(queue.isEmpty());
    }
}


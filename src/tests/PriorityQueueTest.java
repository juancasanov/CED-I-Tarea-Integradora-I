package tests;

import exceptions.QueueIsEmptyException;
import model.structures.PriorityQueue;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PriorityQueueTest {
    private PriorityQueue<Integer> priorityQueue;

    @Before
    public void setUp() {
        priorityQueue = new PriorityQueue<>();
    }

    @Test
    public void testAdd() {
        assertTrue(priorityQueue.add(5));
        assertEquals(1, priorityQueue.size());
        assertFalse(priorityQueue.isEmpty());
    }

    @Test
    public void testRemove() {
        priorityQueue.add(10);
        assertEquals(Integer.valueOf(10), priorityQueue.remove());
        assertTrue(priorityQueue.isEmpty());
    }

    @Test
    public void testPeek() throws QueueIsEmptyException {
        priorityQueue.add(8);
        assertEquals(Integer.valueOf(8), priorityQueue.get());
        assertFalse(priorityQueue.isEmpty());
    }

    @Test
    public void testRemoveNonExistentElement() {
        assertNull(priorityQueue.remove());
        assertTrue(priorityQueue.isEmpty());
    }

    @Test
    public void testRemoveSpecificElement() {
        priorityQueue.add(1);
        priorityQueue.add(2);
        priorityQueue.add(3);
        priorityQueue.remove(2);
        assertEquals(Integer.valueOf(1), priorityQueue.remove());
        assertEquals(Integer.valueOf(3), priorityQueue.remove());
        assertTrue(priorityQueue.isEmpty());
    }
}

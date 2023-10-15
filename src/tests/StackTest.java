package tests;

import exceptions.QueueIsEmptyException;
import model.structures.Stack;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class StackTest {
    private Stack<Integer> stack;

    @Before
    public void setUp() {
        stack = new Stack<>();
    }

    @Test
    public void testPush() {
        stack.push(5,"added",1);
        assertFalse(stack.isEmpty());
        assertEquals(Integer.valueOf(5), stack.peek());
    }

    @Test
    public void testPop() throws QueueIsEmptyException {
        stack.push(10,"added",2);
        assertEquals(Integer.valueOf(10), stack.pop());
        assertTrue(stack.isEmpty());
    }

    @Test
    public void testPeek() throws QueueIsEmptyException {
        stack.push(8,"added",3);
        assertEquals(Integer.valueOf(8), stack.peek());
        assertFalse(stack.isEmpty());
    }

    @Test
    public void testPopEmptyStack() {
        assertNull(stack.pop());
        assertTrue(stack.isEmpty());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(stack.isEmpty());
        stack.push(1,"added",4);
        assertFalse(stack.isEmpty());
        stack.pop();
        assertTrue(stack.isEmpty());
    }

    @Test
    public void testPopSpecificElement() throws QueueIsEmptyException {
        stack.push(1,"added",2);
        stack.push(2,"added",3);
        stack.push(3,"added",4);
        stack.pop(2);
        assertEquals(Integer.valueOf(2), stack.pop());
        assertEquals(Integer.valueOf(1), stack.pop());
        assertTrue(stack.isEmpty());
    }
}


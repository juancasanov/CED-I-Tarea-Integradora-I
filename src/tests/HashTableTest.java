package tests;

import model.classes.Task;
import model.structures.HashTable;
import org.junit.Test;
import java.util.Calendar;
import static org.junit.Assert.*;

public class HashTableTest {

    private HashTable<String, Task> taskList;

    private void scenario1() {
        taskList = new HashTable<>();
    }

    private Task[] scenario2() {
        taskList = new HashTable<>();

        Task task1 = new Task("Tarea Integradora I", "Quiero dormiiiiiir!", Calendar.getInstance(), 1);
        Task task2 = new Task("Tarea Integradora II", "Quiero dormir x2", Calendar.getInstance(), 2);
        Task task3 = new Task("Tarea Integradora III" , "Quiero dormir x3", Calendar.getInstance(), 3);

        taskList.put(task1.getTitle(), task1);
        taskList.put(task2.getTitle(), task2);
        taskList.put(task3.getTitle(), task3);

        return new Task[]{task1, task2, task3};
    }

    @Test
    public void testPut() {
        scenario1();

        Task task1 = new Task("Tarea Integradora I", "Quiero dormiiiiiir!", Calendar.getInstance(), 1);
        Task task2 = new Task("Tarea Integradora II", "Quiero dormir x2", Calendar.getInstance(), 2);
        Task task3 = new Task("Tarea Integradora III" , "Quiero dormir x3", Calendar.getInstance(), 3);

        assertEquals(task1, taskList.put(task1.getTitle(), task1));
        assertEquals(task2, taskList.put(task2.getTitle(), task2));
        assertEquals(task3, taskList.put(task3.getTitle(), task3));
    }

    @Test
    public void testGetValue() {
        scenario1();

        Task task1 = new Task("Tarea Integradora I", "Quiero dormiiiiiir!", Calendar.getInstance(), 1);
        Task task2 = new Task("Tarea Integradora II", "Quiero dormir x2", Calendar.getInstance(), 2);
        Task task3 = new Task("Tarea Integradora III" , "Quiero dormir x3", Calendar.getInstance(), 3);

        taskList.put(task1.getTitle(), task1);
        taskList.put(task2.getTitle(), task2);
        taskList.put(task3.getTitle(), task3);

        assertNotNull(task1);
        assertEquals(task1, taskList.getValue(task1.getTitle(), task1));
        assertNotNull(task2);
        assertEquals(task2, taskList.getValue(task2.getTitle(), task2));
        assertNotNull(task3);
        assertEquals(task3, taskList.getValue(task3.getTitle(), task3));
    }

    @Test
    public void testContainsKey() {
        scenario2();

        assertTrue(taskList.containsKey("Tarea Integradora I"));
        assertTrue(taskList.containsKey("Tarea Integradora II"));
        assertTrue(taskList.containsKey("Tarea Integradora II"));
    }

    @Test
    public void testContainsValue() {
        scenario1();

        Task task1 = new Task("Tarea Integradora I", "Quiero dormiiiiiir!", Calendar.getInstance(), 1);
        Task task2 = new Task("Tarea Integradora II", "Quiero dormir x2", Calendar.getInstance(), 2);
        Task task3 = new Task("Tarea Integradora III" , "Quiero dormir x3", Calendar.getInstance(), 3);

        taskList.put(task1.getTitle(), task1);
        taskList.put(task2.getTitle(), task2);
        taskList.put(task3.getTitle(), task3);

        assertTrue(taskList.containsValue(task1));
        assertTrue(taskList.containsValue(task2));
        assertTrue(taskList.containsValue(task3));
    }

    @Test
    public void testRemove() {
        Task[] tasks = scenario2();

        taskList.remove("Tarea Integradora I", tasks[0]);
        taskList.remove("Tarea Integradora II", tasks[1]);
        taskList.remove("Tarea Integradora III", tasks[2]);

        assertNull(taskList.getValue("Tarea Integradora I", tasks[0]));
        assertNull(taskList.getValue("Tarea Integradora II", tasks[1]));
        assertNull(taskList.getValue("Tarea Integradora III", tasks[2]));
    }
}

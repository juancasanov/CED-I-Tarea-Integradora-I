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
}

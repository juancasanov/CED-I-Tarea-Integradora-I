package tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import model.Controller;

import java.util.Calendar;

public class ControllerTest {
    private Controller controller;

    @Before
    public void setUp() {
        controller = new Controller();
    }

    @Test
    public void testAddTask() {
        // Asegúrate de que se agrega una tarea correctamente y que está en la tabla de tareas
        controller.addTask("Task1", "Description", Calendar.getInstance(), 1);
        assertTrue(controller.getTasks().containsKey("Task1"));
    }

    @Test
    public void testRemoveTask() {
        // Agrega una tarea y luego elimínala
        controller.addTask("Task1", "Description", Calendar.getInstance(), 1);
        assertEquals("The task was removed successfully!", controller.removeTask("Task1"));
        assertFalse(controller.getTasks().containsKey("Task1"));
    }

    @Test
    public void testGetPriorityTask() {
        // Asegúrate de que devuelva null si no hay tareas de alta prioridad
        assertNull(controller.getPriorityTask());

        // Agrega una tarea de alta prioridad y asegúrate de que se devuelva correctamente
        controller.addTask("Task1", "Description", Calendar.getInstance(), 2);
        assertNotNull(controller.getPriorityTask());
    }

    @Test
    public void testGetNonPriorityTask() {
        // Asegúrate de que devuelva null si no hay tareas de baja prioridad
        assertNull(controller.getNonPriorityTask());

        // Agrega una tarea de baja prioridad y asegúrate de que se devuelva correctamente
        controller.addTask("Task1", "Description", Calendar.getInstance(), 0);
        assertNotNull(controller.getNonPriorityTask());
    }
}


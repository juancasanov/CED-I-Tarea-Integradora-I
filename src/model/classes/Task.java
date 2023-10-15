package model.classes;

import java.util.Calendar;

public class Task implements Comparable<Task>, Cloneable {

    private String title;
    private String description;
    private Calendar deadline;
    private int priority;

// The `public Task(String title, String description, Calendar deadline, int priority)` is a
// constructor for the `Task` class. It is used to create a new `Task` object with the specified
// `title`, `description`, `deadline`, and `priority` values. The constructor initializes the instance
// variables of the `Task` object with the provided values.
    public Task(String title, String description, Calendar deadline, int priority){
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.priority = priority;
    }

/**
 * The getTitle() function returns the title of an object.
 * 
 * @return The method is returning the value of the variable "title".
 */
    public String getTitle() {
        return title;
    }

/**
 * The function sets the title of an object.
 * 
 * @param title The title parameter is a String that represents the new title for an object.
 */
    public void setTitle(String title) {
        this.title = title;
    }

/**
 * The getDescription() function returns the description of an object.
 * 
 * @return The method is returning the value of the variable "description".
 */
    public String getDescription() {
        return description;
    }

/**
 * The function sets the description of an object.
 * 
 * @param description The parameter "description" is a String that represents the description of an
 * object.
 */
    public void setDescription(String description) {
        this.description = description;
    }

/**
 * The function returns the deadline as a Calendar object.
 * 
 * @return The method is returning a Calendar object.
 */
    public Calendar getDeadline() {
        return deadline;
    }

/**
 * The function sets the deadline for a task.
 * 
 * @param deadline The deadline parameter is a Calendar object that represents the deadline for a task
 * or event.
 */
    public void setDeadline(Calendar deadline) {
        this.deadline = deadline;
    }

/**
 * The function returns the priority value.
 * 
 * @return The method is returning the value of the variable "priority".
 */
    public int getPriority() {
        return priority;
    }

/**
 * The function sets the priority of an object.
 * 
 * @param priority The priority parameter is an integer value that represents the priority level of an
 * object or task.
 */
    public void setPriority(int priority) {
        this.priority = priority;
    }

/**
 * This function compares two tasks based on their deadlines and priorities.
 * 
 * @param task The "task" parameter is an object of the "Task" class.
 * @return The method is returning an integer value.
 */
    @Override
    public int compareTo(Task task){
        int toReturn = 0;
        if(deadline.equals(task.getDeadline())){
            toReturn = priority - task.getPriority();
        }else{
            toReturn = task.getDeadline().compareTo(deadline);
        }
        return toReturn;
    }

/**
 * The toString() function returns a string representation of an object, including its description,
 * deadline, and priority.
 * 
 * @return The `toString()` method is returning a string representation of an object. The returned
 * string includes the description, deadline (as a long value representing the time), and priority of
 * the object.
 */
    @Override
    public String toString(){
        return "\nDescription: " + description + "\nDeadline: " + deadline.getTime() + "\nPriority: " + priority;
    }

/**
 * The `clone()` method in Java is overridden to create a deep copy of an object, including a deep copy
 * of the `deadline` field if it is not null.
 * 
 * @return The method is returning a cloned copy of the Task object.
 */
    @Override
    public Object clone() {
        try {
            // Llama al método clone de la clase Object para obtener una copia superficial.
            Task clonedTask = (Task) super.clone();

            // Realiza una copia profunda del campo "deadline" (Calendar).
            if (this.deadline != null) {
                clonedTask.deadline = (Calendar) this.deadline.clone();
            }

            return clonedTask;
        } catch (CloneNotSupportedException e) {
            // Esta excepción no debería ocurrir, ya que Task implementa Cloneable.
            throw new AssertionError();
        }
    }
}

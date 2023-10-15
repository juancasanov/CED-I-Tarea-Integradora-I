package model;

import exceptions.QueueIsEmptyException;
import model.classes.Task;
import model.nodes.NodeStack;
import model.structures.PriorityQueue;
import model.structures.Stack;
import model.structures.HashTable;
import model.structures.Queue;

import java.util.Calendar;
import exceptions.HashTableIsEmptyException;
import exceptions.NonExistentKeyException;

/**
 * The Controller class manages a task list by adding, modifying, and removing tasks, as well as
 * providing methods to retrieve the highest priority task and a non-priority task.
 */
public class Controller{

    private HashTable<String,Task> tasks;
    private Queue<Task> nonPriorityTasks;
    private PriorityQueue<Task> priorityTasks;
    private Stack<Task> actions;
    public Controller(){
// The code snippet is initializing four data structures: `tasks`, `nonPriorityTasks`, `priorityTasks`,
// and `actions`.
        tasks = new HashTable<String,Task>();
        nonPriorityTasks = new Queue<Task>();
        priorityTasks= new PriorityQueue<Task>();
        actions = new Stack<Task>();
    }

/**
 * The addTask function adds a new task to a task list, categorizing it as either a priority task or a
 * non-priority task.
 * 
 * @param title The title of the task. It is a string that represents the name or title of the task.
 * @param description The description parameter is a String that represents the description of the
 * task. It provides additional information or details about the task.
 * @param deadline The "deadline" parameter is a Calendar object that represents the due date and time
 * of the task.
 * @param priority The priority parameter is an integer that represents the priority level of the task.
 * A priority of 0 indicates a non-priority task, while any other positive integer represents a
 * priority task.
 */
    public void addTask(String title, String description, Calendar deadline, int priority){
        Task temp = new Task(title,description,deadline,priority);
        tasks.put(title,temp);
        if (priority == 0) {
            nonPriorityTasks.add(temp);
        } else {
            priorityTasks.add(temp);
        }
        actions.push(null,"added",(Task)temp.clone());
    }

/**
 * The function displays the contents of a hash table called "tasks".
 */
    public void displayTasks(){
        tasks.printHashTable();
    }

/**
 * The function returns a HashTable containing tasks, with the keys being strings and the values being
 * Task objects.
 * 
 * @return A HashTable object with keys of type String and values of type Task.
 */
    public HashTable<String,Task> getTasks(){
        return tasks;
    }

/**
 * The function modifies a task in a task list by updating its title, description, deadline, or
 * priority.
 * 
 * @param title The title of the task that you want to modify.
 * @param newTitle The new title for the task.
 * @param newDescription The `newDescription` parameter is a `String` that represents the new
 * description for the task.
 * @param deadline The deadline parameter is of type Calendar, which represents a specific point in
 * time. It is used to set the new deadline for the task being modified.
 * @param newPriority The newPriority parameter is an integer that represents the new priority of the
 * task.
 * @param option The "option" parameter is used to determine which aspect of the task needs to be
 * modified. The possible values for the "option" parameter are:
 * @return The method is returning a String message indicating whether the task was modified
 * successfully or if there was an error.
 */
    public String modifyTask(String title, String newTitle, String newDescription,Calendar deadline, int newPriority,int option) {
        String message = "The task was modified successfully!";
        Task oldTask = null;
        Task newTask = null;
        try {
            if (tasks.isEmpty()) {
                throw new HashTableIsEmptyException("The task list is empty.");
            }
            
            if (!tasks.containsKey(title)) {
                throw new NonExistentKeyException("Task with title '" + title + "' does not exist.");
            }
            oldTask = (Task) tasks.getValue(title).clone();
            newTask = (Task)tasks.getValue(title).clone();
            tasks.remove(title);
            
            switch (option) {
                case 1:
                    newTask.setTitle(newTitle);
                    break;
                case 2:
                    newTask.setDescription(newDescription);
                    break;
                case 3:
                    newTask.setDeadline(deadline);
                    break;
                case 4:
                    newTask.setPriority(newPriority);
                    break;
            }
            
            tasks.put(newTask.getTitle(), newTask);
            
            // Update priority/non-priority task lists
            if (oldTask.getPriority() == 0) {
                nonPriorityTasks.remove(oldTask);
                nonPriorityTasks.add(newTask);
            } else {
                priorityTasks.remove(oldTask);
                priorityTasks.add(newTask);
            }
            //System.out.println("Old==New? : " + (oldTask==newTask));
        } catch (HashTableIsEmptyException e) {
            message = e.getMessage();
        } catch (NonExistentKeyException e1) {
            message = e1.getMessage();
        }
        actions.push(oldTask,"modified",newTask);
        return message;
    }
    
/**
 * The removeTask function removes a task from a collection and updates the necessary data structures.
 * 
 * @param title The title of the task that needs to be removed.
 * @return The method is returning a message indicating whether the task was removed successfully or if
 * there was an error.
 */
    public String removeTask(String title){
        String message = "The task was removed successfully!";
        try{
            Task temp = tasks.getValue(title);
            tasks.remove(title);
            actions.push((Task) temp.clone(), "removed", null);
            priorityTasks.remove(temp);
            nonPriorityTasks.remove(temp);
        }catch(HashTableIsEmptyException e){
            message = e.getMessage();
            actions.push(null,"removed",null);
        }catch(NonExistentKeyException e1){
            message = e1.getMessage();
            actions.push(null,"removed",null);
        }
        return message;
    }

/**
 * The function returns a string containing the title and details of the highest priority task.
 * 
 * @return The method is returning a string that includes the title and details of the priority task.
 */
    public String getMessagePriorityTask(){
        String toReturn = "";
        toReturn = "Title: " +getPriorityTask().getTitle()+ getPriorityTask();
        return toReturn;
    }

/**
 * The function "getPriorityTask" returns the highest priority task from a queue, or null if the queue
 * is empty.
 * 
 * @return The method is returning a Task object.
 */
    public Task getPriorityTask(){
        Task toReturn;
        try{
            toReturn = priorityTasks.get();
        }catch(QueueIsEmptyException e){
            toReturn = null;
        }
        return toReturn;
    }

/**
 * The function returns a string containing the title and details of a non-priority task.
 * 
 * @return The method is returning a string that contains the title and details of a non-priority task.
 */
    public String getMessageNonPriorityTask(){
        String toReturn = "";
        toReturn = "Title: " +getNonPriorityTask().getTitle() +getNonPriorityTask();
        return toReturn;
    }

/**
 * The function returns a non-priority task from a queue, or null if the queue is empty.
 * 
 * @return The method is returning a Task object.
 */
    public Task getNonPriorityTask(){
        Task toReturn;
        try{
            toReturn = nonPriorityTasks.get();
        }catch(QueueIsEmptyException e){
            toReturn = null;
        }
        return toReturn;
    }

/**
 * The `undoAction` function is used to undo the last action performed in a task management system.
 */
    public void undoAction(){
        NodeStack<Task> action = actions.pop(0);
        if(action!=null){
            switch (action.getAction()){
                case "added":
                    tasks.remove(action.getS().getTitle());
                    if(action.getS().getPriority()==0){
                        nonPriorityTasks.remove(action.getS());
                    }else{
                        priorityTasks.remove(action.getS());
                    }
                    break;
                case "removed":
                    if(action.getT()!=null){
                        tasks.put(action.getT().getTitle(),action.getT());
                        if(action.getT().getPriority()==0){
                            nonPriorityTasks.add(action.getT());
                        }else{
                            priorityTasks.add(action.getT());
                        }
                    }
                    break;
                case "modified":
                    if(action.getT()!=null && action.getS()!=null){
                        System.out.println("T==S? = " + (action.getS()==action.getT()));
                        tasks.remove(action.getS().getTitle());
                        tasks.put(action.getT().getTitle(),action.getT());
                        if(action.getT().getPriority()==0){
                            nonPriorityTasks.remove(action.getS());
                            nonPriorityTasks.add(action.getT());
                        }else{
                            priorityTasks.remove(action.getS());
                            priorityTasks.add(action.getT());
                        }
                    }
                    break;
            }
        }
    }


}

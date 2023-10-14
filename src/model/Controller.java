package model;

import model.classes.Task;
import model.structures.PriorityQueue;
import model.templates.IList;
import model.structures.HashTable;
import model.structures.Queue;

import java.util.Calendar;
import exceptions.HashTableIsEmptyException;
import exceptions.NonExistentKeyException;

public class Controller{

    private HashTable<String,Task> tasks;
    private IList<Task> nonPriorityTasks;
    private IList<Task> priorityTasks;
    public Controller(){
        tasks = new HashTable<String,Task>();
        nonPriorityTasks = new Queue<Task>();
        priorityTasks = new PriorityQueue<Task>();
    }

    public void addTask(String title, String description, Calendar deadline, int priority){
        Task temp = new Task(title,description,deadline,priority);
        tasks.put(title,temp);
        switch(priority) {
            case 0:
                nonPriorityTasks.add(temp);
                break;
            default :
                priorityTasks.add(temp);
        }
    }

    public void displayTasks(){
        tasks.printHashTable();
    }

    public HashTable<String,Task> getTasks(){
        return tasks;
    }

    public String modifyTask(String title, String newTitle, String newDescription,Calendar deadline, int newPriority,int option) {
        String message = "The task was modified successfully!";
        try {
            if (tasks.isEmpty()) {
                System.out.println(tasks.size());
                throw new HashTableIsEmptyException("The task list is empty.");
            }
            
            if (!tasks.containsKey(title)) {
                throw new NonExistentKeyException("Task with title '" + title + "' does not exist.");
            }
            Task oldTask = tasks.getValue(title);
            Task newTask = tasks.getValue(title);
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
            
            tasks.put(newTitle, newTask);
            
            // Update priority/non-priority task lists
            if (newTask.getPriority() == 0) {
                nonPriorityTasks.remove(oldTask);
                nonPriorityTasks.add(newTask);
            } else {
                priorityTasks.remove(oldTask);
                priorityTasks.add(newTask);
            }

        } catch (HashTableIsEmptyException e) {
            message = e.getMessage();
        } catch (NonExistentKeyException e1) {
            message = e1.getMessage();
        }
        return message;
    }
    
    public String removeTask(String title){
        String message = "The task was removed successfully!";
        try{
            tasks.remove(title);
        }catch(HashTableIsEmptyException e){
            message = e.getMessage();
        }catch(NonExistentKeyException e1){
            message = e1.getMessage();
        }
        return message;
    }


}

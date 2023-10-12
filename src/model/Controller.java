package model;

import model.classes.Task;
import model.structures.PriorityQueue;
import model.templates.List;
import model.structures.HashTable;
import model.structures.Queue;

import java.util.Calendar;
import exceptions.HashTableIsEmptyException;
import exceptions.NonExistentKeyException;

public class Controller{

    private HashTable<String,Task> tasks;
    private List<Task> nonPriorityTasks;
    private List<Task> priorityTasks;
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

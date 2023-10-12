package model;

import model.classes.Task;
import model.templates.List;
import model.structures.HashTable;
import model.structures.Queue;
import model.templates.Hash;

import java.util.Calendar;
import exceptions.HashTableIsEmptyException;
import exceptions.NonExistentKeyException;

public class Controller{

    private HashTable<String,Task> tasks;
    public Controller(){
        tasks = new HashTable<String,Task>();
    }

    public void addTask(String title, String description, Calendar deadline, int priority){
        tasks.put(title,new Task(title,description,deadline,priority));
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

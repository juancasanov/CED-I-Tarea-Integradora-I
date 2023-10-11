package model;

import model.classes.Task;
import model.templates.List;
import model.structures.HashTable;
import model.structures.Queue;
import model.templates.Hash;

import java.util.Calendar;

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
}
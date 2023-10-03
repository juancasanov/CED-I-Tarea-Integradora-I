package model;

import model.classes.Task;
import model.templates.List;
import model.structures.Queue;

import java.util.Calendar;

public class Controller{

    private List<Task> tasks;
    public Controller(){
        tasks = new Queue<Task>();
    }

    public void addQueue(String title, String description, Calendar deadline, int priority){
        tasks.add(new Task(title,description,deadline,priority));
    }
}
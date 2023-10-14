package model;

import model.classes.Task;
import model.nodes.NodeStack;
import model.structures.PriorityQueue;
import model.structures.Stack;
import model.structures.HashTable;
import model.structures.Queue;

import java.util.Calendar;
import exceptions.HashTableIsEmptyException;
import exceptions.NonExistentKeyException;

public class Controller{

    private HashTable<String,Task> tasks;
    private Queue<Task> nonPriorityTasks;
    private PriorityQueue<Task> priorityTasks;
    private Stack<Task> actions;
    public Controller(){
        tasks = new HashTable<String,Task>();
        nonPriorityTasks = new Queue<Task>();
        priorityTasks = new PriorityQueue<Task>();
        actions = new Stack<Task>();
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
        actions.push(null,"added",(Task)temp.clone());
    }

    public void displayTasks(){
        tasks.printHashTable();
    }

    public HashTable<String,Task> getTasks(){
        return tasks;
    }

    public String modifyTask(String title, String newTitle, String newDescription,Calendar deadline, int newPriority,int option) {
        String message = "The task was modified successfully!";
        Task oldTask = null;
        Task newTask = null;
        try {
            if (tasks.isEmpty()) {
                System.out.println(tasks.size());
                throw new HashTableIsEmptyException("The task list is empty.");
            }
            
            if (!tasks.containsKey(title)) {
                throw new NonExistentKeyException("Task with title '" + title + "' does not exist.");
            }
            oldTask = (Task) tasks.getValue(title).clone();
            newTask = tasks.getValue(title);
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
            System.out.println("Old==New? : " + (oldTask==newTask));
        } catch (HashTableIsEmptyException e) {
            message = e.getMessage();
        } catch (NonExistentKeyException e1) {
            message = e1.getMessage();
        }
        actions.push(oldTask,"modified",newTask);
        return message;
    }
    
    public String removeTask(String title){
        String message = "The task was removed successfully!";
        try{
            tasks.remove(title);
            actions.push((Task)tasks.getValue(title).clone(),"removed",null);
        }catch(HashTableIsEmptyException e){
            message = e.getMessage();
            actions.push(null,"removed",null);
        }catch(NonExistentKeyException e1){
            message = e1.getMessage();
            actions.push(null,"removed",null);
        }
        return message;
    }

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

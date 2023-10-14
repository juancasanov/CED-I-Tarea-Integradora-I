package model.classes;

import java.util.Calendar;

public class Task implements Comparable<Task> {

    private String title;
    private String description;
    private Calendar deadline;
    private int priority;

    public Task(String title, String description, Calendar deadline, int priority){
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Calendar getDeadline() {
        return deadline;
    }

    public void setDeadline(Calendar deadline) {
        this.deadline = deadline;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public int compareTo(Task task){
        int toReturn = 0;
        if(deadline==task.getDeadline()){
            toReturn = priority - task.getPriority();
        }else{
            toReturn = deadline.compareTo(task.getDeadline());
        }
        return toReturn;
    }

    @Override
    public String toString(){
        return "\nDescription: " + description + "\nDeadline: " + deadline.getTime() + "\nPriority: " + priority;
    }
}

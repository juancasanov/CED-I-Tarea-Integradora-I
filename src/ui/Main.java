/**
 * The Main class is a user interface that allows users to add tasks, display tasks, undo actions, and
 * exit the program.
 */
package ui;

import model.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.text.ParseException;

public class Main {

    private Controller controller;
    private BufferedReader reader;

    public Main() {
        controller = new Controller();
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public void showMenu() {
        System.out.println("Menu:");
        System.out.println("1. Add Task");
        System.out.println("2. Show Tasks");
        System.out.println("3. Modify Tasks");
        System.out.println("4. Remove Tasks");
        System.out.println("5. Undo");
        System.out.println("6. Modify, Finish (Delete) Priority Task");
        System.out.println("7. Modify, Finish (Delete) Non Priority Task");
        System.out.println("8. Exit");
        System.out.print("Select an option: ");
    }

    public void executeOption(int option) {
        switch (option) {
            case 1:
                addTask();
                break;
            case 2:
                displayTasks();
                break;
            case 3:
                modifyTask("");
                break;
            case 4:
                removeTasks();
                break;
            case 5:
                System.out.println("Undoing last action...");
                controller.undoAction();
                break;
            case 6:
                actionsWithPriorityTask();
                break;
            case 7:
                actionsWithNonPriorityTask();
                break;
            case 8:
                System.out.println("Exiting the program...");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid option. Please select a valid option.");
                break;
        }
    }    

    public void start() {
        while (true) {
            showMenu();
            int option = validateIntegerInput();
            executeOption(option);
        }
    }

    public static void main(String[] args) {
        Main view = new Main();
        view.start();
        try {
            view.reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void addTask() {
        System.out.print("Enter task title: ");
        String title = getStringInput();
        System.out.print("Enter description of the task: ");
        String description = getStringInput();
        System.out.print("Enter task due date (yyyy-mm-dd): ");
        String dueDateString = getStringInput();
        Calendar deadline = Calendar.getInstance();

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            deadline.setTime(sdf.parse(dueDateString));
        } catch (ParseException e) {
            System.out.println("Invalid date format. Task not added.");
            return;
        }

        System.out.println("Enter the priority of the task 1. Priority 2. Non priority:");
        int isPriority = validateIntegerInput();
        if(isPriority!=1 && isPriority!=2){
            System.out.println("The option typed is incorrect, Task was not created");
            return;
        }
        int priority = 0;

        if (isPriority == 1) {
            System.out.println("Enter priority of the task (1-5) 1. Highest 5. Lowest");
            priority = validateIntegerInput();
            if(priority<1 || priority>5){
                System.out.println("The priority typed is incorrect, Task was not created");
                return;
            }
        }

        controller.addTask(title, description, deadline, priority);
        System.out.println("Task added successfully!");
    }
    private void displayTasks() {
        controller.displayTasks();
    }

    private void modifyTask(String title){
        String newTitle = "";
        String newDescription = "";
        String newDeadline = "";
        int newPriority = 0;
        Calendar deadline = Calendar.getInstance();
        if(title.isEmpty()) {
            System.out.println("Enter the title of the task to modify:");
            title = getStringInput();
        }

        System.out.println("Which attribute do you want to modify? (1. Title, 2. Description, 3. Deadline, 4. Priority)");
        int option = validateIntegerInput();
        if(option==-1){
            System.out.println("Option typed was incorrect, Task will not be modified");
            return;
        }

        switch(option){
            case 1:
                System.out.println("Enter the new title:");
                newTitle = getStringInput();
                break;
            case 2:
                System.out.println("Enter the new description:");
                newDescription = getStringInput();
                break;
            case 3:
                System.out.println("Enter the new deadline:");
                newDeadline = getStringInput();
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    deadline.setTime(sdf.parse(newDeadline));
                } catch (ParseException e) {
                    System.out.println("Invalid date format. Task not added.");
                    return;
                }
                break;
            case 4:
                System.out.println("Enter the new priority:");
                newPriority = validateIntegerInput();
                if(newPriority<0 || newPriority>5){
                    System.out.println("The priority typed was incorrect, Task will not be modified");
                    return;
                }
                break;
            default:
                System.out.println("Invalid option. Please select a valid option.");
                return;
        }
        System.out.println(controller.modifyTask(title,newTitle,newDescription,deadline,newPriority,option));
    }
    
    public void removeTasks() {
        System.out.println("Write the task you want to remove:");
        String title = getStringInput();
        System.out.println(controller.removeTask(title));
    }

    public void actionsWithPriorityTask(){
        if(controller.getPriorityTask()!=null){
            System.out.println(controller.getMessagePriorityTask());
            System.out.println("What would you like to do?");
            System.out.println("1. Modify, 2. Finish (Delete)");
            System.out.print("Option: ");
            int option = validateIntegerInput();
            if(option!=1 && option!=2){
                System.out.println("Options typed are incorrect, the task will not be modify.");
                return;
            }
            if(option==1){
                modifyTask(controller.getPriorityTask().getTitle());
            }else{
                controller.removeTask(controller.getPriorityTask().getTitle());
                System.out.println("The task has been removed");
            }
        }else{
            System.out.println("There's no priority task.");
        }
    }

    public void actionsWithNonPriorityTask(){
            if(controller.getNonPriorityTask()!=null){
            System.out.println(controller.getMessageNonPriorityTask());
            System.out.println("What would you like to do?");
            System.out.print("1. Modify, 2. Finish (Delete)");
            System.out.print("Option: ");
            int option = validateIntegerInput();
            if(option!=1 && option!=2){
                System.out.println("Options typed are incorrect, the task will not be modify.");
                return;
            }
            if(option==1){
                modifyTask(controller.getNonPriorityTask().getTitle());
            }else{
                controller.removeTask(controller.getNonPriorityTask().getTitle());
                System.out.println("The task has been removed");
            }
        }else{
            System.out.println("There's no non priority task.");
        }
    }

    private int validateIntegerInput() {
        int input = -1;
        try {
            String read = reader.readLine();

            if(isInteger(read)){
                input = Integer.parseInt(read);
            }
        } catch (IOException e) {
            reader = new BufferedReader(new InputStreamReader(System.in));
        }
        return input;
    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private String getStringInput(){
        String input = "";
        try{
            input = reader.readLine();
        }catch(IOException e){
            reader = new BufferedReader(new InputStreamReader(System.in));
        }
        return input;
    }
}

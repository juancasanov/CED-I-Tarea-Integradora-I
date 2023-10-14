/**
 * The Main class is a user interface that allows users to add tasks, display tasks, undo actions, and
 * exit the program.
 */
package ui;

import model.Controller;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;
import java.text.ParseException;
import model.structures.HashTable;
import model.classes.Task;

public class Main {

    private Controller controller;
    private Scanner reader;

    public Main() {
        controller = new Controller();
        reader = new Scanner(System.in);
    }

    public void showMenu() {
        System.out.println("Menu:");
        System.out.println("1. Add Task");
        System.out.println("2. Show Tasks");
        System.out.println("3. Modify Tasks");
        System.out.println("4. Remove Tasks");
        System.out.println("5. Undo");
        System.out.println("6. Exit");
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
                modifyTask();
                break;
            case 4:
                removeTasks();
                break;
            case 5:
                System.out.println("Undoing last action...");
                break;
            case 6:
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
            int option = reader.nextInt();
            reader.nextLine();
            executeOption(option);
        }
    }

    public static void main(String[] args) {
        Main view = new Main();
        view.start();
    }

    private void addTask() {
        System.out.print("Enter task title: ");
        String title = reader.nextLine();
        System.out.print("Enter description of the task: ");
        String description = reader.nextLine();
        System.out.print("Enter task due date (yyyy-mm-dd): ");
        String dueDateString = reader.nextLine();
        Calendar deadline = Calendar.getInstance();

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            deadline.setTime(sdf.parse(dueDateString));
        } catch (ParseException e) {
            System.out.println("Invalid date format. Task not added.");
            return;
        }

        System.out.println("Enter the priority of the task 1. Priority 2. Non priority:");
        
        int isPriority = reader.nextInt();
        int priority = 0;

        if (isPriority == 1) {
            System.out.println("Enter priority of the task (1-5) 1. Highest 5. Lowest");
            priority = reader.nextInt();
            reader.nextLine(); // Clear the scanner buffer
        }

        controller.addTask(title, description, deadline, priority);
        System.out.println("Task added successfully!");
    }
    private void displayTasks() {
        controller.displayTasks();
    }

    private void modifyTask(){
        String newTitle = "";
        String newDescription = "";
        String newDeadline = "";
        int newPriority = 0;
        Calendar deadline = Calendar.getInstance();
        System.out.println("Enter the title of the task to modify:");
        String title = reader.nextLine();  
        //reader.nextLine();

        System.out.println("Which attribute do you want to modify? (1. Title, 2. Description, 3. Deadline, 4. Priority)");
        int option = reader.nextInt();

        switch(option){
            case 1:
                reader.nextLine();
                System.out.println("Enter the new title:");
                newTitle = reader.nextLine();
                break;
            case 2:
                reader.nextLine();
                System.out.println("Enter the new description:");
                newDescription = reader.nextLine();
                break;
            case 3:
                reader.nextLine();
                System.out.println("Enter the new deadline:");
                newDeadline = reader.nextLine();
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                deadline.setTime(sdf.parse(newDeadline));
            } catch (ParseException e) {
                System.out.println("Invalid date format. Task not added.");
                return;
        }
                break;
            case 4:
                reader.nextLine();
                System.out.println("Enter the new priority:");
                newPriority = reader.nextInt();
                break;
            default:
                System.out.println("Invalid option. Please select a valid option.");
        }
        System.out.println(controller.modifyTask(title,newTitle,newDescription,deadline,newPriority,option));
    }
    
    public void removeTasks() {

        System.out.println("Write the task you want to remove:");
        String title = reader.nextLine();
        System.out.println(controller.removeTask(title));
        //System.out.println("The task was removed successfully!");
    }
}

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
        System.out.println("3. Remove Tasks");
        System.out.println("4. Undo");
        System.out.println("5. Exit");
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
                removeTasks();
                break;
            case 4:
                System.out.println("Undoing last action...");
                break;
            case 5:
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

        System.out.print("Enter task priority (1-5): ");
        int priority = reader.nextInt();
        reader.nextLine();  // Clear the scanner buffer
        controller.addTask(title, description, deadline, priority);
        System.out.println("Task added successfully!");
    }
    private void displayTasks() {
        controller.getTasks().printHashTable();
    }

    public void removeTasks() {

        System.out.println("Write the task you want to remove:");
        String title = reader.nextLine();
        controller.removeTask(title);
        System.out.println("The task was removed successfully!");
    }
}

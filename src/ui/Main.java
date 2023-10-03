package ui;

import model.Controller;
import java.util.Scanner;

public class Main{

    private Controller controller;
    private Scanner reader;

    public Main(){
        controller = new Controller();
        reader = new Scanner(System.in);
    }
    
    public static void main(String[] args){
        Main view = new Main();
    }
}
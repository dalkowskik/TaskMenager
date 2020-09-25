package pl.coderslab;


import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import pl.coderslab.utiks.ConsoleColors;

import java.io.*;
import java.sql.Array;
import java.util.Arrays;
import java.util.Scanner;


public class Main {


    static final String nameoffile = "tasks.csv";
    static String[][] tasks;


    public static void main(String[] args) {
        try {
            String a = "tasks.csv";
            loadtasks(a);
        } catch (FileNotFoundException a) {
        }
        String[] taboptions = {"add", "remove", "list", "exit"};
        options();
        select();
    }

    public static void options() {
        String[] taboptions = {"add", "remove", "list", "exit"};
        System.out.println(ConsoleColors.BLUE + "Please select an option:" + ConsoleColors.RESET);
        for (String options : taboptions) {
            System.out.println(options);
        }
    }

    public static void select() {

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String option = scanner.next();
            switch (option) {
                case "add":
                    addtask();
                    break;
                case "list":
                    listtasks();
                    break;
                case "exit":
                    exit();
                    break;
                case "remove":
                    removeTask();
                    break;
                default:
                    System.out.println("Please select a correct option." + "\n");
            }
            options();
        }
    }

    public static void loadtasks(String file) throws FileNotFoundException {

        File filetasks = new File(file);
        StringBuilder reading = new StringBuilder();
        Scanner scan = new Scanner(filetasks);
        while (scan.hasNextLine()) {
            reading.append(scan.nextLine() + "  ");
        }
    }


    public static void listtasks() {

        File file = new File("tasks.csv");
        StringBuilder reading = new StringBuilder();
        try {
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                reading.append(scan.nextLine() + "\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println("no file");
        }
        System.out.println(reading.toString());
    }


    public static void addtask() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please add task description");
        String description = scanner.nextLine();
        System.out.println("Please add task date");
        String date = scanner.nextLine();
        System.out.println("Is your task is important: true/false");
        String important = scanner.nextLine();


        try (
                FileWriter fileWriter = new FileWriter("tasks.csv", true)) {
            fileWriter.append(description + ", " + date + ", " + important + "\n");
            System.out.println(ConsoleColors.YELLOW+"Done!"+ConsoleColors.RESET+"\n");
        } catch (IOException ex) {
            System.out.println("Error writing into file");
        }
    }

    public static void removeTask() {

        System.out.println("\n" + "Tell me name of task which you want to remove: " + "\n");


        File file = new File("tasks.csv");
        StringBuilder reading = new StringBuilder();
        try {
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                reading.append(scan.nextLine() + "--");
            }
        } catch (FileNotFoundException e) {
            System.out.println("no file");
        }
        String a = reading.toString();
        String tab[] = a.split("--");
        for (int i = 0; i < tab.length; i++) {
            System.out.println(i + ": " + tab[i]);
        }

        Scanner scan1 = new Scanner(System.in);
        System.out.println(" ");
        String removeline = scan1.nextLine();
        int count=0;

        for (int i = 0; i < tab.length; i++) {
            if (tab[i].contains(removeline)) {
                tab = ArrayUtils.removeElement(tab, tab[i]);
                count+=1;
            }
        }
        if(count==0){
            System.out.println("\n"+"task does not exist"+"\n");
        }else{
            System.out.println(ConsoleColors.YELLOW+"Done!"+ConsoleColors.RESET+"\n");
        }


        try (PrintWriter printWriter = new PrintWriter("tasks.csv")) {
            for (int i = 0; i < tab.length; i++) {
                printWriter.println(tab[i]);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Error writing to file");
        }
    }


    public static void exit() {

        System.out.println("\n"+ConsoleColors.BLUE+ "Goodbye!"+ConsoleColors.RESET);
        System.out.println("\n"+"\n"+ConsoleColors.CYAN_BOLD+ "the program was created by Kamil Dalkowski"+"\n"+"while attending the CodersLab course"+ConsoleColors.RESET);
        System.exit(0);
    }


}

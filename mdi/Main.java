
package mdi;

import moes.Moes;
import product.Media;
import customer.Student;
import java.util.Scanner;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;


public class Main {

    private static final String extension = ".moes";
    private static final String magicCookie = "MOES";
    private static final String fileVersion = "1";

    private Moes moes;
    private Menu menu;
    private boolean running = true;
    private Scanner scanner = new Scanner(System.in); 

    private String filename;
   
    public Main(String[] args) {

        System.out.println("                     \\\\\\ ///");
        System.out.println("                    /  _   _\\");
        System.out.println("                  (| (.) (.) |)");
        System.out.println(" ---------------.oooo--()--oooo.---------------");
        System.out.println("|                                              |");
        System.out.println("|  Mavs Online Entertainment System (MOES)     |");
        System.out.println("|  Version 22.0.2       Sunil Thapa Magar      |");
        System.out.println("|                                              |");
        System.out.println(" ------------------------------------------------");
        
        
        menu = new Menu();
        running = true;


        if (args.length > 0) {
            filename = args[0];
            if (!filename.endsWith(extension)) {
                filename += extension;
            }
            fileload();
        } else {
            moes = new Moes();
        }


        menu.addMenuItem(new MenuItem("Exit", () -> {
            endApp();
        }));
        menu.addMenuItem(new MenuItem("Play media", () -> {
            playMedia();
        }));
        menu.addMenuItem(new MenuItem("List media", () -> {
            listMedia();
        }));
        menu.addMenuItem(new MenuItem("List available points", () -> {
            listAvailablePoints();
        }));
        menu.addMenuItem(new MenuItem("Buy points", () -> {
            buyPoints();
        }));
        menu.addMenuItem(new MenuItem("Add media", () -> {
            addMedia();
        }));
        menu.addMenuItem(new MenuItem("List all students", () -> {
            listStudents();
        }));
        menu.addMenuItem(new MenuItem("Add a student", () -> {
            addStudent();
        })); 
    }

    public static void main(String[] args) {
        Main app = new Main(args);
        app.mdi();
    }

    
   
    public void mdi() {
        System.out.println("Welcome to Mavs Online Entertainment System (MOES)");


        while (running) {
      
            System.out.println(menu);
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 
            menu.run(choice);
        }
    }

    private void fileload() {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String cookieLine = br.readLine();
            if (!magicCookie.equals(cookieLine)) {
                throw new IOException("Invalid format of file");
            }

            String version = br.readLine();
            if (!fileVersion.equals(version)) {
                throw new IOException("Unsupported file version: " + version);
            }

            moes = new Moes(br);
            System.out.println("File loaded!!");
        } catch (IOException e) {
            System.out.println("File opening error: " + e.getMessage());
            moes = new Moes();
        }
    }

    private void fileSave() {
        if (filename == null) {
            System.out.print("Enter filename to be saved: ");
            filename = scanner.nextLine().trim();
            if (!filename.endsWith(extension)) {
                filename += extension;
            }
        }

        try {
            File file = new File(filename);
            if (file.exists()) {
                String backupFilename = filename + "~";
                File backupFile = new File(backupFilename);

                if (backupFile.exists()) {
                    backupFile.delete();
                }

                if (!file.renameTo(backupFile)) {
                    System.out.println("Unable to create backup file!!!");
                }
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
                bw.write(magicCookie + "\n");
                bw.write(fileVersion + "\n");
                moes.save(bw);
                System.out.println("File saved successfully!!!");
            }
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }
   

    // Existing methods (addStudent, listStudents, addMedia, listMedia, playMedia, listAvailablePoints, buyPoints) remain unchanged


    // Method to add a student
    private void addStudent() {
        System.out.print("Enter student's name: ");
        String name = scanner.nextLine();
        System.out.print("Enter student's ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter student's email: ");
        String email = scanner.nextLine();
        System.out.print("Unlimited account? (yes/no): ");
        boolean isUnlimited = scanner.nextLine().equalsIgnoreCase("yes");
        Student student = new Student(name, id, email, isUnlimited);
        moes.addStudent(student);
        System.out.println("Student Added");
        fileSave();
    }


    // Method to list all students
    private void listStudents() {
        String studentList = moes.getStudentList();
        System.out.println(studentList);
    }


    // Method to add media
    private void addMedia() {
        System.out.print("Enter media title: ");
        String title = scanner.nextLine();
        System.out.print("Enter media URL: ");
        String url = scanner.nextLine();
        System.out.print("Enter media points: ");
        int points = scanner.nextInt();
        scanner.nextLine(); 
        Media media = new Media(title, url, points);
        moes.addMedia(media);
        System.out.println("Media added");
    }


    // Method to list all media
    private void listMedia() {
        String mediaList = moes.getMediaList();
        System.out.println(mediaList);
    }


    // Method to play media
    private void playMedia() {
      
        // Display students and ask for user input
        listStudents();
        System.out.print("Enter student index: ");
        int studentIndex = scanner.nextInt();
        scanner.nextLine();
        
        // Display available points for selected student
        int points = moes.getPoints(studentIndex);
        if (points == Integer.MAX_VALUE) {
            System.out.println("Unlimited Account");
        } else {
            System.out.println("Available points: " + points);
        }

        // Display media and ask for user input
        listMedia();
        System.out.print("Enter media index: ");
        int mediaIndex = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Play the media and display the result
        String result = moes.playMedia(studentIndex, mediaIndex);
        System.out.println(result);

        // Show remaining points for Alacarte accounts
        if (points != Integer.MAX_VALUE) {
            System.out.println("Points remaining: " + moes.getPoints(studentIndex));
        }
    }


    // Method to list available points for a student
    private void listAvailablePoints() {
        listStudents();
        System.out.print("Enter student index: ");
        int studentIndex = scanner.nextInt();
        scanner.nextLine(); 
        int points = moes.getPoints(studentIndex);
        if (points == Integer.MAX_VALUE) {
            System.out.println("Unlimited Account: Infinite points available.");
        } else {
            System.out.println("Available points: " + points);
        }
    }


    // Method to buy points for a student
    private void buyPoints() {
        listStudents();
        System.out.print("Enter student index: ");
        int studentIndex = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Current points: " + moes.getPoints(studentIndex));
        System.out.print("Enter points to buy: ");
        int additionalPoints = scanner.nextInt();
        scanner.nextLine();

        if (additionalPoints > 0) {
            String result = moes.buyPoints(studentIndex, additionalPoints);
            System.out.println(result);
        } else {
            System.out.println("Invalid points entered.");
        }
    }


    // End the application
    private void endApp() {
        System.out.println("Exiting application...");
        running = false;
    }
}


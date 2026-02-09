package moes;

import customer.Account;
import customer.Student;
import customer.Alacarte;
import customer.Unlimited;
import product.Media;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * The Moes class manages ArrayLists containing Student and Media objects 
 * 
 * @author Sunil Thapa Magar
 * @version 0.2
 * @since 2024
 */

public class Moes {
    private List<Media> library = new ArrayList<>();     //media objects
    private List<Student> customers = new ArrayList<>();   //student objects

    /**
     * Adds a Media object to library
     * 
     * @param media The Media object to be added.
     * @since 2024
     */

    public Moes() {
        library = new ArrayList<>();
        customers = new ArrayList<>();
    }


    public void addMedia(Media media){
        library.add(media);
    }

    /**
     * Returns the formatted list of media objects .
     * 
     * @return A string representation of the media list.
     * @since 2024
     */

    public String getMediaList() {
        StringBuilder mediaList = new StringBuilder();
        for (int i = 0; i < library.size(); i++) {
            Media media = library.get(i);
            mediaList.append(i).append(") ").append(media.toString()).append("\n");
        }
        return mediaList.toString();
    }

    /**
     *Adds a Student object to the customers list.
    * 
    * @param student The student object to be added.
    * @since 2024
    */

    public void addStudent(Student student){
        customers.add(student);
    }

    /**
     * Returns the formatted list
     * 
     * @return A string representation of the student list.
     * @since 2024
     */

    public String getStudentList(){
        StringBuilder studentList= new StringBuilder();
    
        for (int i=0; i<customers.size(); i++){
            Student student= customers.get(i);
            studentList.append(i).append(") ").append(student.toString()).append("\n");
        }
        return studentList.toString();
    }
    
    /**
     * Obtains the points for the selected student based on their account.
     * @param studentIndex The index of student in list.
     * @return The points remaining.
     * @since 2024
     */

    public int getPoints(int studentIndex) {
        
        Student student = customers.get(studentIndex);
        Account account = student.getAccount();

        if (account instanceof Alacarte) {
            return ((Alacarte) account).getPointsRemaining();
        } else if (account instanceof Unlimited) {
            return Integer.MAX_VALUE;
        } else {
            throw new UnsupportedOperationException("Unknown subclass of Account");
        }
    }
    
    /**
     * Buys points for the selected student based on their account.
     * @param studentIndex The index of student in list.
     * @param points The number of points to buy.
     * @return A message indicating the result
     * @since 2024
     */

    public String buyPoints(int studentIndex, int points) {
        
        Student student = customers.get(studentIndex);
        Account account = student.getAccount();

        if (account instanceof Alacarte) {
            ((Alacarte) account).buyPoints(points);
            return "Points purchased. Current balance: " + ((Alacarte) account).getPointsRemaining() + " points.";
        } else if (account instanceof Unlimited) {
            return "Unlimited account; no additional points needed.";
        } else {
            throw new UnsupportedOperationException("Unknown subclass of Account");
        }
    }

    /**
     * Plays the media for the selected student
     * 
     * @param studentIndex The index of student in list.
     * @param mediaIndex The index of media in list.
     * @return A message that indicates the result.
     * @since 2024
     */


    public String playMedia(int studentIndex, int mediaIndex) {
       
        Student student = customers.get(studentIndex);
        Media media = library.get(mediaIndex);
        return student.requestMedia(media);
    }


    public void save(BufferedWriter bw) throws IOException {
       
        bw.write(Integer.toString(library.size()) + '\n');
        for (Media media : library) {
            media.save(bw); 
        }

        bw.write(Integer.toString(customers.size()) + '\n');
        for (Student student : customers) {
            student.save(bw); 
        }
    }

    
    public Moes(BufferedReader br) throws IOException {

        int mediaSize = Integer.parseInt(br.readLine());
        library = new ArrayList<>();
        for (int i = 0; i < mediaSize; i++) {
            library.add(new Media(br));
        }

        int studentSize = Integer.parseInt(br.readLine());
        customers = new ArrayList<>();
        for (int i = 0; i < studentSize; i++) {
            customers.add(new Student(br));
        }
    }

    
}
    





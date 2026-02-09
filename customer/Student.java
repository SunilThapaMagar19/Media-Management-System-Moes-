package customer;

import product.Media;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Represents a student with a name, ID, email, and an account.
 * @author Sunil Thapa Magar
 * @version 2.0
 * @since 2024
 */

public class Student {
    private String name;
    private String id;
    private String email;
    private Account account;

     /**
     * Constructs a Student with the specified details.
     * @param name the name of the student
     * @param id the ID of the student
     * @param email the email address of the student
     * @param unlimited if true, an Unlimited account is created; otherwise, an Alacarte account is created
     * @throws IllegalArgumentException if the email is not a UTA email address
     * @since 2024
     */

    public Student(String name, String id, String email, boolean unlimited) {
        if (!email.endsWith("@uta.edu") && !email.endsWith("@mavs.uta.edu")) {
            throw new IllegalArgumentException("Non-UTA email address: " + email);
        }

        this.name = name;
        this.id = id;
        this.email = email;

        if (unlimited) {
            this.account = new Unlimited();
        } else {
            this.account = new Alacarte();
        }

    }

    public Student(BufferedReader br) throws IOException {
        this.name = br.readLine();
        this.id = br.readLine();
        this.email = br.readLine();
        
        String accountType = br.readLine();
        switch (accountType) {
            case "customer.Unlimited":
                this.account = new Unlimited(br);
                break;
            case "customer.Alacarte":
                this.account = new Alacarte(br);
                break;
            default:
                throw new IOException("Unknown account type: " + accountType);
        }
    }

    /**
     * Gets the account associated with this student.
     * @return account 
     * @since 2024
    */

    public Account getAccount(){
        return account;
    }

     /**
     * Requests to play the media.
     * @param media the media to be played
     * @return a message indicating the result of the play request
     * @since 2024
     */

    public String requestMedia(Media media){
        return account.play(media);
    }

    /**
     * Returns a string representation of the student including their details.
     * @return a string representation of the student
     * @since 2024
     */

    @Override

    public String toString(){
        return name + " (" + id + ", " + email + ", Account #" + account.getAccountNumber() + ")"; 
    }

    public void save(BufferedWriter bw) throws IOException {
        bw.write(name + '\n');
        bw.write(id + '\n');
        bw.write(email + '\n');
        bw.write(account.getClass().getName() + '\n');
        account.save(bw);
    }


}
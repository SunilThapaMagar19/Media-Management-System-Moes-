package customer;

import product.Media;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;



/**
 * Abstract class that represents a student account in the MOES System.
 * Provides methods to change the account number and media access.
 * 
 * @author Sunil Thapa Magar
 * @version 0.2
 * @since 2024
 */

public abstract class Account {

    private static int nextAccountNumber = 1;
    private int accountNumber;

     /**
     * Constructs an Account with a unique account number.
     */
    
    public Account() {
        this.accountNumber = nextAccountNumber++;
    }

    /**
     * Gets the account number of student account.
     * @return the account number
     * @since 2024
     */

    public Account(BufferedReader br) throws IOException {
        this.accountNumber = Integer.parseInt(br.readLine());
        nextAccountNumber = Integer.parseInt(br.readLine());
    }


    public int getAccountNumber(){
        return accountNumber;
    }

    /**
     * Plays the given media if the account has sufficient points.
     * @param media the media to be played
     * @return a message to indicate whether the media is being played or if additional points are required
     * @since 2024
     */

    public abstract String play(Media media);  

    public void save(BufferedWriter bw) throws IOException {
        bw.write(Integer.toString(accountNumber) + '\n');
        bw.write(Integer.toString(nextAccountNumber) + '\n');
    }

}


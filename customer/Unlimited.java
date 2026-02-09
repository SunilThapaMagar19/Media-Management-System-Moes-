package customer;

import product.Media;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;


/**
 * Represents an Unlimited account 
 * @author Sunil Thapa Magar
 * @version 2.0
 * @since 2024
 */

public class Unlimited extends Account {


     /**
     * Constructs an Unlimited account.
     */
    /**
     * Media can be played without restrictions.
     * @param media the media to be played
     * @return a message indicating that the media is being played
     * @since 2024
     */

    public Unlimited() {
        super(); // Call the superclass constructor
    }

    public Unlimited(BufferedReader br) throws IOException {
        super(br);
    }


    @Override
    public String play(Media media){
        return "Playing" + media.toString();
    }
    
    @Override
    public void save(BufferedWriter bw) throws IOException {
        super.save(bw);
    }
    
}

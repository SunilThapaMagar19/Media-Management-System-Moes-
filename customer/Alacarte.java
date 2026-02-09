
package customer;

import product.Media;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Represents an Alacarte account which allows playing media based on purchased points.
 * @author Sunil Thapa Magar
 * @version 2.0
 * @since 2024
 */


public class Alacarte extends Account {

    private int pointsRemaining;

    /**
     * Constructs an Alacarte with 0 points.
    */

    public Alacarte() {
        super();  // Call superclass constructor
        pointsRemaining = 0;
    }

    public Alacarte(BufferedReader br) throws IOException {
        super(br);
        this.pointsRemaining = Integer.parseInt(br.readLine());
    }


    /**
     * Adds points to the Alacarte account.
     * @param points the number of points to be added
     * @since 2024
     */

    public void buyPoints(int points) {
        pointsRemaining = pointsRemaining + points; 
    }

       /**
     * Gets the number of points remaining.
     * @return the number of points remaining
     * @since 2024
     */

    public int getPointsRemaining() {
        return pointsRemaining;
    }

    /**
     * Plays the given media only if there are enough points.
     * @param media the media to be played
     * @return a message to indicate whether the media is being played or if additional points are required
     * @since 2024
     */

    @Override
    public String play(Media media) {
        if (pointsRemaining >= media.getPoints()) {
            pointsRemaining -= media.getPoints();
            return "Playing " + media.toString();
        } else {
            return "Buy more points: Requires " + media.getPoints() + " points, you have " + pointsRemaining + " points";
        }
    }


    public void save(BufferedWriter bw) throws IOException {
        super.save(bw);
        bw.write(Integer.toString(pointsRemaining) + '\n');
    }
}



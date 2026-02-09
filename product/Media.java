
package product;



import java.net.MalformedURLException;
import java.net.URL;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;

/** 
 * Represents a media item with a title, URL, and points.
 * @author Sunil Thapa Magar
 * @version 0.2
 * @since 2024
 */

public class Media {

    private String title;
    private String url;
    private int points; 
   
    /**
     * Creates a new media item with the given title, URL, and points.
     * 
     * @param title the title of the media item
     * @param url the URL of the media item
     * @param points the points required to play the media item
     * @since 2024
     */

    public Media(String title, String url, int points) {
        this.title = title;
        this.url = url;
        this.points = points;
        validateUrl(url);
    }

    public Media(BufferedReader br) throws IOException {
        this.title = br.readLine();
        this.url = br.readLine();
        this.points = Integer.parseInt(br.readLine());
        validateUrl(url);
    }


    @SuppressWarnings("deprecation")
    private void validateUrl(String url) {
        try {
            new URL(url);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid URL: " + url);
        }
    }

    public int getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return title + " (" + url + ", " + points + " points)";
    }

    public void save(BufferedWriter bw) throws IOException {
        bw.write(title + '\n');
        bw.write(url + '\n');
        bw.write(Integer.toString(points) + '\n');
}
}
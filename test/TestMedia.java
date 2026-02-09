
package test;

import product.Media;

public class TestMedia {
    public static void main(String[] args) {
        Media media = new Media("History of Nepal", "https://assets.cambridge.org/052180/4701/frontmatter/0521804701_frontmatter.pdf", 10);
        String expected = "History of Nepal (https://assets.cambridge.org/052180/4701/frontmatter/0521804701_frontmatter.pdf)";
        String actual = media.toString();

        if (!expected.equals(actual)) {
            System.out.println("FAIL!!! Expected: " + expected + " but got " + actual);
            System.exit(1);
        }

        String[] validUrls = {"https://youtube.com", "file://media/lib/garp.mp4"};
        String[] invalidUrls = {"hello.world", "htt://badurl.com", "flub://badurl.com"};

        for (String validUrl : validUrls) {
            try {
                new Media("Valid Media", validUrl, 10);
            } catch (RuntimeException e) {
                System.out.println("FAIL: Expected valid URL " + validUrl + " but got exception " + e.getMessage());
                System.exit(1);
            }
        }

        for (String invalidUrl : invalidUrls) {
            try {
                new Media("Invalid Media", invalidUrl, 10);
                System.out.println("FAIL: Expected invalid URL " + invalidUrl + " to throw exception");
                System.exit(1);
            } catch (RuntimeException e) {
                
            }
        }
    }
}
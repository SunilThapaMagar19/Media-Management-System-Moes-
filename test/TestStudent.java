package test;

import customer.Student;
import product.Media;

public class TestStudent {
    public static void main(String[] args) {
        int fail = 0;

        Student student = new Student("Sunil Thapa Magar", "1002148333", "sxt8335@mavs.uta.edu",true);
        String expected = "Sunil Thapa Magar (1002148333, sxt8335@mavs.uta.edu, Account #1)";
        String actual = student.toString();

        if (!expected.equals(actual)) {
            System.out.println("FAIL!!! Expected : " + expected + " but got :" + actual);
            fail++;
        }

        try {
            new Student("Sunil Thapa Magar", "1002148333", "sunil.thapamagar@gmail.com", true);
            System.out.println("FAIL: Invalid email");
            fail++;
        } catch (IllegalArgumentException e) {
           
            String expectedString = "Non-UTA email address: sunil.thapamagar@gmail.com";
            if (!e.getMessage().equals(expectedString)) {
                System.out.println("FAIL: Expected message 'Non-UTA email address: sunil.thapamagar@gmail.com' but got :" + e.getMessage());
                fail++;
            }
        } catch (Exception e) {
            System.out.println("FAIL: Expected IllegalArgumentException " + e);
            fail++;
        }

        Media media = new Media("History of Nepal", "https://assets.cambridge.org/052180/4701/frontmatter/0521804701_frontmatter.pdf", 10);
        String expectedMedia = "History of Nepal (https://assets.cambridge.org/052180/4701/frontmatter/0521804701_frontmatter.pdf)";
        String actualMedia = student.requestMedia(media);

        if (!("Playing " + expectedMedia).equals(actualMedia)) {
            System.out.println("FAIL: Expected '" + "Playing " + expectedMedia + "' but got '" + actualMedia + "'");
            fail++;
        }

        System.exit(fail);
    }
}
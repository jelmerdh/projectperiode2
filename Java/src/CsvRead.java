import java.io.*;
import java.util.Scanner;
public class CsvRead {
    public CsvRead() {
    read("mexico.csv");
    }

    public void read(String fileName) {
        //parsing a CSV file into Scanner class constructor
        try {
            Scanner sc = new Scanner(new File(fileName));
            sc.useDelimiter(",");   //sets the delimiter pattern
            while (sc.hasNext())  //returns a boolean value
            {
                System.out.print(sc.next()+ " ");  //find and returns the next complete token from this scanner
            }
            sc.close();  //closes the scanner
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
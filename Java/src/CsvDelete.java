import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.Scanner;


public class CsvDelete {
    public CsvDelete() throws Exception {
        delete(true);

    }

    public void delete(boolean isMexico) throws Exception {
        String i = LocalDate.now().minusDays(28).toString();
        System.out.println(i);
        File inputFile = new File("other.csv");
        File tempFile = new File("other_temp.csv");
        if(isMexico) {
            i = LocalDate.now().minusDays(3).toString();
            inputFile = new File("mexico.csv");
            tempFile = new File("mexico_temp.csv");
        }
        int dateToRemove=Integer.parseInt(i.replace("-", ""));
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String currentLine;
        String currentRemove = "temp";
        int curRem = 0;
        while ((currentLine = reader.readLine()) != null) {
            String[] currParts = currentLine.split(",");

            try{curRem=Integer.parseInt(currParts[1].replace("-", ""));
                if (curRem < dateToRemove) {
                    currentRemove = currentLine;
                    System.out.println(curRem);
                }
            } catch(ArrayIndexOutOfBoundsException e) {}
            // trim newline when comparing with lineToRemove
            String trimmedLine = currentLine.trim();
            if (trimmedLine.equals(currentRemove)) continue;
            writer.write(currentLine + System.getProperty("line.separator"));
        }
        writer.close();
        reader.close();
        if (inputFile.delete()) {
            System.out.println("Deleted the file: " + inputFile.getName());
        } else {
            System.out.println("Failed to delete the file.");
        }
        boolean success = tempFile.renameTo(inputFile);

        if (!success) {
            System.out.println("suck");
        }
    }

}


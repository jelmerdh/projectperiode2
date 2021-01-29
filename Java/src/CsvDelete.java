import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;



public class CsvDelete {
    public CsvDelete() throws Exception {
        delete(true);

    }

    public void delete(boolean isMexico) throws Exception {
        String dateToRemove = LocalDate.now().minusDays(28).toString();
        File inputFile = new File("other.csv");
        File tempFile = new File("other_temp.csv");
        if(isMexico) {
            dateToRemove = LocalDate.now().minusDays(3).toString();
            inputFile = new File("mexico.csv");
            tempFile = new File("mexico_temp.csv");
        }
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String lineToRemove = dateToRemove;
        String currentLine;
        String currentRemove = "temp";
        while ((currentLine = reader.readLine()) != null) {
            if (currentLine.indexOf(lineToRemove) >= 0) {
                currentRemove = currentLine;
                System.out.println("hier");
            }
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


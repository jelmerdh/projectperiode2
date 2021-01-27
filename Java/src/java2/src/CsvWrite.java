import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
public class CsvWrite {

    public CsvWrite(){
        write();
    }
    public void write() {
        try (PrintWriter writer = new PrintWriter("test.csv")) {

            StringBuilder sb = new StringBuilder();

            sb.append("stn, date, time, temp, dewp, stp,slp, visib, wdsp, sndp, frshtt, cldc,  wnddir");
            sb.append('\n');
            sb.append("999999, 30-11-1999, 17:51, 80, 8, 2, 3, 3, 3, 3, 3, 3, 3");




            writer.write(sb.toString());

            System.out.println("done!");

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

}

import java.io.*;
import java.util.Scanner;

public class CsvWrite {

    public CsvWrite(){

        write(999999, "2021-01-3", "16:01:14", (float)24, (float)5, (float)14,(float)14,(float)35, (float)14,(float)4,"n", (float)15, 3);
    }
    public void write(int stn, String date, String time, Float temp, Float dewp, Float stp, Float slp, Float visib, Float  wdsp, Float sndp, String frshtt, Float cldc, int wnddir) {
        StringBuilder sb = new StringBuilder();
        String fileName = "empty.csv";
        if(stn > 760012 && stn < 769044) {
            fileName = "mexico.csv";
        } else {
            fileName = "other.csv";
        }
        File file = new File(fileName);
        try (FileWriter writer = new FileWriter(fileName, true)) {
            if(file.length() == 0) {
                sb.append("stn date time temp dewp stp slp visib wdsp sndp frshtt cldc  wnddir,\n");
            }
            sb.append(stn+","+date+","+time+","+temp+","+dewp+","+stp+","+slp+","+visib+","+wdsp+","+sndp+","+frshtt+","+cldc+","+wnddir+"\n");
            writer.write(sb.toString());
        } catch (java.io.IOException e) {
            System.out.println(e.getMessage());
        }
    }

}

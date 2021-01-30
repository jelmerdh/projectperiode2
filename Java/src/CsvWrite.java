import java.io.*;
import java.util.Scanner;

public class CsvWrite {

    public CsvWrite(){
    }
    public void writeMexico(int stn, String date, String time, Float temp, Float dewp, Float stp, Float slp, Float visib, Float  wdsp, Float sndp, String frshtt, Float cldc, int wnddir) {
        StringBuilder sb = new StringBuilder();
           String fileName = "mexico.csv";

        File file = new File(fileName);
        try (FileWriter writer = new FileWriter(fileName, true)) {
            if(file.length() == 0) {
                sb.append("stn, date, time, temp, dewp, stp, slp, visib, wdsp, sndp, frshtt, cldc,  wnddir\n");
            }
            sb.append(stn+","+date+","+time+","+temp+","+dewp+","+stp+","+slp+","+visib+","+wdsp+","+sndp+","+frshtt+","+cldc+","+wnddir+"\n");
            writer.write(sb.toString());
        } catch (java.io.IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public void writeOther(int stn, String date, String time, Float temp) {
        StringBuilder sb = new StringBuilder();
           String fileName = "other.csv";
        File file = new File(fileName);
        try (FileWriter writer = new FileWriter(fileName, true)) {
            if(file.length() == 0) {
                sb.append("stn, date, time, temp\n");
            }
            sb.append(stn+","+date+","+time+","+temp+"\n");
            writer.write(sb.toString());
        } catch (java.io.IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

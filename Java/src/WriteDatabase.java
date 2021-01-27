import java.sql.PreparedStatement;
import java.sql.SQLException;


public class WriteDatabase {

    // AANMAKEN LINKEDLIST

    private Database db;
    private Metingen meting;

    public WriteDatabase(){

        db = new Database("jdbc:mysql://localhost:3306/unwdmi", "root", "");
        Insert(999999, "2021-01-3", "16:01:14", (float)24, (float)5, (float)14,(float)14,(float)35, (float)14,(float)4,"n", (float)15, 3);
        meting = new Metingen();
    }



    public void Insert(int stn, String date, String time, Float temp, Float dewp, Float stp, Float slp, Float visib, Float  wdsp, Float sndp, String frshtt, Float cldc, int wnddir){

        try {

            System.out.println("Hij begint aan test!");

            String query = "INSERT INTO weatherdata (stn, date, time, temp, dewp, stp,slp, visib, wdsp, sndp, frshtt, cldc,  wnddir)"
                    + "VALUES (?, ?, ?, ?, ?,? , ?, ? ,?, ?, ?, ?, ?)";
            PreparedStatement pstm = db.getMyConn().prepareStatement(query);
            pstm.setInt(1, stn);
            pstm.setString(2, date);
            pstm.setString(3,time);
            pstm.setFloat(4, temp);
            pstm.setFloat(5, dewp);
            pstm.setFloat(6, stp);
            pstm.setFloat(7, slp);
            pstm.setFloat(8,visib);
            pstm.setFloat(9,wdsp);
            pstm.setFloat(10,sndp);
            pstm.setString(11,frshtt);
            pstm.setFloat(12,cldc);
            pstm.setInt(13,wnddir);
            pstm.execute();
            System.out.println("Query is verstuurd!");
            pstm.close();

        } catch (SQLException e){
            e.printStackTrace();
        }

    }
}

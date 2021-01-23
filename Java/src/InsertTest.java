import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.sql.Date;

public class InsertTest {

    // AANMAKEN LINKEDLIST

    private Database db;


    public InsertTest(){

        db = new Database("jdbc:mysql://localhost:3306/unwdmi", "root", "");
        test();
    }

    public Date getDate(){
        long milis = System.currentTimeMillis();
        java.sql.Date date= new java.sql.Date(milis);
        return date;
    }



    public void test(){
        try {


            System.out.println("Hij begint aan test!");

            String query = "INSERT INTO weatherdata (stn, date, time,temp, dewp, stp,slp, visib, wdsp,sndp, frshtt, cldc,  wnddir)"
                    + "VALUES (?, ?, ?, ?, ?,? , ?, ? ,?, ?, ?, ?, ?)";
            PreparedStatement pstm = db.getMyConn().prepareStatement(query);
            pstm.setInt(1, 999999);
            pstm.setDate(2, getDate());
            pstm.setTime(3,);
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

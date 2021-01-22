import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;

public class InsertTest {

    // AANMAKEN LINKEDLIST
    private LinkedList<>
    private Database db;


    public InsertTest(){

        db = new Database("jdbc:mysql://localhost:3306/unwdmi", "root", "");
        test();
    }

    public void test(){
        try {
            System.out.println("Hij begint aan test!");

            String query = "INSERT INTO weatherdata (stn, name, country, latitude, longitude, elevation)"
                    + "VALUES (?, ?, ?, ?, ?,? )";
            PreparedStatement pstm = db.getMyConn().prepareStatement(query);
            pstm.setInt(1, 999999);
            pstm.setString(2, "JASPERLAND");
            pstm.setString(3,"JELMERLAND");
            pstm.setDouble(4, 50.7);
            pstm.setDouble(5, 6.9);
            pstm.setDouble(6, 15.0);
            pstm.execute();
            System.out.println("Query is verstuurd!");
            pstm.close();

        } catch (SQLException e){
            e.printStackTrace();
        }

    }
}

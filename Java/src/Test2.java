import java.sql.ResultSet;
import java.sql.SQLException;

public class Test2 {


    public Database db;

    public Test2() {
        System.out.println("test2 werkt");
        db = new Database("jdbc:mysql://localhost:3306/unwdmi", "root", "");
        selectTest();
    }

    public void selectTest(){
        try{
        ResultSet rs = db.select("SELECT country FROM stations WHERE latitude = 50.7");
        while (rs.next()) {
            String country = rs.getString("country");
            System.out.println(country);
        }

        }catch (SQLException e){
            e.printStackTrace();
        }

    }
    public Database getDb(){
        return db;
    }
}

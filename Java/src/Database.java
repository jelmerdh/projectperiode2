import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class Database {


    private Connection myConn;



    public Database(String host, String User, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            myConn = DriverManager.getConnection(host, User, password);
            System.out.println("Connectie werkt");
        } catch(ClassNotFoundException | SQLException ex){
            ex.printStackTrace();
        }
    }

    public ResultSet select(String query){
        try{
            System.out.println("Begin select statement werkt");
            Statement stm = myConn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            return rs;

        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public Connection getMyConn(){
        return myConn;
    }
}

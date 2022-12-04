import model.Yolcu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class AbstractDatabaseLayer {
    static Connection con = null;
    static final String url = "jdbc:mysql://localhost:8080/otobussistem";

    void connect(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                    url,"root","1327emreylr");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}

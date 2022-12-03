import model.Yolcu;
import java.sql.*;

public class DatabaseLayer extends AbstractDatabaseLayer{
    void insertYolcu(Yolcu yolcu){
        if(con==null) connect();
        try {
            String que = "insert into Yolcu(isim, soyisim,telefon) values (?,?,?)";
            PreparedStatement add=con.prepareStatement(que);
            add.setString(1,yolcu.getIsim().toUpperCase());
            add.setString(2,yolcu.getSoyisim().toUpperCase());
            add.setString(3,yolcu.getTelefonNumarasi());
            add.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

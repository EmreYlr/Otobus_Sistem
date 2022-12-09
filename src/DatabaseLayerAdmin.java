import model.DatabaseCommon;
import model.Kullanci;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseLayerAdmin extends AbstractDatabaseLayer implements DatabaseCommon {
    @Override
    public void insertAdmin(Kullanci kullanci){
        if(con==null) connect();
        try {
            String que = "insert into Admin(isim, soyisim,kullaniciAdi,sifre,cinsiyet,statu) values (?,?,?,?,?,?)";
            PreparedStatement add=con.prepareStatement(que);
            add.setString(1,kullanci.getIsim().toUpperCase());
            add.setString(2,kullanci.getSoyisim().toUpperCase());
            add.setString(3,kullanci.getKullaniciAdi());
            add.setString(4,kullanci.getSifre());
            add.setString(5,String.valueOf(kullanci.cinsiyet));
            add.setString(6,String.valueOf(kullanci.statu));
            add.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public boolean checkLogin(String kullaniciAdi, String sifre){
        if(con==null) connect();
        try {
            PreparedStatement add = con.prepareStatement("select kullaniciAdi,sifre from admin where kullaniciAdi = ? ");
            add.setString(1,kullaniciAdi);
            ResultSet rs = add.executeQuery();
            if(rs.next()){
                if(rs.getString(1).equals(kullaniciAdi) && rs.getString(2).equals(sifre)) return true;
            }else{
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}

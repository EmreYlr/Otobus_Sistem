import model.*;

import javax.swing.*;
import java.sql.*;
import java.util.HashMap;
import java.util.List;

public class DatabaseLayerUser extends AbstractDatabaseLayer{

    void insertYolcu(Yolcu yolcu){
        if(con==null) connect();
        try {
            String que = "insert into Yolcu(isim,soyisim,telefon,cinsiyet,tc) values (?,?,?,?,?)";
            PreparedStatement add=con.prepareStatement(que);
            add.setString(1,yolcu.getIsim().toUpperCase());
            add.setString(2,yolcu.getSoyisim().toUpperCase());
            add.setString(3,yolcu.getTelefon());
            add.setString(4,String.valueOf(yolcu.cinsiyet));
            add.setString(5,yolcu.getTc());
            add.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    int getYolcuId(Yolcu yolcu){
        if(con == null) connect();
        try {
            PreparedStatement add = con.prepareStatement("select id from yolcu where tc = ?");
            add.setString(1,yolcu.getTc());
            ResultSet rs = add.executeQuery();
            while(rs.next()){
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    void getSeferList(List<Sefer> list){
        if(con == null) connect();
        try {
            PreparedStatement add = con.prepareStatement("select id, plakaKoduKalkis,plakaKoduVaris, kalkisTarih,varisTarih,kaptan from sefer");
            ResultSet rs = add.executeQuery();
            while(rs.next()){
                list.add(new Sefer(rs.getInt(1),0, rs.getInt(2),rs.getInt(3), rs.getString(4), rs.getString(5),rs.getString(6)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    void getOtobusList(List<Otobus> list){
        if(con == null) connect();
        try {
            PreparedStatement add = con.prepareStatement("select plaka,kapasite from otobus");
            ResultSet rs = add.executeQuery();
            while(rs.next()){
                list.add(new Otobus(rs.getString(1),rs.getInt(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    void getYolcu(List<Yolcu> list){
        if(con == null) connect();
        try {
            PreparedStatement add = con.prepareStatement("select isim, soyisim, telefon, cinsiyet,tc from yolcu");
            ResultSet rs = add.executeQuery();
            while(rs.next()){
                if(rs.getString(4).equals("erkek")){
                    list.add(new Yolcu(rs.getString(1), rs.getString(2),rs.getString(3),null,null,rs.getString(5),Kullanci.Cinsiyet.erkek, null));
                }else{
                    list.add(new Yolcu(rs.getString(1), rs.getString(2),rs.getString(3),null,null,rs.getString(5),Kullanci.Cinsiyet.kadin, null));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    void getKoltuk(List<Koltuk> list){
        if(con == null) connect();
        try {
            PreparedStatement add = con.prepareStatement("select yolcu_id, sefer_id, koltuk_no from koltuk;");
            ResultSet rs = add.executeQuery();
            while(rs.next()){
                list.add(new Koltuk(rs.getInt(1), rs.getInt(2), rs.getInt(3)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void updateYolcu(String[] temp, String[] tempUpdate){
        if(con==null) connect();
        try {
            PreparedStatement add=con.prepareStatement("update yolcu set isim = ?, soyisim = ?, telefon = ?, cinsiyet = ?, tc = ? where tc = ?");
            add.setString(1,tempUpdate[1]);
            add.setString(2,tempUpdate[2]);
            add.setString(3,tempUpdate[3]);
            add.setString(4,tempUpdate[4]);
            add.setString(5,tempUpdate[0]);
            add.setString(6,temp[0]);
            PreparedStatement add2=con.prepareStatement("update koltuk set sefer_id = ?, koltuk_no = ? where sefer_id = ? and koltuk_no = ?");
            add2.setString(1,tempUpdate[5]);
            add2.setString(2,tempUpdate[6]);
            add2.setString(3,temp[5]);
            add2.setString(4,temp[6]);
            add2.executeUpdate();
            add.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    void updateSefer(String[] temp, String[] tempUpdate){
        if(con==null) connect();
        try {
            PreparedStatement add=con.prepareStatement("update sefer set id = ?, plakaKoduKalkis = ?, plakaKoduVaris = ?, kalkisTarih = ?, varisTarih = ?, kaptan = ? where id = ?");
            add.setString(1,tempUpdate[0]);
            if(tempUpdate[1].equals("İstanbul")){
                add.setInt(2,34);
            }else{
                add.setInt(2,63);
            }
            if(tempUpdate[2].equals("Urfa")){
                add.setInt(3,63);
            }else{
                add.setInt(3,34);
            }

            add.setString(4,tempUpdate[3]);
            add.setString(5,tempUpdate[4]);
            add.setString(6,tempUpdate[5]);
            add.setString(7,temp[0]);
            PreparedStatement add2=con.prepareStatement("update otobus set plaka = ?, kapasite = ? where plaka = ?");
            add2.setString(1,tempUpdate[6]);
            add2.setString(2,tempUpdate[7]);
            add2.setString(3,temp[6]);
            add2.executeUpdate();
            add.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void yolcuSil(String[] temp){
        if(con==null) connect();
        try {
            PreparedStatement add=con.prepareStatement("delete from yolcu where tc = ?");
            add.setString(1,temp[0]);
            PreparedStatement add2=con.prepareStatement("delete from koltuk where sefer_id = ? and koltuk_no = ?");
            add2.setString(1,temp[1]);
            add2.setString(2,temp[2]);
            add2.executeUpdate();
            add.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    void seferSil(String[] temp){
        if(con==null) connect();
        try {
            //delete from yolcu where id in (select yolcu_id from koltuk WHERE sefer_id = 24)
            PreparedStatement add3=con.prepareStatement("delete from yolcu where id in (select yolcu_id from koltuk WHERE sefer_id = ?)");
            add3.setString(1,temp[0]);
            PreparedStatement add=con.prepareStatement("delete from sefer where id = ?");
            add.setString(1,temp[0]);
            PreparedStatement add2=con.prepareStatement("delete from otobus where plaka = ?");
            add2.setString(1,temp[1]);
            add3.executeUpdate();
            add.executeUpdate();
            add2.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    void instertSefer(Sefer s){
        if(con == null) connect();
        try {
            String que = "insert into sefer(otobus_id, plakaKoduKalkis, plakaKoduVaris, kalkisTarih, varisTarih, kaptan) values (?,?,?,?,?,?)";
            PreparedStatement add=con.prepareStatement(que);
            add.setInt(1,s.getOtobus_id());
            add.setInt(2,s.getPlakaKoduKalkis());
            add.setInt(3,s.getPlakaKoduVaris());
            add.setString(4, s.getKalkisTarihi());
            add.setString(5, s.getVarisTarihi());
            add.setString(6,s.getKaptan());
            add.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    void instertOtobus(Otobus o){
        if(con == null) connect();
        try {
            String que = "insert into otobus(plaka, kapasite) values (?,?)";
            PreparedStatement add=con.prepareStatement(que);
            add.setString(1,o.getPlaka());
            add.setInt(2,o.getKapasite());
            add.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    int getOtobusId(Otobus o){
        if(con == null) connect();
        try {
            String que = "select id from otobus where plaka = ?";
            PreparedStatement add=con.prepareStatement(que);
            add.setString(1,o.getPlaka());
            ResultSet rs = add.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    int getSeferId(int o){
        if(con == null) connect();
        try {
            String que = "select id from sefer where otobus_id = ?";
            PreparedStatement add=con.prepareStatement(que);
            add.setInt(1,o);
            ResultSet rs = add.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    void getSefer(String s1, String s2,Sehir s,JList list){
        if(con==null) connect();
        DefaultListModel model = new DefaultListModel<>();
        try {
            PreparedStatement add = con.prepareStatement("select kalkisTarih, varisTarih, id from sefer where plakaKoduKalkis = ?");
            add.setInt(1,s.getPlakaKodu());
            ResultSet rs = add.executeQuery();
            while (rs.next()){
                model.addElement(rs.getInt(3)+" "+ s1 +" "+ rs.getString(1) +"--->"+ s2 + " " + rs.getString(2));
                list.setModel(model);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    HashMap<Integer, Integer> getOtobus(int seferId){
        if(con == null) connect();
        HashMap<Integer,Integer> temp = new HashMap<Integer,Integer>();
        try {
            PreparedStatement add = con.prepareStatement("select yolcu_id, sefer_id, koltuk_no from koltuk where sefer_id = ?");
            add.setInt(1,seferId);
            ResultSet rs = add.executeQuery();
            while(rs.next()){
                temp.put(rs.getInt(3), rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return temp;
    }

    HashMap<Integer,String> getCinsiyet(HashMap<Integer,Integer> h){
        if(con == null) connect();
        HashMap<Integer,String> temp = new HashMap<Integer,String>();
        try {
            PreparedStatement add = con.prepareStatement("select id, cinsiyet from yolcu");
            ResultSet rs = add.executeQuery();
            while(rs.next()){
                for (Integer i : h.keySet()) {
                    if(rs.getInt(1) == h.get(i)){
                        temp.put(i,rs.getString(2));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return temp;
    }


    void insertKoltuk(Koltuk k) {
        if (con == null) connect();
        try {
            String que = "insert into koltuk (sefer_id, yolcu_id, koltuk_no) values (?, ?, ?)";
            PreparedStatement add = con.prepareStatement(que);
            add.setInt(1,k.getSefer_id());
            add.setInt(2,k.getYolcu_id());
            add.setInt(3,k.getKoltukNo());
            add.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

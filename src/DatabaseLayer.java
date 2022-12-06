import model.*;

import javax.swing.*;
import java.sql.*;
import java.util.HashMap;

public class DatabaseLayer extends AbstractDatabaseLayer{
    void insertAdmin(Kullanci kullanci){
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

    boolean checkLogin(String kullaniciAdi, String sifre){
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

    void instertSefer(Sefer s){
        if(con == null) connect();
        try {
            String que = "insert into sefer(id, otobus_id, plakaKoduKalkis, plakaKoduVaris, kalkisTarih, varisTarih, kaptan) values (?,?,?,?,?,?,?)";
            PreparedStatement add=con.prepareStatement(que);
            add.setInt(1,s.getId());
            add.setInt(2,s.getOtobus_id());
            add.setInt(3,s.getPlakaKoduKalkis());
            add.setInt(4,s.getPlakaKoduVaris());
            add.setString(5, s.getKalkisTarihi());
            add.setString(6, s.getVarisTarihi());
            add.setString(7,s.getKaptan());
            add.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void getSefer(String s1, String s2,Sehir sehir, JList list){
        if(con==null) connect();
        DefaultListModel model = new DefaultListModel<>();
        try {
            PreparedStatement add = con.prepareStatement("select kalkisTarih, varisTarih, otobus_id from sefer where plakaKoduKalkis = ?");
            add.setInt(1,sehir.getPlakaKodu());
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

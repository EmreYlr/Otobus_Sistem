package model;

public class Admin extends Kullanci{
    public Admin(String isim, String soyisim,String kullaniciAdi,String sifre,Cinsiyet cinsiyet) {
        super(isim, soyisim, kullaniciAdi,"admin",cinsiyet);
        statu = Statu.admin;
    }
}

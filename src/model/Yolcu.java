package model;

public class Yolcu extends Kullanci {

    public Yolcu(String isim, String soyisim,String kullaniciAdi, String sifre,Cinsiyet cinsiyet) {
        super(isim, soyisim,kullaniciAdi, sifre,cinsiyet);
        statu = Statu.yolcu;
    }
}

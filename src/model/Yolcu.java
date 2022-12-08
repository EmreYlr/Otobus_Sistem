package model;

public class Yolcu extends Kullanci {
    String telefon;
    String tc;


    public String getTc() {
        return tc;
    }

    public void setTc(String tc) {
        this.tc = tc;
    }

    public Yolcu(String isim, String soyisim, String telefon, String kullaniciAdi, String sifre, String tc, Cinsiyet cinsiyet, Statu statu) {
        super(isim, soyisim,kullaniciAdi, sifre,cinsiyet);
        this.statu = Statu.yolcu;
        this.telefon = telefon;
        this.tc = tc;
    }
    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }
}

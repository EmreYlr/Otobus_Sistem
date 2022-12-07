package model;

public class Kullanci {
    public enum Cinsiyet{erkek,kadin};
    public enum Statu{admin,yolcu};
    public Cinsiyet cinsiyet;
    public Statu statu;
    protected int id;

    public Cinsiyet getCinsiyet() {
        return cinsiyet;
    }

    public void setCinsiyet(Cinsiyet cinsiyet) {
        this.cinsiyet = cinsiyet;
    }

    protected String isim;
    protected String soyisim;
    protected String sifre;
    protected String kullaniciAdi;

    public Kullanci(String isim, String soyisim, String kullaniciAdi, String sifre, Cinsiyet cinsiyet) {
        this.isim = isim;
        this.soyisim = soyisim;
        this.kullaniciAdi = kullaniciAdi;
        this.sifre = sifre;
        this.cinsiyet = cinsiyet;
    }
    public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    public void setKullaniciAdi(String kullaniciAdi) {
        this.kullaniciAdi = kullaniciAdi;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public String getSoyisim() {
        return soyisim;
    }

    public void setSoyisim(String soyisim) {
        this.soyisim = soyisim;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }


}

package model;

public class Kullanci {
    protected int id;
    protected String isim;
    protected String soyisim;
    protected String sifre;

    public Kullanci(String isim, String soyisim, String sifre) {
        this.isim = isim;
        this.soyisim = soyisim;
        this.sifre = sifre;
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

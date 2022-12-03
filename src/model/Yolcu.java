package model;

public class Yolcu extends Kullanci {

    private String telefonNumarasi;

    public Yolcu(String isim, String soyisim, String sifre, String telefonNumarasi) {
        super(isim, soyisim, sifre);
        this.telefonNumarasi = telefonNumarasi;
    }


    public String getTelefonNumarasi() {
        return telefonNumarasi;
    }

    public void setTelefonNumarasi(String telefonNumarasi) {
        this.telefonNumarasi = telefonNumarasi;
    }
}

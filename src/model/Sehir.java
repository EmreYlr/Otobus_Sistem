package model;
public class Sehir {
    private int plakaKodu;
    private String isim;

    public int getPlakaKodu() {
        return plakaKodu;
    }

    public void setPlakaKodu(int plakaKodu) {
        this.plakaKodu = plakaKodu;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public Sehir(int plakaKodu, String isim) {
        this.plakaKodu = plakaKodu;
        this.isim = isim;
    }
}

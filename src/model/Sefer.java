package model;

import java.util.Date;

public class Sefer {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int otobus_id;
    private int plakaKoduKalkis;
    private int plakaKoduVaris;
    private String kalkisTarihi;
    private String varisTarihi;
    private String kaptan;
    public Sefer(){

    }

    public Sefer(int id, int otobus_id, int plakaKoduKalkis, int plakaKoduVaris, String kalkisTarihi, String varisTarihi, String kaptan) {
        this.id = id;
        this.otobus_id = otobus_id;
        this.plakaKoduKalkis = plakaKoduKalkis;
        this.plakaKoduVaris = plakaKoduVaris;
        this.kalkisTarihi = kalkisTarihi;
        this.varisTarihi = varisTarihi;
        this.kaptan = kaptan;
    }

    public int getOtobus_id() {
        return otobus_id;
    }

    public void setOtobus_id(int otobus_id) {
        this.otobus_id = otobus_id;
    }

    public int getPlakaKoduKalkis() {
        return plakaKoduKalkis;
    }

    public void setPlakaKoduKalkis(int plakaKoduKalkis) {
        this.plakaKoduKalkis = plakaKoduKalkis;
    }

    public int getPlakaKoduVaris() {
        return plakaKoduVaris;
    }

    public void setPlakaKoduVaris(int plakaKoduVaris) {
        this.plakaKoduVaris = plakaKoduVaris;
    }



    public String getKaptan() {
        return kaptan;
    }

    public void setKaptan(String kaptan) {
        this.kaptan = kaptan;
    }

    public String getKalkisTarihi() {
        return kalkisTarihi;
    }

    public void setKalkisTarihi(String kalkisTarihi) {
        this.kalkisTarihi = kalkisTarihi;
    }

    public String getVarisTarihi() {
        return varisTarihi;
    }

    public void setVarisTarihi(String varisTarihi) {
        this.varisTarihi = varisTarihi;
    }


}

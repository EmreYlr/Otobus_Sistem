package model;
public class Otobus {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String plaka;
    private int kapasite;

    public  Otobus(){

    }
    public Otobus(String plaka, int kapasite) {
        this.plaka = plaka;
        this.kapasite = kapasite;
    }

    public String getPlaka() {
        return plaka;
    }

    public void setPlaka(String plaka) {
        this.plaka = plaka;
    }

    public int getKapasite() {
        return kapasite;
    }

    public void setKapasite(int kapasite) {
        this.kapasite = kapasite;
    }
}

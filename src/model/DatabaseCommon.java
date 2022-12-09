package model;

public interface DatabaseCommon {
    void insertAdmin(Kullanci kullanici);
    boolean checkLogin(String kullaniciAdi, String sifre);
}

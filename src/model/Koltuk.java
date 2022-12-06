package model;
public class Koltuk {
    int id;
    int yolcu_id;
    int sefer_id;
    int koltukNo;

    public int getKoltukNo() {
        return koltukNo;
    }

    public void setKoltukNo(int koltukNo) {
        this.koltukNo = koltukNo;
    }

    public Koltuk(int yolcu_id, int sefer_id, int koltukNo) {
        this.yolcu_id = yolcu_id;
        this.sefer_id = sefer_id;
        this.koltukNo = koltukNo;
    }

    public int getYolcu_id() {
        return yolcu_id;
    }
    public void setYolcu_id(int yolcu_id) {
        this.yolcu_id = yolcu_id;
    }
    public int getSefer_id() {
        return sefer_id;
    }
    public void setSefer_id(int sefer_id) {
        this.sefer_id = sefer_id;
    }


}

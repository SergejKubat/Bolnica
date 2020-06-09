package model;

public class OdeljenjeTip {
    
    private int id;
    private int naziv;

    public OdeljenjeTip() {
    }

    public OdeljenjeTip(int id, int naziv) {
        this.id = id;
        this.naziv = naziv;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNaziv() {
        return naziv;
    }

    public void setNaziv(int naziv) {
        this.naziv = naziv;
    }
    
}

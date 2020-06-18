package model;

import java.util.List;

public class DoktorTip {
    
    private int id;
    private String naziv;
    private List<Doktor> doktori;

    public DoktorTip() {
    }

    public DoktorTip(int id, String naziv) {
        this.id = id;
        this.naziv = naziv;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public List<Doktor> getDoktori() {
        return doktori;
    }

    public void setDoktori(List<Doktor> doktori) {
        this.doktori = doktori;
    }
    
}

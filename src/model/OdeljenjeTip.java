package model;

import java.util.List;

public class OdeljenjeTip {
    
    private int id;
    private String naziv;
    private List<Odeljenje> odeljenja;

    public OdeljenjeTip() {
    }

    public OdeljenjeTip(int id, String naziv) {
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

    public List<Odeljenje> getOdeljenja() {
        return odeljenja;
    }

    public void setOdeljenja(List<Odeljenje> odeljenja) {
        this.odeljenja = odeljenja;
    }
    
}

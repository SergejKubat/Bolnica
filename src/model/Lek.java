package model;

import java.util.List;

public class Lek {
    
    private int id;
    private Kompanija kompanijaId;
    private String naziv;
    private String tip;
    private List<Recept> recepti;

    public Lek() {
    }

    public Lek(int id, Kompanija kompanijaId, String naziv, String tip) {
        this.id = id;
        this.kompanijaId = kompanijaId;
        this.naziv = naziv;
        this.tip = tip;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Kompanija getKompanijaId() {
        return kompanijaId;
    }

    public void setKompanijaId(Kompanija kompanijaId) {
        this.kompanijaId = kompanijaId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public List<Recept> getRecepti() {
        return recepti;
    }

    public void setRecepti(List<Recept> recepti) {
        this.recepti = recepti;
    }

    @Override
    public String toString() {
        return naziv;
    }
    
}

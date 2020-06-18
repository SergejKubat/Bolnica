package model;

import java.util.List;

public class BolnicaTip {
    
    private int id;
    private String naziv;
    private List<Bolnica> bolnice;

    public BolnicaTip() {
    }

    public BolnicaTip(int id, String naziv) {
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

    public List<Bolnica> getBolnice() {
        return bolnice;
    }

    public void setBolnice(List<Bolnica> bolnice) {
        this.bolnice = bolnice;
    }
    
}

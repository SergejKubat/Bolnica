package com.bolnica.model;

public class DoktorTip {
    
    private int id;
    private int naziv;

    public DoktorTip() {
    }

    public DoktorTip(int id, int naziv) {
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

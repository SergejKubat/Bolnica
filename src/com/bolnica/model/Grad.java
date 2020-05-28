package com.bolnica.model;

public class Grad {
    
    private int id;
    private String ime;

    public Grad() {
    }

    public Grad(int id, String ime) {
        this.id = id;
        this.ime = ime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }
    
}

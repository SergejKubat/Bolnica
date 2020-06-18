package model;

import java.util.List;

public class Grad {
    
    private int id;
    private String ime;
    private List<Bolnica> bolnice;

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

    public List<Bolnica> getBolnice() {
        return bolnice;
    }

    public void setBolnice(List<Bolnica> bolnice) {
        this.bolnice = bolnice;
    }
    
}

package model;

public class Odeljenje {
    
    private int id;
    private int bolnicaId;
    private int tipId;
    private String naziv;
    private String opis;

    public Odeljenje() {
    }

    public Odeljenje(int id, int bolnicaId, int tipId, String naziv, String opis) {
        this.id = id;
        this.bolnicaId = bolnicaId;
        this.tipId = tipId;
        this.naziv = naziv;
        this.opis = opis;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBolnicaId() {
        return bolnicaId;
    }

    public void setBolnicaId(int bolnicaId) {
        this.bolnicaId = bolnicaId;
    }

    public int getTipId() {
        return tipId;
    }

    public void setTipId(int tipId) {
        this.tipId = tipId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
    
}

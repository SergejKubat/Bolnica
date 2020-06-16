package model;

public class Odeljenje {
    
    private int id;
    private Bolnica bolnicaId;
    private OdeljenjeTip tipId;
    private String naziv;
    private String opis;

    public Odeljenje() {
    }

    public Odeljenje(int id, Bolnica bolnicaId, OdeljenjeTip tipId, String naziv, String opis) {
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

    public Bolnica getBolnicaId() {
        return bolnicaId;
    }

    public void setBolnicaId(Bolnica bolnicaId) {
        this.bolnicaId = bolnicaId;
    }

    public OdeljenjeTip getTipId() {
        return tipId;
    }

    public void setTipId(OdeljenjeTip tipId) {
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

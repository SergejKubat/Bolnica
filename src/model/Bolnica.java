package model;

public class Bolnica {
    
    private int id;
    private BolnicaTip bolnicaTipId;
    private Grad gradId;
    private String ime;
    private String adresa;
    private String opis;

    public Bolnica() {
    }

    public Bolnica(int id, BolnicaTip bolnicaTipId, Grad gradId, String ime, String adresa, String opis) {
        this.id = id;
        this.bolnicaTipId = bolnicaTipId;
        this.gradId = gradId;
        this.ime = ime;
        this.adresa = adresa;
        this.opis = opis;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BolnicaTip getBolnicaTipId() {
        return bolnicaTipId;
    }

    public void setBolnicaTipId(BolnicaTip bolnicaTipId) {
        this.bolnicaTipId = bolnicaTipId;
    }

    public Grad getGradId() {
        return gradId;
    }

    public void setGradId(Grad gradId) {
        this.gradId = gradId;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
    
}

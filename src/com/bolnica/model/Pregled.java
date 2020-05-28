package com.bolnica.model;

public class Pregled {
    
    private int id;
    private int pacijentId;
    private int doktorOd;
    private int dijagnozaId;
    private String datum;
    private String vreme;

    public Pregled() {
    }

    public Pregled(int id, int pacijentId, int doktorOd, int dijagnozaId, String datum, String vreme) {
        this.id = id;
        this.pacijentId = pacijentId;
        this.doktorOd = doktorOd;
        this.dijagnozaId = dijagnozaId;
        this.datum = datum;
        this.vreme = vreme;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPacijentId() {
        return pacijentId;
    }

    public void setPacijentId(int pacijentId) {
        this.pacijentId = pacijentId;
    }

    public int getDoktorOd() {
        return doktorOd;
    }

    public void setDoktorOd(int doktorOd) {
        this.doktorOd = doktorOd;
    }

    public int getDijagnozaId() {
        return dijagnozaId;
    }

    public void setDijagnozaId(int dijagnozaId) {
        this.dijagnozaId = dijagnozaId;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getVreme() {
        return vreme;
    }

    public void setVreme(String vreme) {
        this.vreme = vreme;
    }
    
}

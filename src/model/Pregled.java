package model;

public class Pregled {
    
    private int id;
    private Pacijent pacijentId;
    private Doktor doktorId;
    private Dijagnoza dijagnozaId;
    private String datum;
    private String vreme;

    public Pregled() {
    }

    public Pregled(int id, Pacijent pacijentId, Doktor doktorId, Dijagnoza dijagnozaId, String datum, String vreme) {
        this.id = id;
        this.pacijentId = pacijentId;
        this.doktorId = doktorId;
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

    public Pacijent getPacijentId() {
        return pacijentId;
    }

    public void setPacijentId(Pacijent pacijentId) {
        this.pacijentId = pacijentId;
    }

    public Doktor getDoktorId() {
        return doktorId;
    }

    public void setDoktorOd(Doktor doktorId) {
        this.doktorId = doktorId;
    }

    public Dijagnoza getDijagnozaId() {
        return dijagnozaId;
    }

    public void setDijagnozaId(Dijagnoza dijagnozaId) {
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

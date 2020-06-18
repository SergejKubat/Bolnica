package model;

import java.util.List;

public class Pregled {

    private int id;
    private Pacijent pacijentId;
    private Doktor doktorId;
    private String datum;
    private String vreme;
    private boolean odrzan;
    private List<Dijagnoza> dijagnoza;

    public Pregled() {
    }

    public Pregled(int id, Pacijent pacijentId, Doktor doktorId, String datum, String vreme, boolean odrzan) {
        this.id = id;
        this.pacijentId = pacijentId;
        this.doktorId = doktorId;
        this.datum = datum;
        this.vreme = vreme;
        this.odrzan = odrzan;
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

    public boolean isOdrzan() {
        return odrzan;
    }

    public void setOdrzan(boolean odrzan) {
        this.odrzan = odrzan;
    }

    public List<Dijagnoza> getDijagnoza() {
        return dijagnoza;
    }

    public void setDijagnoza(List<Dijagnoza> dijagnoza) {
        this.dijagnoza = dijagnoza;
    }

}

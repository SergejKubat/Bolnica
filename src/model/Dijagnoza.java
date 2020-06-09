package model;

public class Dijagnoza {
    
    private int id;
    private int bolestId;
    private int pregledId;
    private String opis;

    public Dijagnoza() {
    }

    public Dijagnoza(int id, int bolestId, int pregledId, String opis) {
        this.id = id;
        this.bolestId = bolestId;
        this.pregledId = pregledId;
        this.opis = opis;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBolestId() {
        return bolestId;
    }

    public void setBolestId(int bolestId) {
        this.bolestId = bolestId;
    }

    public int getPregledId() {
        return pregledId;
    }

    public void setPregledId(int pregledId) {
        this.pregledId = pregledId;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
}

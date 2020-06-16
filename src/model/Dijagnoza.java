package model;

public class Dijagnoza {
    
    private int id;
    private Bolest bolestId;
    private Pregled pregledId;
    private String opis;

    public Dijagnoza() {
    }

    public Dijagnoza(int id, Bolest bolestId, Pregled pregledId, String opis) {
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

    public Bolest getBolestId() {
        return bolestId;
    }

    public void setBolestId(Bolest bolestId) {
        this.bolestId = bolestId;
    }

    public Pregled getPregledId() {
        return pregledId;
    }

    public void setPregledId(Pregled pregledId) {
        this.pregledId = pregledId;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
}

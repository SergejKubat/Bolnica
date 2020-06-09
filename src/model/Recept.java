package model;

public class Recept {
    
    private int id;
    private int lekId;
    private int dijagnozaId;

    public Recept() {
    }

    public Recept(int id, int lekId, int dijagnozaId) {
        this.id = id;
        this.lekId = lekId;
        this.dijagnozaId = dijagnozaId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLekId() {
        return lekId;
    }

    public void setLekId(int lekId) {
        this.lekId = lekId;
    }

    public int getDijagnozaId() {
        return dijagnozaId;
    }

    public void setDijagnozaId(int dijagnozaId) {
        this.dijagnozaId = dijagnozaId;
    }
    
}

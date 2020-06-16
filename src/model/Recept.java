package model;

public class Recept {
    
    private int id;
    private Lek lekId;
    private Dijagnoza dijagnozaId;

    public Recept() {
    }

    public Recept(int id, Lek lekId, Dijagnoza dijagnozaId) {
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

    public Lek getLekId() {
        return lekId;
    }

    public void setLekId(Lek lekId) {
        this.lekId = lekId;
    }

    public Dijagnoza getDijagnozaId() {
        return dijagnozaId;
    }

    public void setDijagnozaId(Dijagnoza dijagnozaId) {
        this.dijagnozaId = dijagnozaId;
    }
    
}

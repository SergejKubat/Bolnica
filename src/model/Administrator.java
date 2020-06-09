package model;

public class Administrator {
    private int id;
    private String email;
    private String lozinka;
    private boolean blokiran;

    public Administrator() {
    }

    public Administrator(int id, String email, String lozinka, boolean blokiran) {
        this.id = id;
        this.email = email;
        this.lozinka = lozinka;
        this.blokiran = blokiran;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public boolean isBlokiran() {
        return blokiran;
    }

    public void setBlokiran(boolean blokiran) {
        this.blokiran = blokiran;
    }
}

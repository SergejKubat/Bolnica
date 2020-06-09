package model;

public class Kompanija {
    
    private int id;
    private String ime;
    private String email;
    private String brojTelefona;
    private String grad;
    private String drzava;
    private String adresa;
    private String opis;

    public Kompanija() {
    }

    public Kompanija(int id, String ime, String email, String brojTelefona, String grad, String drzava, String adresa, String opis) {
        this.id = id;
        this.ime = ime;
        this.email = email;
        this.brojTelefona = brojTelefona;
        this.grad = grad;
        this.drzava = drzava;
        this.adresa = adresa;
        this.opis = opis;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBrojTelefona() {
        return brojTelefona;
    }

    public void setBrojTelefona(String brojTelefona) {
        this.brojTelefona = brojTelefona;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public String getDrzava() {
        return drzava;
    }

    public void setDrzava(String drzava) {
        this.drzava = drzava;
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

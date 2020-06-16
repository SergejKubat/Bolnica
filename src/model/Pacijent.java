package model;

public class Pacijent {
    
    private int pacijentId;
    private Doktor doktorId;
    private String ime;
    private String prezime;
    private String pol;
    private String datumRodjenja;
    private String jmbg;
    private String email;
    private String brojTelefona;
    private String adresa;

    public Pacijent() {
    }

    public Pacijent(int pacijentId, Doktor doktorId, String ime, String prezime, String pol, String datumRodjenja, String jmbg, String email, String brojTelefona, String adresa) {
        this.pacijentId = pacijentId;
        this.doktorId = doktorId;
        this.ime = ime;
        this.prezime = prezime;
        this.pol = pol;
        this.datumRodjenja = datumRodjenja;
        this.jmbg = jmbg;
        this.email = email;
        this.brojTelefona = brojTelefona;
        this.adresa = adresa;
    }

    public int getPacijentId() {
        return pacijentId;
    }

    public void setPacijentId(int pacijentId) {
        this.pacijentId = pacijentId;
    }

    public Doktor getDoktorId() {
        return doktorId;
    }

    public void setDoktorId(Doktor doktorId) {
        this.doktorId = doktorId;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getPol() {
        return pol;
    }

    public void setPol(String pol) {
        this.pol = pol;
    }

    public String getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(String datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
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

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    @Override
    public String toString() {
        return "Pacijent{" + "pacijentId=" + pacijentId + ", doktorId=" + doktorId + ", ime=" + ime + ", prezime=" + prezime + ", pol=" + pol + ", datumRodjenja=" + datumRodjenja + ", jmbg=" + jmbg + ", email=" + email + ", brojTelefona=" + brojTelefona + ", adresa=" + adresa + '}';
    }
    
}
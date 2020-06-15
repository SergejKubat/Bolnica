package model;

public class Doktor {
    
    private int id;
    private DoktorTip doktorTipId;
    private Odeljenje odeljenjeId;
    private String ime;
    private String prezime;
    private String pol;
    private String datumRodjenja;
    private String jmbg;
    private String email;
    private String brojTelefona;
    private String adresa;
    private String lozinka;
    private String opis;
    private boolean blokiran;

    public Doktor() {
    }

    public Doktor(int id, DoktorTip doktorTipId, Odeljenje odeljenjeId, String ime, String prezime, String pol, String datumRodjenja, String jmbg, String email, String brojTelefona, String adresa, String lozinka, String opis, boolean blokiran) {
        this.id = id;
        this.doktorTipId = doktorTipId;
        this.odeljenjeId = odeljenjeId;
        this.ime = ime;
        this.prezime = prezime;
        this.pol = pol;
        this.datumRodjenja = datumRodjenja;
        this.jmbg = jmbg;
        this.email = email;
        this.brojTelefona = brojTelefona;
        this.adresa = adresa;
        this.lozinka = lozinka;
        this.opis = opis;
        this.blokiran = blokiran;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DoktorTip getDoktorTipId() {
        return doktorTipId;
    }

    public void setDoktorTipId(DoktorTip doktorTipId) {
        this.doktorTipId = doktorTipId;
    }

    public Odeljenje getOdeljenjeId() {
        return odeljenjeId;
    }

    public void setOdeljenjeId(Odeljenje odeljenjeId) {
        this.odeljenjeId = odeljenjeId;
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

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public boolean isBlokiran() {
        return blokiran;
    }

    public void setBlokiran(boolean blokiran) {
        this.blokiran = blokiran;
    }
    
}
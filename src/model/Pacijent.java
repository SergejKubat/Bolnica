package model;

import java.util.List;
import java.util.Objects;

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
    private List<Pregled> pregledi;

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

    public List<Pregled> getPregledi() {
        return pregledi;
    }

    public void setPregledi(List<Pregled> pregledi) {
        this.pregledi = pregledi;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.pacijentId;
        hash = 59 * hash + Objects.hashCode(this.doktorId);
        hash = 59 * hash + Objects.hashCode(this.ime);
        hash = 59 * hash + Objects.hashCode(this.prezime);
        hash = 59 * hash + Objects.hashCode(this.pol);
        hash = 59 * hash + Objects.hashCode(this.datumRodjenja);
        hash = 59 * hash + Objects.hashCode(this.jmbg);
        hash = 59 * hash + Objects.hashCode(this.email);
        hash = 59 * hash + Objects.hashCode(this.brojTelefona);
        hash = 59 * hash + Objects.hashCode(this.adresa);
        hash = 59 * hash + Objects.hashCode(this.pregledi);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pacijent other = (Pacijent) obj;
        if (this.pacijentId != other.pacijentId) {
            return false;
        }
        if (!Objects.equals(this.ime, other.ime)) {
            return false;
        }
        if (!Objects.equals(this.prezime, other.prezime)) {
            return false;
        }
        if (!Objects.equals(this.pol, other.pol)) {
            return false;
        }
        if (!Objects.equals(this.datumRodjenja, other.datumRodjenja)) {
            return false;
        }
        if (!Objects.equals(this.jmbg, other.jmbg)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.brojTelefona, other.brojTelefona)) {
            return false;
        }
        if (!Objects.equals(this.adresa, other.adresa)) {
            return false;
        }
        if (!Objects.equals(this.doktorId, other.doktorId)) {
            return false;
        }
        if (!Objects.equals(this.pregledi, other.pregledi)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }
    
}
package com.bolnica.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class DBHelper {

    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";
    private static final String DB_URL = "jdbc:mysql://localhost/bolnica";

    private static Connection connection = null;

    public static void insertPacijent(String ime, String prezime, String pol, String datumRodjenja, String jmbg, String email, String telefon, String adresa) {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            connection.setAutoCommit(false);

            String query = "INSERT INTO pacijent (PACIJENT_IME, PACIJENT_PREZIME, PACIJENT_POL, PACIJENT_DATUM_RODJENJA, PACIJENT_JMBG, PACIJENT_EMAIL, "
                    + "PACIJENT_BROJ_TELEFONA, PACIJENT_ADRESA) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, ime);
                statement.setString(2, prezime);
                statement.setString(3, pol);
                statement.setString(4, datumRodjenja);
                statement.setString(5, jmbg);
                statement.setString(6, email);
                statement.setString(7, telefon);
                statement.setString(8, adresa);
                statement.executeUpdate();
                connection.commit();
                statement.close();
            } catch (SQLException ex) {
                connection.rollback();
            }

            connection.close();
        } catch (SQLException ex) {

        }
    }

    public static void insertPregled(int pacijentId, int doktorId, String datumPregleda, String vremePregleda) {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            connection.setAutoCommit(false);

            String query = "INSERT INTO pregled (PACIJENT_ID, DOKTOR_ID, DIJAGNOZA_ID, PREGLED_DATUM, PREGLED_VREME) VALUES (?, ?, ?, ?, ?);";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, doktorId);
                statement.setInt(2, pacijentId);
                statement.setString(3, datumPregleda);
                statement.setString(4, vremePregleda);
                statement.executeUpdate();
                connection.commit();
                statement.close();
            } catch (SQLException ex) {
                connection.rollback();
            }

            connection.close();
        } catch (SQLException ex) {

        }
    }

    public static void insertDijagnoza(int bolestId, int pregledId, String opis) {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            connection.setAutoCommit(false);

            String query = "INSERT INTO dijagnoza (BOLEST_ID, PREGLED_ID, DIJAGNOZA_OPIS) VALUES (?, ?, ?);";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, bolestId);
                statement.setInt(2, pregledId);
                statement.setString(3, opis);
                statement.executeUpdate();
                connection.commit();
                statement.close();
            } catch (SQLException ex) {
                connection.rollback();
            }

            connection.close();
        } catch (SQLException ex) {

        }
    }

    public static void insertRecept(int lekId, int dijagnozaId) {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            connection.setAutoCommit(false);

            String query = "INSERT INTO recept (LEK_ID, DIJAGNOZA_ID) VALUES (?, ?);";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, lekId);
                statement.setInt(2, dijagnozaId);
                statement.executeUpdate();
                connection.commit();
                statement.close();
            } catch (SQLException ex) {
                connection.rollback();
            }

            connection.close();
        } catch (SQLException ex) {

        }
    }

    public static void insertDoktor(int doktorTipId, int odeljenjeId, String ime, String prezime, String pol, Date datumRodjenja, String jmbg, String email, String brt, String adresa, String lozinka, String opis) {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            connection.setAutoCommit(false);

            String query = "INSERT INTO doktor (DT_ID, ODELJENJE_ID, DOKTOR_IME, DOKTOR_PREZIME, DOKTOR_POL, DOKTOR_DATUM_RODJENJA, DOKTOR_JMBG, DOKTOR_EMAIL, DOKTOR_BROJ_TELEFONA, DOKTOR_ADRESA, DOKTOR_LOZINKA, DOKTOR_OPIS) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, doktorTipId);
                statement.setInt(2, odeljenjeId);
                statement.setString(3, ime);
                statement.setString(4, prezime);
                statement.setString(5, pol);
                statement.setString(6, datumRodjenja.toString());
                statement.setString(7, jmbg);
                statement.setString(8, email);
                statement.setString(9, adresa);
                statement.setString(10, lozinka);
                statement.setString(11, opis);
                statement.executeUpdate();
                connection.commit();
                statement.close();
            } catch (SQLException ex) {
                connection.rollback();
            }

            connection.close();
        } catch (SQLException ex) {

        }
    }

    public static void insertBolnica(int bolnicaTipId, int gradId, String ime, String adresa, String opis) {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            connection.setAutoCommit(false);

            String query = "INSERT INTO bolnica (BT_ID, GRAD_ID, BOLNICA_IME, BOLNICA_ADRESA, BOLNICA_OPIS) VALUES (?,?,?,?,?);";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, bolnicaTipId);
                statement.setInt(2, gradId);
                statement.setString(3, ime);
                statement.setString(4, adresa);
                statement.setString(5, opis);
                statement.executeUpdate();
                connection.commit();
                statement.close();
            } catch (SQLException ex) {
                connection.rollback();
            }

            connection.close();
        } catch (SQLException ex) {

        }
    }

    public static void insertBolest(String naziv, String opis) {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            connection.setAutoCommit(false);

            String query = "INSERT INTO bolest (BOLEST_NAZIV, BOLEST_OPIS) VALUES (?, ?);";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, naziv);
                statement.setString(2, opis);
                statement.executeUpdate();
                connection.commit();
                statement.close();
            } catch (SQLException ex) {
                connection.rollback();
            }

            connection.close();
        } catch (SQLException ex) {

        }
    }

    public static void insertKompanija(String ime, String email, String brt, String grad, String drzava, String adresa, String opis) {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            connection.setAutoCommit(false);

            String query = "INSERT INTO kompanija (KOMPANIJA_IME, KOMPANIJA_EMAIL, KOMPANIJA_BROJ_TELEFONA, KOMPANIJA_GRAD, KOMPANIJA_DRZAVA, KOMPANIJA_ADRESA, KOMPANIJA_OPIS) VALUES (?, ?, ?, ?, ?, ?, ?);";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, ime);
                statement.setString(2, email);
                statement.setString(3, brt);
                statement.setString(4, grad);
                statement.setString(5, drzava);
                statement.setString(6, adresa);
                statement.setString(7, opis);
                statement.executeUpdate();
                connection.commit();
                statement.close();
            } catch (SQLException ex) {
                connection.rollback();
            }

            connection.close();
        } catch (SQLException ex) {

        }
    }

    public static void insertLek(int kompanijaId, String naziv, String tip) {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            connection.setAutoCommit(false);

            String query = "INSERT INTO lek (KOMPANIJA_ID, LEK_NAZIV, LEK_TIP) VALUES (?, ?, ?);";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, kompanijaId);
                statement.setString(2, naziv);
                statement.setString(3, tip);
                statement.executeUpdate();
                connection.commit();
                statement.close();
            } catch (SQLException ex) {
                connection.rollback();
            }

            connection.close();
        } catch (SQLException ex) {

        }
    }

    public static void insertAdministator(String email, String lozinka) {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            connection.setAutoCommit(false);

            String query = "INSERT INTO administrator (admin_email, admin_lozinka) VALUES (?, ?);";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, email);
                statement.setString(2, lozinka);
                statement.executeUpdate();
                connection.commit();
                statement.close();
            } catch (SQLException ex) {
                connection.rollback();
            }

            connection.close();
        } catch (SQLException ex) {

        }
    }
    
    public static void updatePacijent(int id, String ime, String prezime, String pol, String datumRodjenja, String jmbg, String email, String telefon, String adresa) {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            connection.setAutoCommit(false);

            String query = "UPDATE pacijent SET PACIJENT_IME = ?, PACIJENT_PREZIME = ?, PACIJENT_POL = ?, PACIJENT_DATUM_RODJENJA = ?, PACIJENT_JMBG = ?, PACIJENT_EMAIL = ?, PACIJENT_BROJ_TELEFONA = ?, PACIJENT_ADRESA = ? WHERE pacijent.PACIJENT_ID = ?;";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, ime);
                statement.setString(2, prezime);
                statement.setString(3, pol);
                statement.setString(4, datumRodjenja);
                statement.setString(5, jmbg);
                statement.setString(6, email);
                statement.setString(7, telefon);
                statement.setString(8, adresa);
                statement.setInt(9, id);
                statement.executeUpdate();
                connection.commit();
                statement.close();
            } catch (SQLException ex) {
                connection.rollback();
            }

            connection.close();
        } catch (SQLException ex) {

        }
    }

}

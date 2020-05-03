package com.bolnica.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseHelper {

    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";
    private static final String DB_URL = "jdbc:mysql://localhost/bolnica";

    private static Connection connection = null;
    
    public static void insertPacijent(String ime, String prezime, String pol, String datumRodjenja, String jmbg, String email, String telefon, String adresa) {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            connection.setAutoCommit(false);

            String query = "INSERT INTO pacijent (PACIJENT_IME, PACIJENT_PREZIME, PACIJENT_POL, PACIJENT_DATUM_RODJENJA, PACIJENT_JMBG, PACIJENT_EMAIL, "
                    + "PACIJENT_BROJ_TELEFONA, PACIJENT_ADRESA) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

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
                ex.printStackTrace();
            }

            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void insertPregled(int pacijentId, int doktorId, String datumPregleda, String vremePregleda) {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            connection.setAutoCommit(false);

            String query = "INSERT INTO pregled (PACIJENT_ID, DOKTOR_ID, DIJAGNOZA_ID, PREGLED_DATUM, PREGLED_VREME) VALUES (?, ?, ?, ?, ?)";

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
                ex.printStackTrace();
            }

            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void insertGrad(String imeGrada) {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            connection.setAutoCommit(false);

            String query = "INSERT INTO grad(GRAD_IME) VALUES (?)";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, imeGrada);
                statement.executeUpdate();
                connection.commit();
                statement.close();
            } catch (SQLException ex) {
                connection.rollback();
                ex.printStackTrace();
            }

            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}

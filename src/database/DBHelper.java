package database;

import model.Administrator;
import model.Bolest;
import model.Bolnica;
import model.Dijagnoza;
import model.Doktor;
import model.Grad;
import model.Kompanija;
import model.Lek;
import model.Pacijent;
import model.Pregled;
import model.Recept;
import utils.HashUtil;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class DBHelper {

    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";
    private static final String DB_URL = "jdbc:mysql://localhost/bolnica";

    private static Connection connection = null;

    public static Doktor prijavaDoktor(String email, String lozinka) {

        Doktor doktor = null;

        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            connection.setAutoCommit(false);

            String query = "SELECT * FROM doktor WHERE doktor.DOKTOR_EMAIL = ? AND doktor.DOKTOR_LOZINKA = ?;";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, email);
                statement.setString(2, HashUtil.getSHA(lozinka));
                ResultSet result = statement.executeQuery();
                if (result.next()) {
                    if (result.getBoolean(14)) {
                        return doktor;
                    }
                    doktor = new Doktor(result.getInt(1), result.getInt(2), result.getInt(3), result.getString(4), result.getString(5), result.getString(6),
                            result.getDate(7).toString(), result.getString(8), result.getString(9), result.getString(10), result.getString(11), result.getString(12), result.getString(13),
                            result.getBoolean(14));
                }
                connection.commit();
                statement.close();
                return doktor;
            } catch (SQLException ex) {
                connection.rollback();
            }

            connection.close();
        } catch (SQLException ex) {

        }
        return doktor;
    }

    public static Administrator prijavaAdministrator(String email, String lozinka) {

        Administrator administrator = null;

        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            connection.setAutoCommit(false);

            String query = "SELECT * FROM admin WHERE admin.ADMIN_EMAIL = ? AND admin.ADMIN_LOZINKA = ?;";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, email);
                statement.setString(2, HashUtil.getSHA(lozinka));
                ResultSet result = statement.executeQuery();
                if (result.next()) {
                    if (result.getBoolean(4)) {
                        return administrator;
                    }
                    administrator = new Administrator(result.getInt(1), result.getString(2), result.getString(3), result.getBoolean(4));
                }
                connection.commit();
                statement.close();
                return administrator;
            } catch (SQLException ex) {
                connection.rollback();
            }

            connection.close();
        } catch (SQLException ex) {

        }
        return administrator;
    }

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

            String query = "INSERT INTO doktor (DT_ID, ODELJENJE_ID, DOKTOR_IME, DOKTOR_PREZIME, DOKTOR_POL, DOKTOR_DATUM_RODJENJA, DOKTOR_JMBG, DOKTOR_EMAIL, DOKTOR_BROJ_TELEFONA, DOKTOR_ADRESA, DOKTOR_LOZINKA, DOKTOR_OPIS, DOKTOR_IS_BLOKIRAN) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, doktorTipId);
                statement.setInt(2, odeljenjeId);
                statement.setString(3, ime);
                statement.setString(4, prezime);
                statement.setString(5, pol);
                statement.setDate(6, datumRodjenja);
                statement.setString(7, jmbg);
                statement.setString(8, email);
                statement.setString(9, brt);
                statement.setString(10, adresa);
                statement.setString(11, HashUtil.getSHA(lozinka));
                statement.setString(12, opis);
                statement.setBoolean(13, false);
                statement.executeUpdate();
                connection.commit();
                statement.close();
            } catch (SQLException ex) {
                connection.rollback();
                System.out.println(ex);
            }

            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex);
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

    public static void updatePregled(int id, String datum, String vreme) {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            connection.setAutoCommit(false);

            String query = "UPDATE pregled SET PREGLED_DATUM = ?, PREGLED_VREME = ? WHERE pregled.PREGLED_ID = ?;";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, datum);
                statement.setString(2, vreme);
                statement.setInt(3, id);
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

    public static void updateDijagnoza(int id, String opis) {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            connection.setAutoCommit(false);

            String query = "UPDATE dijagnoza SET DIJAGNOZA_OPIS = ? WHERE dijagnoza.DIJAGNOZA_ID = ?;";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, opis);
                statement.setInt(2, id);
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

    public static void updateRecept(int id, int lekId) {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            connection.setAutoCommit(false);

            String query = "UPDATE recept SET LEK_ID = ? WHERE recept.RECEPT_ID = ?;";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, lekId);
                statement.setInt(2, id);
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

    public static void updateLozinka(int id, String lozinka) {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            connection.setAutoCommit(false);

            String query = "UPDATE doktor SET DOKTOR_LOZINKA = ? WHERE doktor.DOKTOR_ID = ?;";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, HashUtil.getSHA(lozinka));
                statement.setInt(2, id);
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

    public static void blockDoktor(int id, boolean tip) {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            connection.setAutoCommit(false);

            int block = tip ? 1 : 0;

            String query = "UPDATE doktor SET DOKTOR_IS_BLOKIRAN = ? WHERE doktor.DOKTOR_ID = ?;";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, block);
                statement.setInt(2, id);
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

    public static void blockAdmin(int id, boolean tip) {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            connection.setAutoCommit(false);

            int block = tip ? 1 : 0;

            String query = "UPDATE admin SET ADMIN_IS_BLOCKED = ? WHERE admin.ADMIN_ID = ?;";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, block);
                statement.setInt(2, id);
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

    public static List<Pacijent> selectAllPacijent(int id) {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            connection.setAutoCommit(false);

            String query = "SELECT * FROM pacijent WHERE pacijent.DOKTOR_ID = ?;";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                List<Pacijent> pacijenti = new ArrayList<>();
                ResultSet result = statement.executeQuery();
                while (result.next()) {
                    pacijenti.add(new Pacijent(result.getInt(1), result.getInt(2), result.getString(3), result.getString(4), result.getString(5),
                            result.getDate(6).toString(), result.getString(7), result.getString(8), result.getString(9), result.getString(10)));
                }
                connection.commit();
                statement.close();
                return pacijenti;
            } catch (SQLException ex) {
                connection.rollback();
            }

            connection.close();
        } catch (SQLException ex) {

        }
        return null;
    }

    public static List<Pregled> selectAllPregled(int id) {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            connection.setAutoCommit(false);

            String query = "SELECT * FROM pregled WHERE pregled.DOKTOR_ID = ?;";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                List<Pregled> pregledi = new ArrayList<>();
                ResultSet result = statement.executeQuery();
                while (result.next()) {
                    pregledi.add(new Pregled(result.getInt(1), result.getInt(2), result.getInt(3), result.getInt(4), result.getString(5), result.getString(6)));
                }
                connection.commit();
                statement.close();
                return pregledi;
            } catch (SQLException ex) {
                connection.rollback();
            }

            connection.close();
        } catch (SQLException ex) {

        }
        return null;
    }

    public static List<Dijagnoza> selectAllDijagnoza(int id) {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            connection.setAutoCommit(false);

            String query = "SELECT DIJAGNOZA_ID, BOLEST_ID, PREGLED_ID, DIJAGNOZA_OPIS FROM dijagnoza JOIN pregled ON dijagnoza.DIJAGNOZA_ID = pregled.DIJAGNOZA_ID "
                    + "WHERE pregled.DOKTOR_ID = ?;";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                List<Dijagnoza> dijagnoze = new ArrayList<>();
                ResultSet result = statement.executeQuery();
                while (result.next()) {
                    dijagnoze.add(new Dijagnoza(result.getInt(1), result.getInt(2), result.getInt(3), result.getString(4)));
                }
                connection.commit();
                statement.close();
                return dijagnoze;
            } catch (SQLException ex) {
                connection.rollback();
            }

            connection.close();
        } catch (SQLException ex) {

        }
        return null;
    }

    public static List<Recept> selectAllRecept(int id) {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            connection.setAutoCommit(false);

            String query = "SELECT RECEPT_ID, LEK_ID, DIJAGNOZA_ID FROM recept JOIN dijagnoza ON recept.DIJAGNOZA_ID = dijagnoza.DIJAGNOZA_ID JOIN pregled ON "
                    + "dijagnoza.DIJAGNOZA_ID = pregled.DIJAGNOZA_ID WHERE doktor.DOKTOR_ID = 1";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                List<Recept> recepti = new ArrayList<>();
                ResultSet result = statement.executeQuery();
                while (result.next()) {
                    recepti.add(new Recept(result.getInt(1), result.getInt(1), result.getInt(1)));
                }
                connection.commit();
                statement.close();
                return recepti;
            } catch (SQLException ex) {
                connection.rollback();
            }

            connection.close();
        } catch (SQLException ex) {

        }
        return null;
    }

    public static Administrator selectAdministrator(int id) {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            connection.setAutoCommit(false);

            String query = "SELECT * FROM admin WHERE admin.ADMIN_ID = ?;";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                ResultSet result = statement.executeQuery();
                result.next();
                Administrator admin = new Administrator(result.getInt(1), result.getString(2), result.getString(3), result.getBoolean(4));
                connection.commit();
                statement.close();
                return admin;
            } catch (SQLException ex) {
                connection.rollback();
            }

            connection.close();
        } catch (SQLException ex) {

        }
        return null;
    }

    public static Doktor selectDoktor(int id) {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            connection.setAutoCommit(false);

            String query = "SELECT * FROM doktor WHERE doktor.DOKTOR_ID = ?;";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                ResultSet result = statement.executeQuery();
                result.next();
                Doktor doktor = new Doktor(result.getInt(1), result.getInt(2), result.getInt(3), result.getString(4), result.getString(5), result.getString(6),
                        result.getDate(7).toString(), result.getString(8), result.getString(9), result.getString(10), result.getString(11), result.getString(12), result.getString(13),
                        result.getBoolean(14));
                connection.commit();
                statement.close();
                return doktor;
            } catch (SQLException ex) {
                connection.rollback();
                ex.printStackTrace();
            }

            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }
    
    public static Doktor selectDoktorByEmail(String email) {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            connection.setAutoCommit(false);

            String query = "SELECT * FROM doktor WHERE doktor.DOKTOR_EMAIL = ?;";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, email);
                ResultSet result = statement.executeQuery();
                result.next();
                Doktor doktor = new Doktor(result.getInt(1), result.getInt(2), result.getInt(3), result.getString(4), result.getString(5), result.getString(6),
                        result.getDate(7).toString(), result.getString(8), result.getString(9), result.getString(10), result.getString(11), result.getString(12), result.getString(13),
                        result.getBoolean(14));
                connection.commit();
                statement.close();
                return doktor;
            } catch (SQLException ex) {
                connection.rollback();
            }

            connection.close();
        } catch (SQLException ex) {

        }
        return null;
    } 

    public static List<Bolest> selectAllBolest() {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            connection.setAutoCommit(false);

            String query = "SELECT * FROM bolest;";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                List<Bolest> bolesti = new ArrayList<>();
                ResultSet result = statement.executeQuery();
                while (result.next()) {
                    bolesti.add(new Bolest(result.getInt(1), result.getString(2), result.getString(3)));
                }
                connection.commit();
                statement.close();
                return bolesti;
            } catch (SQLException ex) {
                connection.rollback();
            }

            connection.close();
        } catch (SQLException ex) {

        }
        return null;
    }

    public static List<Lek> selectAllLek() {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            connection.setAutoCommit(false);

            String query = "SELECT * FROM bolest;";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                List<Lek> lekovi = new ArrayList<>();
                ResultSet result = statement.executeQuery();
                while (result.next()) {
                    lekovi.add(new Lek(result.getInt(1), result.getInt(2), result.getString(3), result.getString(4)));
                }
                connection.commit();
                statement.close();
                return lekovi;
            } catch (SQLException ex) {
                connection.rollback();
            }

            connection.close();
        } catch (SQLException ex) {

        }
        return null;
    }

    public static List<Administrator> selectAllAdministrator() {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            connection.setAutoCommit(false);

            String query = "SELECT * FROM admin;";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                List<Administrator> administratori = new ArrayList<>();
                ResultSet result = statement.executeQuery();
                while (result.next()) {
                    administratori.add(new Administrator(result.getInt(1), result.getString(2), result.getString(3), result.getBoolean(4)));
                }
                connection.commit();
                statement.close();
                return administratori;
            } catch (SQLException ex) {
                connection.rollback();
            }

            connection.close();
        } catch (SQLException ex) {

        }
        return null;
    }

    public static List<Doktor> selectAllDoktor() {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            connection.setAutoCommit(false);

            String query = "SELECT * FROM doktor;";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                List<Doktor> doktori = new ArrayList<>();
                ResultSet result = statement.executeQuery();
                while (result.next()) {
                    doktori.add(new Doktor(result.getInt(1), result.getInt(2), result.getInt(3), result.getString(4), result.getString(5), result.getString(6),
                            result.getDate(7).toString(), result.getString(8), result.getString(9), result.getString(10), result.getString(11), result.getString(12), result.getString(13),
                            result.getBoolean(14)));
                }
                connection.commit();
                statement.close();
                return doktori;
            } catch (SQLException ex) {
                connection.rollback();
            }

            connection.close();
        } catch (SQLException ex) {

        }
        return null;
    }

    public static List<Bolnica> selectAllBolnica() {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            connection.setAutoCommit(false);

            String query = "SELECT * FROM bolnica;";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                List<Bolnica> bolnice = new ArrayList<>();
                ResultSet result = statement.executeQuery();
                while (result.next()) {
                    bolnice.add(new Bolnica(result.getInt(1), result.getInt(2), result.getInt(3), result.getString(4), result.getString(5), result.getString(6)));
                }
                connection.commit();
                statement.close();
                return bolnice;
            } catch (SQLException ex) {
                connection.rollback();
            }

            connection.close();
        } catch (SQLException ex) {

        }
        return null;
    }

    public static List<Kompanija> selectAllKompanija() {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            connection.setAutoCommit(false);

            String query = "SELECT * FROM kompanija;";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                List<Kompanija> kompanije = new ArrayList<>();
                ResultSet result = statement.executeQuery();
                while (result.next()) {
                    kompanije.add(new Kompanija(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getString(6),
                            result.getString(7), result.getString(8)));
                }
                connection.commit();
                statement.close();
                return kompanije;
            } catch (SQLException ex) {
                connection.rollback();
            }

            connection.close();
        } catch (SQLException ex) {

        }
        return null;
    }

    public static List<Grad> selectAllGrad() {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            connection.setAutoCommit(false);

            String query = "SELECT * FROM grad;";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                List<Grad> gradovi = new ArrayList<>();
                ResultSet result = statement.executeQuery();
                while (result.next()) {
                    gradovi.add(new Grad(result.getInt(1), result.getString(2)));
                }
                connection.commit();
                statement.close();
                return gradovi;
            } catch (SQLException ex) {
                connection.rollback();
            }

            connection.close();
        } catch (SQLException ex) {

        }
        return null;
    }

    public static void deletePregled(int id) {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            connection.setAutoCommit(false);

            String query = "DELETE FROM pregled WHERE pregled.PREGLED_ID = ?";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
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

    public static void deleteDijagnoza(int id) {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            connection.setAutoCommit(false);

            String query = "DELETE FROM dijagnoza WHERE dijagnoza.DIJAGNOZA_ID = ?";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
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

    public static void deleteRecept(int id) {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            connection.setAutoCommit(false);

            String query = "DELETE FROM recept WHERE recept.RECEPT_ID = ?";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
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

    public static void deleteDoktor(int id) {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            connection.setAutoCommit(false);

            String query = "DELETE FROM doktor WHERE doktor.DOKTOR_ID = ?";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
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

    public static void deleteAdministrator(int id) {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            connection.setAutoCommit(false);

            String query = "DELETE FROM admin WHERE admin.ADMIN_ID = ?";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
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

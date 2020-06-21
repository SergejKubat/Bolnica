package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    
    private static final String REGEX_IME = "[a-zA-Z0-9]{3,32}";
    private static final String REGEX_LOZINKA = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
    private static final String REGEX_EMAIL = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
    private static final String REGEX_BROJ_TELEFONA = "[0-9]{3}-[0-9]{3}-[0-9]{3,4}";
    private static final String REGEX_SIGURNOSNI_KOD = "[0-9]{6}";
    private static final String REGEX_VREME = "[0-9]{2}:[0-9]{2}";

    public static boolean proveriIme(String ime) {
        return Pattern.matches(REGEX_IME, ime);
    }

    public static boolean proveriLozinku(String lozinka) {
        return Pattern.matches(REGEX_LOZINKA, lozinka);
    }

    public static boolean proveriEmail(String email) {
        Pattern VALID_EMAIL_ADDRESS_REGEX
                = Pattern.compile(REGEX_EMAIL, Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
    }

    public static boolean proveriBrojTelefona(String brojTelefona) {
        return Pattern.matches(REGEX_BROJ_TELEFONA, brojTelefona);
    }
    
    public static boolean proveriSigurnosniKod(String sigurnosniKod) {
        return Pattern.matches(REGEX_SIGURNOSNI_KOD, sigurnosniKod);
    }
    
    public static boolean proveriVreme(String sigurnosniKod) {
        return Pattern.matches(REGEX_VREME, sigurnosniKod);
    }
}

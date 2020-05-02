package com.bolnica.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    public static boolean proveriIme(String ime) {
        return Pattern.matches("[a-zA-Z0-9]{3,32}", ime);
    }

    public static boolean proveriLozinku(String lozinka) {
        return Pattern.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", lozinka);
    }

    public static boolean proveriEmail(String email) {
        Pattern VALID_EMAIL_ADDRESS_REGEX
                = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
    }

    public static boolean proveriBroj(String brojTelefona) {
        return Pattern.matches("\\d{9,10}", brojTelefona);
    }
}

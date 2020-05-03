package com.bolnica.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtil {

    private static final String SALT = "EHEwIy8tqB9xcIMk";

    public static String getSHA(String lozinka) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            lozinka = lozinka + SALT;

            byte[] messageDigest = md.digest(lozinka.getBytes());

            BigInteger no = new BigInteger(1, messageDigest);

            String hashtext = no.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Exception thrown for incorrect algorithm: " + e);
            return null;
        }
    }
}

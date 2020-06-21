package utils;

public class Utilities {
    
    public static String generisiSigurnosniKod() {
        StringBuilder nasumicanBroj = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            nasumicanBroj.append((int) (Math.random() * 10));
        }
        return nasumicanBroj.toString();
    }
    
}

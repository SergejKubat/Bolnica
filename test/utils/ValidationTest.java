package utils;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ValidationTest {
    
    public ValidationTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testProveriIme() {
        System.out.println("proveriIme");
        String ime = "Nikola";
        boolean expResult = true;
        boolean result = Validation.proveriIme(ime);
        assertEquals(expResult, result);
    }

    @Test
    public void testProveriLozinku() {
        System.out.println("proveriLozinku");
        String lozinka = "P@ssw0rd!";
        boolean expResult = true;
        boolean result = Validation.proveriLozinku(lozinka);
        assertEquals(expResult, result);
    }

    @Test
    public void testProveriEmail() {
        System.out.println("proveriEmail");
        String email = "nikola@gmail.com";
        boolean expResult = true;
        boolean result = Validation.proveriEmail(email);
        assertEquals(expResult, result);
    }

    @Test
    public void testProveriBrojTelefona() {
        System.out.println("proveriBrojTelefona");
        String brojTelefona = "065-444-6642";
        boolean expResult = true;
        boolean result = Validation.proveriBrojTelefona(brojTelefona);
        assertEquals(expResult, result);
    }

    @Test
    public void testProveriSigurnosniKod() {
        System.out.println("proveriSigurnosniKod");
        String sigurnosniKod = "554387";
        boolean expResult = true;
        boolean result = Validation.proveriSigurnosniKod(sigurnosniKod);
        assertEquals(expResult, result);
    }

    @Test
    public void testProveriVreme() {
        System.out.println("proveriVreme");
        String vreme = "14:15";
        boolean expResult = true;
        boolean result = Validation.proveriVreme(vreme);
        assertEquals(expResult, result);
    }
    
}

package studentenadmin;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import studentadmin.*;

/**
 * Test klasse voor scholers
 */
public class ScholerTest {

    private Opleiding opleiding = null;
    private Cpp cpp ;
    private Regulier regulier = null;
    private Scholer scholer = null;




    @Before
    public void setUp() throws Exception {

        cpp = new Cpp("Java", 6);
        opleiding = new Opleiding("Wiskunde", 120.0);
        scholer = new Scholer("Daniel", cpp);
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Test public void testScholerMetOpleiding() throws StudentAdminException {
        thrown.expect(StudentAdminException.class);
        thrown.expectMessage("Scholers mogen alleen met Cpp\'s gekoppeld worden");

        Scholer newScholer = new Scholer("Tobias", opleiding);
    }

    @Test public void testScholerMetTeKorteNaam() throws StudentAdminException {
        thrown.expect(StudentAdminException.class);
        thrown.expectMessage("De naam van een student moet minimaal 2 letters bevatten");

        Scholer newScholer = new Scholer("T!3  >", cpp);
    }




    @Test public void testVerhoogAantalModulesTeVeel() throws StudentAdminException {
        thrown.expect(StudentAdminException.class);
        thrown.expectMessage("Aantal behaalde modules is groter dan deze CPP heeft");

        scholer.verhoogAantalModules(7);
    }


    @Test public void testVerhoogAantalModulesKleinerNul() throws StudentAdminException {
        thrown.expect(StudentAdminException.class);
        thrown.expectMessage("Aantal behaalde modules is kleiner dan 0");

        scholer.verhoogAantalModules(-1);
    }
}

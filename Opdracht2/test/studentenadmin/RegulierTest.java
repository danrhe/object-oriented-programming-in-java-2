package studentenadmin;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import studentadmin.Cpp;
import studentadmin.Opleiding;
import studentadmin.Regulier;
import studentadmin.StudentAdminException;

/**
 * Test klasse voor reguliere studenten
 */
public class RegulierTest {

    private Cpp cpp ;
    private Regulier regulier = null;
    private Opleiding opleiding = null;


    @Before
    public void setUp() throws Exception {

        cpp = new Cpp("Java", 6);
        opleiding = new Opleiding("Wiskunde", 120.0);
        regulier = new Regulier("Daniel", opleiding);
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test public void testRegulierMetCpp() throws StudentAdminException {
        thrown.expect(StudentAdminException.class);
        thrown.expectMessage("Reguliere studenten mogen alleen met opleidingen gekoppeld worden");

        Regulier newRegulier = new Regulier("Tobias", cpp);
    }

    @Test public void testRegulierMetTeKorteNaam() throws StudentAdminException {
        thrown.expect(StudentAdminException.class);
        thrown.expectMessage("De naam van een student moet minimaal 2 letters bevatten");

        Regulier newRegulier = new Regulier("  C!!?", opleiding);
    }

    @Test public void testVerhoogBehaaldeStudiepuntenTeVeel() throws StudentAdminException {
        thrown.expect(StudentAdminException.class);
        thrown.expectMessage("Aantal behaalde studiepunten is groter dan het aantal studiepunten van de opleiding");

        regulier.verhoogBehaaldeStudiepunten(120.1);
    }


    @Test public void testVerhoogBehaaldeStudiepuntenKleinerNul() throws StudentAdminException {
        thrown.expect(StudentAdminException.class);
        thrown.expectMessage("Aantal behaalde studiepunten is kleiner dan 0");

        regulier.verhoogBehaaldeStudiepunten(-0.1);
    }
}
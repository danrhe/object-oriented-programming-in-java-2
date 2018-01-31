package studentenadmin;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import studentadmin.Cpp;
import studentadmin.Opleiding;
import studentadmin.StudentAdmin;
import studentadmin.StudentAdminException;


public class StudentAdminTest {


    private StudentAdmin studentAdmin = null;
    private Cpp cpp = null;
    private Opleiding opleiding = null;

    @Before
    public void setUp() throws Exception {

        studentAdmin = new StudentAdmin();
        cpp = new Cpp("Test_cpp", 6);
        opleiding = new Opleiding("Test_Opleiding", 120.0);

        try {
            studentAdmin.voegOpleidingToe("Wiskunde", 160.0);
            studentAdmin.voegOpleidingToe("Informatica", 120.0);
            studentAdmin.voegCppToe("CPP Softwarearchitect", 4);
            studentAdmin.voegCppToe("CPP Java", 6);
            studentAdmin.voegCppToe("CPP System Ontwikkelaar", 3);

            studentAdmin.voegRegulierToe("Tobias","Wiskunde");
            studentAdmin.voegScholerToe("Hans","CPP Java");

        } catch (StudentAdminException studaminEx) {

            System.out.println(studaminEx.getMessage());

        }


    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Test public void testVoegOpleidingToeBestaatAl() throws StudentAdminException {
        thrown.expect(StudentAdminException.class);
        thrown.expectMessage("Programma met deze naam bestaat al");

        studentAdmin.voegOpleidingToe("Wiskunde", 180.0);
    }


    @Test public void testVoegCppToeBestaatAl() throws StudentAdminException {
        thrown.expect(StudentAdminException.class);
        thrown.expectMessage("Programma met deze naam bestaat al");

        studentAdmin.voegOpleidingToe("CPP Java", 8);
    }


    @Test
    public void testVoegRegulierToeBestaatAl() throws StudentAdminException {

        thrown.expect(StudentAdminException.class);
        thrown.expectMessage("Student met dezelfde naam staat al geregistreerd");

        studentAdmin.voegRegulierToe("Tobias","Informatica");

    }

    @Test
    public void testVoegRegulierToeOnbekendProgamma() throws StudentAdminException {

        String naamStudent = "Daniel";
        String naamProgramma = "Natuurkunde";

        thrown.expect(StudentAdminException.class);
        thrown.expectMessage("Programma met naam " + naamProgramma + " is niet bekend");

        studentAdmin.voegRegulierToe(naamStudent, naamProgramma);

    }

    @Test
    public void testVoegRegulierToeVerkeerdeProgramma() throws StudentAdminException {

        thrown.expect(StudentAdminException.class);
        thrown.expectMessage("Reguliere studenten mogen alleen met opleidingen gekoppeld worden");

        studentAdmin.voegRegulierToe("Daniel","CPP Java");

    }


    @Test
    public void testVoegScholerToeBestaatAl() throws StudentAdminException {

        thrown.expect(StudentAdminException.class);
        thrown.expectMessage("Student met dezelfde naam staat al geregistreerd");

        studentAdmin.voegScholerToe("Hans","CPP System Ontwikkelaar");

    }

    @Test
    public void testVoegScholerToeOnbekendProgamma() throws StudentAdminException {
        String naamStudent = "Kasper";
        String naamProgramma = "CPP Database Developer";

        thrown.expect(StudentAdminException.class);
        thrown.expectMessage("Programma met naam " + naamProgramma + " is niet bekend");

        studentAdmin.voegScholerToe(naamStudent, naamProgramma);

    }


    @Test
    public void testVoegScholerToeVerkeerdProgamma() throws StudentAdminException {
        thrown.expect(StudentAdminException.class);
        thrown.expectMessage("Scholers mogen alleen met Cpp\'s gekoppeld worden");

        studentAdmin.voegScholerToe("Kasper","Informatica");

    }

    @Test
    public void testVerhoogAantalStudiepuntenOnbekendStudent() throws StudentAdminException {

        String naam = "James";
        thrown.expect(StudentAdminException.class);
        thrown.expectMessage("Student met naam " + naam + " is niet in geregistreerd");

        studentAdmin.verhoogAantalStudiepunten(naam,44.0);
    }

    @Test
    public void testVerhoogAantalStudiepuntenTeVeel() throws StudentAdminException {
        thrown.expect(StudentAdminException.class);
        thrown.expectMessage("Aantal behaalde studiepunten is groter dan het aantal studiepunten van de opleiding");

        studentAdmin.verhoogAantalStudiepunten("Tobias",244.0);
    }
    @Test
    public void testVerhoogAantalStudiepuntenKleinerNull() throws StudentAdminException {
        thrown.expect(StudentAdminException.class);
        thrown.expectMessage("Aantal behaalde studiepunten is kleiner dan 0");

        studentAdmin.verhoogAantalStudiepunten("Tobias",-1.0);
    }

    @Test
    public void testVerhoogAantalModulesOnbekendStudent() throws StudentAdminException {
        String naam = "Hansi";
        thrown.expect(StudentAdminException.class);
        thrown.expectMessage("Student met naam " + naam + " is niet in geregistreerd");


        studentAdmin.verhoogAantalModules(naam,4);
    }

    @Test
    public void testVerhoogAantalModulesTeVeel() throws StudentAdminException {
        thrown.expect(StudentAdminException.class);
        thrown.expectMessage("Aantal behaalde modules is groter dan deze CPP heeft");

        studentAdmin.verhoogAantalModules("Hans",7);
    }

    @Test
    public void testVerhoogAantalModulesKleinerNull() throws StudentAdminException {
        thrown.expect(StudentAdminException.class);
        thrown.expectMessage("Aantal behaalde modules is kleiner dan 0");

        studentAdmin.verhoogAantalModules("Hans",-1);
    }

    @Test
    public void testGetStudentInfo() throws StudentAdminException {

        String naam = "Hansi";
        thrown.expect(StudentAdminException.class);
        thrown.expectMessage("Student met naam " + naam + " is niet in geregistreerd");

        studentAdmin.getStudentInfo(naam);
    }



}
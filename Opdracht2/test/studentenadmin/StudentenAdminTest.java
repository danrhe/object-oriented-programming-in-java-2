package studentenadmin;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;


public class StudentenAdminTest {


    StudentenAdmin studentenAdmin = null;
    Cpp cpp = null;
    Opleiding opleiding = null;

    @Before
    public void setUp() throws Exception {

        studentenAdmin = new StudentenAdmin();
        cpp = new Cpp("Test_cpp", 6);
        opleiding = new Opleiding("Test_Opleiding", 120.0);

        try {
            studentenAdmin.voegOpleidingToe("Wiskunde", 160.0);
            studentenAdmin.voegOpleidingToe("Informatica", 120.0);
            studentenAdmin.voegCppToe("CPP Softwarearchitect", 4);
            studentenAdmin.voegCppToe("CPP Java", 6);
            studentenAdmin.voegCppToe("CPP System Ontwikkelaar", 3);

        } catch (StudentenAdminException studaminEx) {

            System.out.println(studaminEx.getMessage());

        }


    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test public void testVoegOpleidingToeBestaatAl() throws StudentenAdminException {
        thrown.expect(StudentenAdminException.class);
        thrown.expectMessage("Programma met deze naam bestaat al");

        studentenAdmin.voegOpleidingToe("Wiskunde", 180.0);
    }


    @Test public void testVoegCppToeBestaatAl() throws StudentenAdminException {
        thrown.expect(StudentenAdminException.class);
        thrown.expectMessage("Programma met deze naam bestaat al");

        studentenAdmin.voegOpleidingToe("CPP Java", 8);
    }


    @Test
    public void testVoegRegulierToeBestaatAl() throws StudentenAdminException {
        studentenAdmin.voegRegulierToe("Daniel","Wiskunde");

        thrown.expect(StudentenAdminException.class);
        thrown.expectMessage("Student met dezelfde naam staat al geregistreerd");

        studentenAdmin.voegRegulierToe("Daniel","Informatica");

    }


    @Test
    public void testVoegScholerToeBestaatAl() throws StudentenAdminException {
        studentenAdmin.voegScholerToe("Daniel","CPP Java");

        thrown.expect(StudentenAdminException.class);
        thrown.expectMessage("Student met dezelfde naam staat al geregistreerd");

        studentenAdmin.voegScholerToe("Daniel","CPP System Ontwikkelaar");

    }

    @Test
    public void testVoegScholerToeVerkeerdProgamma() throws StudentenAdminException {
        thrown.expect(StudentenAdminException.class);
        thrown.expectMessage("Programma met deze naam bestaat al");

        studentenAdmin.voegRegulierToe("Daniel","CPP Java");

    }

    @Test
    public void testVoegStudentToeOnbekendProgamma() {
        boolean thrown = false;
        try {
            studentenAdmin.voegRegulierToe("Daniel","Natuurkunde");
        } catch (StudentenAdminException studEx){

            thrown = true;

        }
    }

}
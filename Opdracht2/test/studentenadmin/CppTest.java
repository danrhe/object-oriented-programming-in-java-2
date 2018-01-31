package studentenadmin;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

/**
 * Created by Danyou on 30.01.18.
 */
public class CppTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testVoegOpleidingToeBestaatAl() throws StudentenAdminException {
        thrown.expect(StudentenAdminException.class);
        thrown.expectMessage("Naam mag niet onbekend zijn");

        Cpp cpp = new Cpp(null,3);
    }

}
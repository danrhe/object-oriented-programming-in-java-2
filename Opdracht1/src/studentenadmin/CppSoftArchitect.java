package studentenadmin;

/**
 * Specificeert de gegevens van de professional program Software Architect
 */
public class CppSoftArchitect extends Cpp {
    public CppSoftArchitect() {
        super(CppKeuze.CPPSOFTARCHITECT.getNaam());
        setAantalModules(CppKeuze.CPPSOFTARCHITECT.getAantalModules());
    }
}

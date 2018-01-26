package studentenadmin;

/**
 * Specificeert de gegevens van de professional program System ontwikkelaar
 */
public class CppSysDev extends Cpp {
    public CppSysDev() {
        super(CppKeuze.CPPSYSDEF.getNaam());
        setAantalModules(CppKeuze.CPPSYSDEF.getAantalModules());
    }
}

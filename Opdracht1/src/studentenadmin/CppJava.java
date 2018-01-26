package studentenadmin;

/**
 * Specificeert de gegevens van de professional program Java
 */
public class CppJava extends Cpp {

    public CppJava(){
        super(CppKeuze.CPPJAVA.getNaam());
        setAantalModules(CppKeuze.CPPJAVA.getAantalModules());

    }

}

package studentenadmin;

/**
 * Created by Danyou on 31.12.17.
 */
public enum CppKeuze {
    /**
     * Specificaties voor het professional program java.
     */
    CPPJAVA("CPP Java", 6),
    /**
     * Specificaties voor het professional program software architect.
     */
    CPPSOFTARCHITECT("CPP Softwarearchitect", 4),
    /**
     * Specificaties voor het professional program system ontwikkelaar.
     */
    CPPSYSDEF("CPP System Ontwikkelaar", 3);

    private String naam;
    private int aantalModules;

    /**
     * Default constructor.
     *
     * @param naam naam van het professional program
     * @param aantalModules het aantal modules die nodig is voor het halen van het certificaat.
     */
    private CppKeuze(String naam, int aantalModules){

        this.naam = naam;
        this.aantalModules = aantalModules;

    }

    public String getNaam(){

        return naam;

    }

    public int getAantalModules(){

        return aantalModules;

    }
}

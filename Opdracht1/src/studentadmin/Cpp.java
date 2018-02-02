package studentadmin;

/**
 * Beheert professional program gerelateerde informatie
 */
class Cpp extends Programma {

    private int aantalModules = 0;


    /**
     * Default constructor.
     **
     * @param naam De naam van het professional program.
     *
     * @param aantalModules Het aantal modules die behaalt moeten worden om voor het programma te slagen.
     */
     Cpp(String naam, int aantalModules){

        super(naam);
        this.aantalModules = aantalModules;

    }


    int getAantalModules() {

        return aantalModules;
    }


    @Override
    public String toString() {
        return "Cpp:" + " naam='" + getNaam()+ '\n' + "aantal modules=" + aantalModules + '\n';
    }
}


package studentenadmin;

/**
 * Beheert professional program gerelateerde informatie
 */
public class Cpp extends Programma {

    private int aantalModules = 0;


    /**
     * Default constructor.
     *
     * Opmerking: De cpp constructor moet met een formele parameter double aangeroepen worden, anders kan de StudentenAdmin
     * klasse geen nieuw Cpp toevoegen. De methode voor het toevoegen van een nieuw programma genereert een constructor klasse
     * en die verwacht voor elk nieuw programma een string parameter en een double parameter.
     *
     * @param naam De naam van het professional program.
     *
     * @param aantalModules Het aantal modules die behaalt moeten worden om voor het programma te slagen.
     */
    public Cpp(String naam, double aantalModules){

        super(naam);
        this.aantalModules = (int)aantalModules;

    }


    public int getAantalModules() {

        return aantalModules;
    }


    @Override
    public String toString() {
        return "Cpp:" + " naam='" + getNaam()+ '\n' + "aantal modules=" + aantalModules + '\n';
    }
}


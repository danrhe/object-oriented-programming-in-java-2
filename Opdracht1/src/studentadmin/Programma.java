package studentadmin;

/**
 * Representeert de overlappende eigenschappen van verschillende onderwijs programma's
 */
abstract class Programma {

    private String naam = null;


    /**
     * Default constructor
     *
     * @param naam De naam van een studie programma
     */
    Programma(String naam){

        this.naam = naam;
    }

    String getNaam() {

        return naam;
    }

    @Override
    public String toString() {

        return "Programma{" + "naam='" + naam + '\'' + '}';
    }
}

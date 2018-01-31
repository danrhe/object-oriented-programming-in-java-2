package studentenadmin;

/**
 * Representeert de overlappende eigenschappen van verschillende onderwijs programma's
 */
public abstract class Programma {

    private String naam = null;


    /**
     * Default constructor
     *
     * @param naam De naam van een studie programma
     */
    public Programma(String naam) throws StudentenAdminException{

        if (naam == null){
           throw new StudentenAdminException("Naam mag niet onbekend zijn");
        }

        this.naam = naam;
    }

    public String getNaam() {

        return naam;
    }

    @Override
    public String toString() {
        return "Programma{" + "naam='" + naam + '\'' + '}';
    }
}

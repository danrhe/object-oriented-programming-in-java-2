package studentenadmin;

/**
 * Beheert opleidingsgerelateerde informatie.
 */
public class Opleiding extends Programma {

    private double aantalStudiepunten = 0;

    /**
     * Default constructor.
     *
     * @param naam Naam van de opleiding.
     * @param aantalStudiepunten Her aantal studiepunten die nodig is om te slagen voor de opleiding.
     */
    public Opleiding(String naam, double aantalStudiepunten){
        super(naam);
        this.aantalStudiepunten = aantalStudiepunten;
    }


    public double getAantalStudiepunten() {

        return aantalStudiepunten;
    }


    @Override
    public String toString() {
        return "Opleiding:" + " naam='" + getNaam()+ '\n' + "aantal studiepunten=" + aantalStudiepunten + '\n';
    }
}

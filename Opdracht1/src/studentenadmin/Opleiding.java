package studentenadmin;

/**
 * Beheert opleidingsgerelateerde informatie
 */
public class Opleiding {
    private String naam = null;

    private double aantalStudiepunten = 0;

    /**
     * Default constructor.
     *
     * @param naam
     */
    public Opleiding(String naam){

        this.naam = naam;
    }

    public String getNaam() {

        return naam;
    }

    public void setNaam(String naam) {

        this.naam = naam;
    }

    public double getAantalStudiepunten() {

        return aantalStudiepunten;
    }

    public void setAantalStudiepunten(double aantalStudiepunten) {

        this.aantalStudiepunten = aantalStudiepunten;
    }
}

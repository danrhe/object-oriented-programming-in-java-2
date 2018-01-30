package restaurant;

/**
 * Representeert een maaltijd in het restaurant
 */
public class Maaltijd {
    private String omschrijving = null;
    private int tafelnr = -1;


    /**
     * Default constructor.
     * @param omschrijving
     * @param tafelnr
     */
    public Maaltijd(String omschrijving, int tafelnr) {
        this.omschrijving = omschrijving;
        this.tafelnr = tafelnr;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public int getTafelnr() {
        return tafelnr;
    }

    @Override
    public String toString() {
        return omschrijving + " voor tafel nummer " + tafelnr;
    }
}

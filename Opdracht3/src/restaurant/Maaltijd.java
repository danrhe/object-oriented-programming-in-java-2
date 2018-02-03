package restaurant;

/**
 * Representeert een maaltijd in het restaurant
 */
class Maaltijd {
    private String omschrijving = null;
    private int tafelnr = -1;


    /**
     * Default constructor.
     *
     * @param omschrijving Omschrijving van wat de maaltijd bevat.
     *
     * @param tafelnr Tafelnummer van het gerecht.
     */
     Maaltijd(String omschrijving, int tafelnr) {
        this.omschrijving = omschrijving;
        this.tafelnr = tafelnr;
    }

    String getOmschrijving() {

         return omschrijving;
    }

    int getTafelnr() {

         return tafelnr;
    }

    @Override
    public String toString() {

         return omschrijving + " voor tafel nummer " + tafelnr;
    }
}

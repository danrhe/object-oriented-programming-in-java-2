package studentenadmin;

/**
 * Beheert de gegevens van een regulier ingeschreven student.
 */
public class Regulier extends Student{

    private Opleiding opleiding = null;
    private double behaaldeStudiepunten = 0.0;

    /**
     * Default constructor.
     *
     * @param naam naam van de student.
     * @param opleiding opleiding die de student volgt.
     */
    public Regulier(String naam, Opleiding opleiding) {
        super(naam);
        this.opleiding = opleiding;

    }

    public double getBehaaldeStudiepunten() {
        return behaaldeStudiepunten;
    }

    public void setBehaaldeStudiepunten(double behaaldeStudiepunten) {

        if (behaaldeStudiepunten <= opleiding.getAantalStudiepunten()) {
            this.behaaldeStudiepunten = behaaldeStudiepunten;
        }
    }


    @Override
    public String toString() {
        String studieresultaat = "niet geslaagd";

        if (behaaldeStudiepunten >= opleiding.getAantalStudiepunten()){
            studieresultaat = "geslaagd";
        }

        return getNaam() + ", " + opleiding.getNaam() + ", " + behaaldeStudiepunten
        + " studiepunten, " + studieresultaat + "\n";
    }
}

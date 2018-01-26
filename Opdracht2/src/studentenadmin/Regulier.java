package studentenadmin;

/**
 * Beheert de gegevens van een regulier ingeschreven student.
 */
public class Regulier extends Student{

    private double behaaldeStudiepunten = 0.0;

    /**
     * Default constructor.
     *
     * @param naam naam van de student.
     *
     * @param opleiding opleiding die de student volgt.
     *
     * @throws StudentenAdminException Wordt gegooid als programma geen instantie van de subklasse opleiding is
     *
     */
    public Regulier(String naam, Programma opleiding) throws StudentenAdminException{
        super(naam, opleiding);

        if (!(opleiding instanceof Opleiding)){

            throw new StudentenAdminException("Reguliere studenten mogen alleen met opleidingen gekoppeld worden");
        }
    }

    public double getBehaaldeStudiepunten() {

        return behaaldeStudiepunten;
    }

    /**
     * Past het aantaal behaalde studiepunten aan. mits het aantal studiepunten niet groter is dan het aantal
     * studiepunten van de opleiding.
     *
     * @param behaaldeStudiepunten Het nieuwe aantal behaalde studiepunten.
     *
     * @throws StudentenAdminException Wordt gegooid als het aantal behaalde studiepunten groter wordt dan aantal studiepunten
     * van de opleiding.
     */
    public void verhoogBehaaldeStudiepunten(double behaaldeStudiepunten) throws StudentenAdminException{

        Opleiding opleiding = (Opleiding)getProgramma();

        if (this.behaaldeStudiepunten + behaaldeStudiepunten > opleiding.getAantalStudiepunten()) {

            throw new StudentenAdminException("Aantal behaalde studiepunten is groter dan het aantal studiepunten van de opleiding");

        }

        this.behaaldeStudiepunten = this.behaaldeStudiepunten + behaaldeStudiepunten;

    }



    /**
     * Stelt vast of student geslaagd is voor de opleiding.
     *
     * @return Status van de opleiding.
     */
    public boolean isGeslaagd(){

        Opleiding opleiding = (Opleiding)getProgramma();

        return behaaldeStudiepunten >= opleiding.getAantalStudiepunten();
    }


    /**
     * Geeft alle scholer gerelateerde info weer.
     *
     * @return Informatie over de scholer
     */
    public String getStudentInfo(){

        String status = "geslaagd";
        if (!isGeslaagd()){

            status = "niet " + status;

        }

        Opleiding opleiding = (Opleiding)getProgramma();

        return getNaam() + ", " + opleiding.getNaam() + ", " + behaaldeStudiepunten + " studiepunten, " + status + "\n";

    }



    @Override
    public String toString() {

        Opleiding opleiding = (Opleiding)getProgramma();

        return getNaam() + ", " + opleiding.getNaam() + ", " + behaaldeStudiepunten + " studiepunten\n";
    }
}

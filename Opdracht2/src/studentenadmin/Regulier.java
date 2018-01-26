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
     * @param opleiding opleiding die de student volgt.
     */
    public Regulier(String naam, Programma opleiding) throws StudentenAdminException{
        super(naam, opleiding);
        if (!opleiding.getClass().getName().equals("studentenadmin.Opleiding")){
            throw new StudentenAdminException("Reguliere studenten mogen alleen met opleidingen gekoppeld worden");
        }
    }

    public double getBehaaldeStudiepunten() {

        return behaaldeStudiepunten;
    }

    /**
     * Past het aantaal behaalde studiepunten aan mits het aantal studiepunten niet groter is dan het aantal
     * studiepunten van de opleiding.
     *
     * @param behaaldeStudiepunten Het nieuwe aantal behaalde studiepunten.
     */
    public boolean verhoogBehaaldProgrammaOnderdeel(double behaaldeStudiepunten) {

        Opleiding opleiding = (Opleiding)getProgramma();

        if (this.behaaldeStudiepunten + behaaldeStudiepunten <= opleiding.getAantalStudiepunten()) {
            this.behaaldeStudiepunten = this.behaaldeStudiepunten + behaaldeStudiepunten;
            return true;
        }
        return false;

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

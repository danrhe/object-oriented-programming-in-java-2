package studentadmin;

/**
 * Beheert de gegevens van een regulier ingeschreven student.
 */
class Regulier extends Student{

    private double behaaldeStudiepunten = 0.0;

    /**
     * Default constructor.
     *
     * @param naam naam van de student.
     *
     * @param opleiding opleiding die de student volgt.
     *
     * @throws StudentAdminException Wordt gegooid als programma geen instantie van de subklasse opleiding is
     *
     */
    Regulier(String naam, Programma opleiding) throws StudentAdminException {
        super(naam, opleiding);

        if (!(opleiding instanceof Opleiding)){

            throw new StudentAdminException("Reguliere studenten mogen alleen met opleidingen gekoppeld worden");
        }
    }


    /**
     * Past het aantaal behaalde studiepunten aan. mits het aantal studiepunten niet groter is dan het aantal
     * studiepunten van de opleiding.
     *
     * @param behaaldeStudiepunten Het nieuwe aantal behaalde studiepunten.
     *
     * @throws StudentAdminException Wordt gegooid als het aantal behaalde studiepunten groter wordt dan aantal studiepunten
     * van de opleiding.
     */
    void verhoogBehaaldeStudiepunten(double behaaldeStudiepunten) throws StudentAdminException {

        Opleiding opleiding = (Opleiding)getProgramma();

        if (this.behaaldeStudiepunten + behaaldeStudiepunten > opleiding.getAantalStudiepunten()) {

            throw new StudentAdminException("Aantal behaalde studiepunten is groter dan het aantal studiepunten van de opleiding");

        }

        if (this.behaaldeStudiepunten + behaaldeStudiepunten < 0) {

            throw new StudentAdminException("Aantal behaalde studiepunten is kleiner dan 0");

        }

        this.behaaldeStudiepunten = this.behaaldeStudiepunten + behaaldeStudiepunten;

    }



    /**
     * Stelt vast of student geslaagd is voor de opleiding.
     *
     * @return Status van de opleiding.
     */
    boolean isGeslaagd(){

        Opleiding opleiding = (Opleiding)getProgramma();

        return behaaldeStudiepunten >= opleiding.getAantalStudiepunten();
    }


    /**
     * Geeft alle scholer gerelateerde info weer.
     *
     * @return Informatie over de scholer
     */
    String getStudentInfo(){

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

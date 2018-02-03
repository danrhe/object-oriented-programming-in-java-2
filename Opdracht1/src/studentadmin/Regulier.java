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
     */
    Regulier(String naam, Programma opleiding) {
        super(naam, opleiding);
    }


    /**
     * Past het aantaal behaalde studiepunten aan, mits het aantal studiepunten niet groter is dan het aantal
     * studiepunten van de opleiding of kleiner dan 0.
     *
     * @param behaaldeStudiepunten Het nieuwe aantal behaalde studiepunten.
     */
    void verhoogBehaaldeStudiepunten(double behaaldeStudiepunten) {

        Opleiding opleiding = (Opleiding)getProgramma();

        boolean teVeel = this.behaaldeStudiepunten + behaaldeStudiepunten > opleiding.getAantalStudiepunten();

        boolean kleinerNull = this.behaaldeStudiepunten + behaaldeStudiepunten < 0;

        if (!(kleinerNull) && !(teVeel)){
            this.behaaldeStudiepunten = this.behaaldeStudiepunten + behaaldeStudiepunten;
        }
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

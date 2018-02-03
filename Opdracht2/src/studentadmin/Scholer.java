package studentadmin;

/**
 * Beheert de gegevens van een scholer.
 */
class Scholer extends Student{

    private int behaaldeModules = 0;

    /**
     * Default constructor.
     *
     * @param naam Naam van de student.
     *
     * @param cpp Professional program.
     *
     * @throws StudentAdminException Wordt gegooid als programma geen instantie van de subklasse Cpp is
     */
    Scholer (String naam, Programma cpp) throws StudentAdminException {
        super(naam, cpp);
        if (!(cpp instanceof Cpp)){
            throw new StudentAdminException("Scholers mogen alleen met Cpp\'s gekoppeld worden");
        }
    }


    /**
     * Verhoogt het aantal behaalde modules van de scholer met een mits dit getal niet groter is dan het aantal modules
     * van het cpp programma.
     *
     * @throws StudentAdminException Wordt gegooid als het behaaldeModules groter wordt dan aantal modules van CPP of kleiner dan 0.
     */
    void verhoogBehaaldeModulesMetEen() throws StudentAdminException {

        Cpp cpp = (Cpp)getProgramma();

        if(this.behaaldeModules + 1  > cpp.getAantalModules()) {

            throw new StudentAdminException("Aantal behaalde modules is groter dan deze CPP heeft");

        }

        if (this.behaaldeModules + 1 < 0) {

            throw new StudentAdminException("Aantal behaalde modules is kleiner dan 0");

        }


        this.behaaldeModules += 1;

    }


    /**
     * Bepaalt de status van het CPP voor een scholier.
     *
     * @return De status.
     */
    boolean isGeslaagd(){
        Cpp cpp = (Cpp)getProgramma();
        return behaaldeModules >= cpp.getAantalModules();
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
        Cpp cpp = (Cpp)getProgramma();

        return getNaam() + ", " + cpp.getNaam() + ", " + behaaldeModules + " behaalde modules, " + status + "\n";

    }

    @Override
    public String toString() {

        return "Regulier: \nNaam: " + getNaam() + "\nbehaalde modules: " +  behaaldeModules + "Programma: " + getProgramma();
    }
}

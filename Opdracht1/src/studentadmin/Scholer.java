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
     */
    Scholer (String naam, Programma cpp) {
        super(naam, cpp);
    }


    /**
     * Verhoogt het aantal behaalde modules van de scholer met een, mits dit getal niet groter is dan het aantal modules
     * van het cpp programma.
     */
    void verhoogBehaaldeModulesMetEen() {

        Cpp cpp = (Cpp)getProgramma();

        if (!(this.behaaldeModules + 1  > cpp.getAantalModules())){
            this.behaaldeModules += behaaldeModules;
        }
    }


    /**
     * Bepaalt de status van het CPP voor een scholer.
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

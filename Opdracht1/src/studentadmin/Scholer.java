package studentadmin;

/**
 * Beheert de gegevens van een scholer.
 */
public class Scholer extends Student{

    private int behaaldeModules = 0;

    /**
     * Default constructor.
     *
     * @param naam Naam van de student.
     *
     * @param cpp Professional program.
     */
    public Scholer (String naam, Programma cpp) {
        super(naam, cpp);
    }


    /**
     * Past het aantal behaalde modules van de scholer aan mits dit getal niet groter is dan het aantal modules van het
     * cpp programma of kleiner dan 0.
     *
     * @param behaaldeModules Het nieuwe aantal behaalde modules.
     */
    public void verhoogBehaaldeModules(int behaaldeModules) {

        Cpp cpp = (Cpp)getProgramma();

        boolean teVeel = this.behaaldeModules + behaaldeModules  > cpp.getAantalModules();

        boolean kleinerNull = this.behaaldeModules + behaaldeModules < 0;

        if (!(kleinerNull) && !(teVeel)){
            this.behaaldeModules += behaaldeModules;
        }

    }


    /**
     * Bepaalt de status van het CPP voor een scholier.
     *
     * @return De status.
     */
    public boolean isGeslaagd(){
        Cpp cpp = (Cpp)getProgramma();
        return behaaldeModules >= cpp.getAantalModules();
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
        Cpp cpp = (Cpp)getProgramma();

        return getNaam() + ", " + cpp.getNaam() + ", " + behaaldeModules + " behaalde modules, " + status + "\n";

    }

    @Override
    public String toString() {

        return "Regulier: \nNaam: " + getNaam() + "\nbehaalde modules: " +  behaaldeModules + "Programma: " + getProgramma();
    }
}

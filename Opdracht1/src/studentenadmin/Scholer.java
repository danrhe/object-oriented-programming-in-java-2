package studentenadmin;

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
     *
     */
    public Scholer (String naam, Cpp cpp){

        super(naam, cpp);
    }

    public int getBehaaldeModules() {

        return behaaldeModules;
    }

    /**
     * Past het aantal behaalde modules van de scholer aan mits dit getal niet groter is dan het aantal modules van het
     * cpp programma.
     *
     * @param behaaldeModules Het nieuwe aantal behaalde modules.
     *
     * @return Success van de operatie.
     */
    public boolean verhoogBehaaldProgrammaOnderdeel(double behaaldeModules) {

        Cpp cpp = (Cpp)getProgramma();

        if(this.behaaldeModules + (int)behaaldeModules  <= cpp.getAantalModules()) {
            this.behaaldeModules += (int) behaaldeModules;
            return true;
        }
        return false;
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

        Cpp cpp = (Cpp)getProgramma();
        return "Regulier: \nNaam: " + getNaam() + "\nbehaalde modules: " +  behaaldeModules + "Programma: " + getProgramma();
    }
}

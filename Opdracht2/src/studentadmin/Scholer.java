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
     *
     * @throws StudentAdminException Wordt gegooid als programma geen instantie van de subklasse Cpp is
     */
    public Scholer (String naam, Programma cpp) throws StudentAdminException {
        super(naam, cpp);
        if (!(cpp instanceof Cpp)){
            throw new StudentAdminException("Scholers mogen alleen met Cpp\'s gekoppeld worden");
        }
    }


    /**
     * Past het aantal behaalde modules van de scholer aan mits dit getal niet groter is dan het aantal modules van het
     * cpp programma.
     *
     * @param behaaldeModules Het nieuwe aantal behaalde modules.
     *
     * @throws StudentAdminException Wordt gegooid als het behaaldeModules groter wordt dan aantal modules van CPP of kleiner dan 0.
     */
    public void verhoogBehaaldeModules(double behaaldeModules) throws StudentAdminException {

        Cpp cpp = (Cpp)getProgramma();

        if(this.behaaldeModules + (int)behaaldeModules  > cpp.getAantalModules()) {

            throw new StudentAdminException("Aantal behaalde modules is groter dan deze CPP heeft");

        }

        if (this.behaaldeModules + behaaldeModules < 0) {

            throw new StudentAdminException("Aantal behaalde modules is kleiner dan 0");

        }


        this.behaaldeModules += (int) behaaldeModules;

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

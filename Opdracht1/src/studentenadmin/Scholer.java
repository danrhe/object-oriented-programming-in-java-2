package studentenadmin;

/**
 * Beheert de gegevens van een scholer.
 */
public class Scholer extends Student{

    private Cpp cpp = null;
    private int behaaldeModules = 0;

    /**
     * Default constructor.
     *
     * @param naam naam van de student.
     * @param cpp professional program
     *
     */
    public Scholer (String naam, Cpp cpp){
        super(naam);
        this.cpp = cpp;
    }

    public int getBehaaldeModules() {

        return behaaldeModules;
    }


    public void setBehaaldeModules(int behaaldeModules) {
        if(behaaldeModules <= cpp.getAantalModules()) {
            this.behaaldeModules = behaaldeModules;
        }
    }


    @Override
    public String toString() {
        String cppResultaat = "niet behaald";
        if (behaaldeModules >= cpp.getAantalModules()){

            cppResultaat = "behaald";

        }
        return getNaam() + ", " + cpp.getNaam() + ", " + behaaldeModules
                + " modules, " + cppResultaat + "\n";
    }
}

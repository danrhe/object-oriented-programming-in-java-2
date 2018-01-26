package studentenadmin;

/**
 * Beheert de gegevens van een student
 */
public class Student {
    private String naam;

    /**
     * Default constructor
     * @param naam
     */
    public Student(String naam){

        this.naam = naam;
    }


    public String getNaam() {

        return naam;
    }

    public void setNaam(String naam) {

        this.naam = naam;
    }

}

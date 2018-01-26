package studentenadmin;

/**
 * Beheert professional program gerelateerde informatie
 */
public class Cpp {

    private String naam = null;

    private int aantalModules = 0;



    public Cpp(String naam){

        this.naam = naam;

    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {

        this.naam = naam;
    }

    public int getAantalModules() {
        return aantalModules;
    }

    public void setAantalModules(int aantalModules) {
        this.aantalModules = aantalModules;
    }

}

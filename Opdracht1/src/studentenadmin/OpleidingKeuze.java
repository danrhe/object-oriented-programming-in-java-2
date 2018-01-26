package studentenadmin;

/**
 * Naam en studiepuntspecificatie van alle mogelijke opleiding keuzes
 */
public enum OpleidingKeuze {
    /**
     * Specificaties voor de opleiding wiskunde
     */
    WISKUNDE("Wiskunde", 160.0),
    /**
     * Specificaties voor de opleiding informatica
     */
    INFORMATICA("Informatica", 120.0);

    private String naam;
    private double aantalStudiepunten;

    /**
     * Default constructor.
     *
     * @param naam naam van de opleiding
     * @param aantalStudiepunten het aantal studiepunten die nodig is voor het halen van het studiediploma.
     */
    private OpleidingKeuze(String naam, double aantalStudiepunten){

        this.naam = naam;
        this.aantalStudiepunten = aantalStudiepunten;

    }

    public String getNaam(){

        return naam;

    };
    public double getAantalStudiepunten(){

        return aantalStudiepunten;

    };
}

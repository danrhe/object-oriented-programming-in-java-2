package studentenadmin;

/**
 * Specificeert de gegevens van de opleiding wiskunde
 */
public class Wiskunde extends Opleiding {

    public Wiskunde(){

        super(OpleidingKeuze.WISKUNDE.getNaam());
        setAantalStudiepunten(OpleidingKeuze.WISKUNDE.getAantalStudiepunten());

    }

}

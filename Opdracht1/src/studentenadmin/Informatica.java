package studentenadmin;

/**
 * Specificeert de gegevens van de opleiding informatica
 */
public class Informatica  extends Opleiding {

    public Informatica(){

        super(OpleidingKeuze.INFORMATICA.getNaam());
        setAantalStudiepunten(OpleidingKeuze.INFORMATICA.getAantalStudiepunten());

    }

}

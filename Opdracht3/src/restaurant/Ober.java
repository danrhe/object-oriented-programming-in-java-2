package restaurant;
import restaurant.UitgifteBalie;

import static java.lang.Thread.sleep;

/**
 * Vertegenwoordigt de ober die maaltijden naar de tafels brengt.
 */
public class Ober implements Runnable {

    /**
     * Tijd die nodig is om naar de tafel te lopen.
     */
    private final int LOOPTIJD = 1000;

    /**
     * Tijd die de ober nodig heeft om een maaltijd te serveren.
     */
    private final int SERVEERTIJD = 1500;

    /**
     * Tijd die de ober wacht voordat die weer kijkt of een maaltijd klaar is.
     */
    private final int WACHTTIJD = 1000;

    private String naam = null;
    private UitgifteBalie uitgiftebalie = null;

    /**
     * Default constructor.
     *
     * @param naam Naam van de ober.
     *
     * @param uitgiftebalie De uitgiftebalie waar de ober het eten ophaalt.
     */
    public Ober(String naam, UitgifteBalie uitgiftebalie) {

        this.naam = naam;
        this.uitgiftebalie = uitgiftebalie;

    }


    /**
     * De serverprocedure.
     *
     * @param maaltijd Maaltijd die de ober serveert.
     *
     * @throws InterruptedException
     */
    private void serveer(Maaltijd maaltijd) throws InterruptedException{

        System.out.println( "Ober " + naam +  " loopt met " + maaltijd.getOmschrijving() + " naar tafel " + maaltijd.getTafelnr());
        sleep(maaltijd.getTafelnr() * LOOPTIJD);

        System.out.println( "Ober " + naam +  " serveert " + maaltijd.toString());
        sleep(SERVEERTIJD);

        System.out.println( "Ober " + naam +  " loopt terug naar de uitgiftebalie");
        sleep(maaltijd.getTafelnr() * LOOPTIJD);
    }


    /**
     * Routine die het starten van een hulpdraad doorloopt.
     */
    public void run() {
        long nu = System.currentTimeMillis();
        long eindtijd = System.currentTimeMillis() + Restaurant.SIMULATIETIJD;

        while (nu <= eindtijd) {
            try {

                Maaltijd maaltijd = uitgiftebalie.pakMaaltijd();
                if (maaltijd != null){

                    serveer(maaltijd);

                } else {

                    sleep(WACHTTIJD);
                }

            } catch (InterruptedException interrupted) {

            }

        }
        System.out.println("Ober " + naam + " is klaar vandaag.");
    }
}




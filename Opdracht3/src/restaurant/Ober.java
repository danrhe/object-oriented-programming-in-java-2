package restaurant;
import restaurant.UitgifteBalie;

import static java.lang.Thread.sleep;

/**
 * Vertegenwoordigt de ober die maaltijden naar de tafels brengt.
 */
class Ober implements Runnable {

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

    private volatile boolean stoppen = false;

    /**
     * Default constructor.
     *
     * @param naam Naam van de ober.
     *
     * @param uitgiftebalie De uitgiftebalie waar de ober het eten ophaalt.
     */
    Ober(String naam, UitgifteBalie uitgiftebalie) {

        this.naam = naam;
        this.uitgiftebalie = uitgiftebalie;

    }


    /**
     * Simuleert de serveerprocedure.
     *
     * @param maaltijd Maaltijd die de ober serveert.
     *
     * @throws InterruptedException Wordt gegooid als draad onverwacht onderbroken wordt.
     */
    private void serveer(Maaltijd maaltijd) throws InterruptedException{

        System.out.println( "Ober " + naam +  " loopt met " + maaltijd.getOmschrijving() + " naar tafel " + maaltijd.getTafelnr());
        sleep(maaltijd.getTafelnr() * LOOPTIJD);

        System.out.println( "Ober " + naam +  " serveert " + maaltijd.toString());
        sleep(SERVEERTIJD);

        System.out.println( "Ober " + naam +  " loopt terug naar de uitgiftebalie");
        sleep(maaltijd.getTafelnr() * LOOPTIJD);
    }

    void stopOber(){
        stoppen = true;
    }


    /**
     * Routine die bij het starten van een hulpdraad doorlopen wordt.
     */
    public void run() {

        stoppen = false;

        while (!stoppen) {
            try {

                Maaltijd maaltijd = uitgiftebalie.pakMaaltijd();
                if (maaltijd != null){

                    serveer(maaltijd);

                } else {

                    sleep(WACHTTIJD);
                }

            } catch (InterruptedException interrupted) {
                System.out.println("Draad werd onverwacht onderbroken");
            }

        }
        System.out.println("Ober " + naam + " is klaar voor vandaag.");
    }
}




package restaurant;

import java.util.*;


/**
 * Klasse die verantwoordelijk is voor het bereiden van maaltijden
 */
class Kok extends Thread{

    private static final int BEREIDINGSTIJD = 4000;


    /**
     * Lijst met gerechten die elk kok kan koken.
     */
    private static final List<String> omschrijvingen = Arrays.asList(
            "Pizza Tonno",
            "Pizza Hawaii",
            "Lasagna",
            "Spaghetti Funghi",
            "Insalata caprese",
            "Gehaktballen",
            "Frietje oorlog",
            "Uitsmijter",
            "Tosti");

    /**
     * Naam van de kok.
     */
    private String naam = null;

    private UitgifteBalie uitgifteBalie = null;

    private volatile boolean stoppen = false;

    /**
     * Constructor van kok.
     *
     * @param naam Naam van de kok.
     * @param uitgifteBalie De balie waar de kok het eten naar toe brengt om het verder te verdelen.
     */
    Kok(String naam, UitgifteBalie uitgifteBalie) {
        this.naam = naam;
        this.uitgifteBalie = uitgifteBalie;
    }


    /**
     * Keuze voor gerecht een tafel en bereiding van de maaltijd
     *
     * @throws InterruptedException Wordt gegooid als draad onverwachts beeindigd wordt door applicatie.
     */
    private void kook() throws InterruptedException{

        Maaltijd maaltijd = new Maaltijd(kiesOmschrijving(), kiesTafel());
        System.out.println( "Kok " + naam +  " begint met koken van " + maaltijd.toString());
        sleep(BEREIDINGSTIJD);

        uitgifteBalie.plaatsMaaltijd(maaltijd);
        System.out.println( "Kok " + naam +  " geeft " + maaltijd.toString() + " aan uitgiftebalie" );

    }

    /**
     * Random keuze van een tafel nummer.
     *
     * @return Het tafelnummer.
     */
    private int kiesTafel(){

        Random random = new Random();
        return random.nextInt(Restaurant.AANTALTAFELS) + 1;
    }

    /**
     * Random keuze van een gerecht.
     *
     * @return De omschrijving van het gerecht.
     */
    private String kiesOmschrijving(){
        Random random = new Random();
        int index = random.nextInt(omschrijvingen.size()-1) ;


        return omschrijvingen.get(index);
    }


    void stopKok(){
        stoppen = true;
    }

    @Override
    public void run() {

        stoppen = false;
        while (!stoppen) {
            try {
                kook();
            } catch (InterruptedException interrupted) {
                System.out.println("Draad werd onverwacht onderbroken");
            }
        }

        System.out.println("Kok " + naam + " is klaar voor vandaag.");
    }

}


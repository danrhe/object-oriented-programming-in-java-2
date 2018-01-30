package restaurant;

import java.util.*;


/**
 * Klasse die verantwoordelijk is voor het bereiden van maaltijden
 */
public class Kok extends Thread{

    public static final int BEREIDINGSTIJD = 4000;


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
    String naam = null;
    UitgifteBalie uitgifteBalie = null;

    /**
     *
     * @param naam Naam van de kok.
     * @param uitgifteBalie De balie waar de kok het eten naar toe brengt om het verder te verdelen.
     */
    public Kok(String naam, UitgifteBalie uitgifteBalie) {
        this.naam = naam;
        this.uitgifteBalie = uitgifteBalie;
    }

    public String getNaam() {
        return naam;
    }

    /**
     * Keuze voor gerecht een tafel en bereiding van de maaltijd
     * @throws InterruptedException
     */
    public void kook() throws InterruptedException{

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
        int tafelNr = random.nextInt(Restaurant.AANTALTAFELS) + 1;

        return tafelNr;
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


    @Override
    public void run() {
        long nu = System.currentTimeMillis();
        long eindtijd = System.currentTimeMillis() + Restaurant.SIMULATIETIJD;

        while (nu <= eindtijd) {
            try {

                nu = System.currentTimeMillis();
                kook();

            } catch (InterruptedException interrupted){

            }

        }

        System.out.println("Kok " + naam + " is klaar vandaag.");
    }
}

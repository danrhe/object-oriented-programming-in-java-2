package restaurant;

/**
 * Restaurant simulatie klasse die als verantwoordelijkheid heeft koks en obers aan te sturen.
 */
public class Restaurant {


    /**
     * Het aantal tafels van het restaurant.
     */
    static final int AANTALTAFELS = 10;

    /**
     * De lengte van de simulatie.
     */
    static final int SIMULATIETIJD = 12000;


    /**
     * De plek waar maaltijden naar toe worden gebracht en op worden gehaald
     */
    private UitgifteBalie uitgifteBalie = null;

    /**
     * De default constructor.
     */
    private Restaurant() {
        uitgifteBalie = new UitgifteBalie();

    }


    /**
     * Start de hoofddraad van de simulatie.
     * @param args mogelijke parameter voor applicatie start
     */
    public static void main(String[] args){
        Restaurant restaurant = new Restaurant();
        Kok kok1 = new Kok("Hugo", restaurant.uitgifteBalie);
        Kok kok2 = new Kok("Bert", restaurant.uitgifteBalie);
        Ober ober1 = new Ober("Hans",restaurant.uitgifteBalie);
        Ober ober2 = new Ober("Markus",restaurant.uitgifteBalie);
        Ober ober3 = new Ober("Geert",restaurant.uitgifteBalie);
        Thread hulpdraadOber1 = new Thread(ober1);
        Thread hulpdraadOber2 = new Thread(ober2);
        Thread hulpdraadOber3 = new Thread(ober3);

        long nu = System.currentTimeMillis();
        long eindtijd = System.currentTimeMillis() + SIMULATIETIJD;

        kok1.start();
        kok2.start();

        hulpdraadOber1.start();
        hulpdraadOber2.start();
        hulpdraadOber3.start();


        while (nu <= eindtijd) {
            try {
                Thread.sleep(1000);
                nu = nu + 1000;

            } catch (InterruptedException e){

                System.out.println ("Hoofddraad is onverwacht beeindigd.");
            }
        }
        kok1.stopKok();
        kok2.stopKok();

        ober1.stopOber();
        ober2.stopOber();
        ober3.stopOber();


        System.out.println("Het restaurant gaat sluiten.");


    }

}

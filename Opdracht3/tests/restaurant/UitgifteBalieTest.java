package restaurant;

import org.junit.Before;
import org.junit.Test;
import restaurant.Maaltijd;
import restaurant.UitgifteBalie;

/**
 * Created by Danyou on 28.01.18.
 */
public class UitgifteBalieTest {

    Maaltijd maaltijd1 = null;
    Maaltijd maaltijd2 = null;
    UitgifteBalie uitgifteBalie = new UitgifteBalie();

    @Before public void setup(){


        maaltijd1 = new Maaltijd("Pizza", 1);
        maaltijd2 = new Maaltijd("Soep", 2);
        uitgifteBalie.plaatsMaaltijd(maaltijd1);
        uitgifteBalie.plaatsMaaltijd(maaltijd2);

    }


    @Test
    public void testPakMaaltijd(){

        Maaltijd m1 = uitgifteBalie.pakMaaltijd();

        System.out.println(m1);
        Maaltijd m2 = uitgifteBalie.pakMaaltijd();
        System.out.println(m2);


    }

}
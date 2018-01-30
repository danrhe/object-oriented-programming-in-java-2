package restaurant;

import restaurant.Maaltijd;

import java.util.ArrayList;

/**
 * De plek waar binnen de simulatie maaltijden neer worden gezet en op worden gehaalt.
 */
public class UitgifteBalie {

    private ArrayList<Maaltijd> maaltijden = new ArrayList<Maaltijd>();


    /**
     * Default constructor.
     */
    public UitgifteBalie(){}

    /**
     * Haalt de maaltijd die relatief het langst bij de balie staat op.
     * N.B. Gezien ArrayList natief niet atomair is moet deze gedeelte functie gesyncroniseerd worden.
     *
     * @return De maaltijd met de index 0 of null.
     */
    public synchronized Maaltijd pakMaaltijd(){

        if (maaltijden.size() > 0){
            Maaltijd maaltijd = maaltijden.get(0);
            maaltijden.remove(0);
            return maaltijd;
        }
        return null;
    }


    /**
     * Plaats een nieuwe maaltijd bij de balie.
     * N.B. Gezien ArrayList natief niet atomair is moet deze gedeelte functie gesyncroniseerd worden.
     */
    public synchronized void plaatsMaaltijd(Maaltijd maaltijd){

        maaltijden.add(maaltijd);

    }
}

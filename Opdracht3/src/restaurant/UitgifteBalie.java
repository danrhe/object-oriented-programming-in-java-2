package restaurant;

import java.util.ArrayList;

/**
 * De plek waar binnen de simulatie maaltijden neer worden gezet en op worden gehaalt.
 */
class UitgifteBalie {

    private ArrayList<Maaltijd> maaltijden = new ArrayList<>();


    /**
     * Default constructor.
     */
    UitgifteBalie(){}

    /**
     * Haalt de maaltijd op die relatief het langst bij de balie staat.
     *
     * N.B. Gezien ArrayList natief niet atomair is moet deze gedeelte functie gesyncroniseerd worden.
     *
     * @return De maaltijd met de index 0 of null.
     */
    synchronized Maaltijd pakMaaltijd(){

        if (maaltijden.size() > 0){
            Maaltijd maaltijd = maaltijden.get(0);
            maaltijden.remove(0);
            return maaltijd;
        }
        return null;
    }


    /**
     * Plaats een nieuwe maaltijd bij de balie.
     *
     * N.B. Gezien ArrayList natief niet atomair is moet deze gedeelte functie gesyncroniseerd worden.
     */
    synchronized void plaatsMaaltijd(Maaltijd maaltijd){

        maaltijden.add(maaltijd);

    }
}

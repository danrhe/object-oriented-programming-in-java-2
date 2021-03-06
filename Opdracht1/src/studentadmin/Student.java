package studentadmin;

/**
 * Beheert de gegevens van een student.
 */
abstract class Student {

    private String naam = null;
    private Programma programma = null;

    /**
     * Default constructor.
     *
     * @param naam Naam van een student.
     *
     * @param programma Het programma waarvoor de student ingeschreven staat.
     */
    Student(String naam, Programma programma) {

        String opgeschoondeString = schoonStringOp(naam);

        if (stringIsMinimaalTweeLetters(opgeschoondeString)){
            this.naam = opgeschoondeString;
            this.programma = programma;
        }

    }

    /**
     * Check van de status van het studie programma van een student.
     *
     * @return Status van het studie programma van een student.
     */
    abstract boolean isGeslaagd();

    /**
     * Geeft de gegevens van een student weer inclusief studiestatus.
     *
     * @return Status van het studie programma van een student.
     */
    abstract String getStudentInfo() ;


    String getNaam() {

        return naam;
    }


    Programma getProgramma() {

        return programma;
    }



    @Override
    public String toString() {
        return "Student{" +
                "naam='" + naam + '\'' +
                ", programma=" + programma +
                '}';
    }

    /**
     * Test of een string tenminste 2 letters lang is.
     *
     * @param string string die getoetst wordt.
     *
     * @return het resultaat van de test.
     */
    private boolean stringIsMinimaalTweeLetters(String string){
        if (string == null){
            return false;
        }
        char[] chars = string.toCharArray();

        int teller = 0;
        for (char c : chars){
            if(Character.isAlphabetic(c)){

                teller ++;
            }
        }

        return (teller >= 2);

    }
    /**
     * Verwijdert niet-alfabetische characters van een string.
     *
     * @param string string die opgeschoond wordt.
     *
     * @return de opgeschoonde String.
     */
    private String schoonStringOp(String string){
        if (string == null){
            return null;
        }
        char[] chars = string.toCharArray();
        char[] goedeChars = new char[chars.length];

        int teller = 0;
        for (char c : chars){
            if(Character.isAlphabetic(c)){

                goedeChars[teller] = c;
                teller ++;
            }
        }

        return (String.valueOf(goedeChars));

    }

}

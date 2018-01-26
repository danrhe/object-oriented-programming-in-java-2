package studentenadmin;

/**
 * Beheert de gegevens van een student.
 */
public abstract class Student {
    private String naam = null;
    private Programma programma = null;

    /**
     * Default constructor.
     *
     * @param naam Naam van een student.
     *
     * @param programma Het programma waarvoor de student ingeschreven staat.
     */
    public Student(String naam, Programma programma){

        this.naam = naam;
        this.programma = programma;
    }


    /**
     * Check van de status van het studie programma van een student.
     *
     * @return Status van het studie programma van een student.
     */
    public abstract boolean isGeslaagd();

    /**
     * Geeft de gegevens van een student weer inclusief studiestatus.
     *
     * @return Status van het studie programma van een student.
     */
    public abstract String getStudentInfo() ;


    /**
     * Methode om bij te houden dat een student kwantitatief vordering heeft gemaakt op het bijbehorende studie programma.
     *
     * @param hoeveelheid Hoeveelheid die de student vordering heeft gemaakt.
     *
     *
     * @return Succes van de operatie.
     */
    /*
    De keuze voor deze abstract methode is op het eerste gezicht misschien verrassend, in het bijzonder als je je realiseert
    dat de eenheid van scholers eigenlijk integers zijn. Maar ik heb bewust voor deze oplossing gekozen,
    omdat ik ervan uitga dat ook nieuwe, nog niet geimplementeerde subklassen van Student met andere soorten programma's
    ook kwantitatief studievoortgang kunnen boeken, die via een gui aanspreekbaar moeten zijn. Dmv deze abstracte methode
    is er al een weg uitgestipt om dit op een uniforme manier te implementeren zonder iets in de rest van het programma
    te hoeven veranderen. In de gui moet alleen de nieuwe subklasse opgenomen worden.
     */
    public abstract boolean verhoogBehaaldProgrammaOnderdeel(double hoeveelheid);


    public String getNaam() {

        return naam;
    }


    public Programma getProgramma() {

        return programma;
    }



    @Override
    public String toString() {
        return "Student{" +
                "naam='" + naam + '\'' +
                ", programma=" + programma +
                '}';
    }
}

package studentadmin;


import java.util.ArrayList;
import java.util.List;

/**
 * Beheert studentenLijst en studie programma's.
 */
public class StudentAdmin {

    /**
     * Lijst met alle studenten.
     */
    private List<Student> studentenLijst = new ArrayList<>();

    /**
     * Lijst met alle studie programmas.
     */
    private List<Programma> programmaLijst = new ArrayList<>();


    /**
     * Maakt een studentenLijst beheer applicatie aan.
     */
    public StudentAdmin(){

        try {
            voegOpleidingToe("Wiskunde", 160);
            voegOpleidingToe("Informatica", 120);
            voegCppToe("CPP Softwarearchitect", 4);
            voegCppToe( "CPP Java", 6);
            voegCppToe("CPP System Ontwikkelaar", 3);

        } catch (StudentAdminException studaminEx) {

            System.out.println(studaminEx.getMessage());
        }

    }


    /**
     * Zoekt een student op basis van zijn naam.
     *
     * @param naam Naam van de student.
     *
     * @return De eerste, op naam matchende student of null.
     *
     * @throws StudentAdminException Als student met opgegeven naam niet in de lijst met studenten staat.
     */
    private Student getBestaandeStudent(String naam) throws StudentAdminException {
        for (Student student : studentenLijst){

            if(student.getNaam().equals(naam)){

                return student;

            }
        }

        throw new StudentAdminException("Student met naam " + naam + " is niet in geregistreerd");
    }

    /**
     * Checkt of een student registreerd staat
     *
     * @param naam Naam van de student.
     *
     * @return waar als student in het systeem staat
     */
    private boolean studentBestaatAl(String naam){
        for (Student student : studentenLijst){

            if(student.getNaam().equals(naam)){
                return true;
            }
        }
        return false;
    }

    /**
     * Zoekt een bestaand studieprogramma op basis van zijn naam.
     *
     * @param naam De naam van het programma.
     *
     * @return Een gegeven studieprogramma of null.
     *
     * @throws StudentAdminException Als programm met opgegeven naam niet in lijst met programma's staat.
     */
    private Programma getBestaandProgramma(String naam) throws StudentAdminException {

        for (Programma programma : programmaLijst){

            if (programma.getNaam().equals(naam)){
                return programma;
            }
        }
        throw new StudentAdminException("Programma met naam " + naam + " is niet bekend");
    }


    /**
     * Checkt of een programma al bestaat.
     *
     * @param naam Naam van het programma.
     *
     * @return waar als programma al in het systeem staat
     */
    private boolean programmaBestaatAl(String naam){

        for (Programma programma : programmaLijst){

            if (programma.getNaam().equals(naam)){
                return true;
            }
        }
        return false;
    }


    /**
     * Voegt een nieuwe opleiding toe aan de lijst van programmaLijst.
     *
     * @param naam De nieuwe opleiding.
     *
     * @param studiepunten Het aantal studiepunten.
     *
     * @throws StudentAdminException Als programma met dezelfde naam al bestaat.
     */
    public void voegOpleidingToe(String naam, double studiepunten) throws StudentAdminException {

        if (programmaBestaatAl(naam)){
            throw new StudentAdminException("Programma met deze naam bestaat al");
        }
        programmaLijst.add(new Opleiding(naam, studiepunten));
    }

    /**
     * Voegt een nieuwe CPP toe aan de lijst van programmaLijst.
     *
     * @param naam Het nieuwe programma.
     *
     * @param modules Het aantal studiepunten.
     *
     * @throws StudentAdminException Als programma met dezelfde naam al bestaat.
     */
    public void voegCppToe(String naam, int modules) throws StudentAdminException {

        if (programmaBestaatAl(naam)){
            throw new StudentAdminException("Programma met deze naam bestaat al");
        }
        programmaLijst.add(new Cpp(naam, modules));
    }



    /**
     * Voegt een student toe aan de lijst van studentenLijst.
     *
     * @param studentNaam De naam van een nieuwe student.
     *
     * @param opleidingNaam De naam van de opleiding.
     *
     * @throws StudentAdminException Als student met de naam al geregistreerd is of het programma geen instantie van Opleiding is.
     */
    public void voegRegulierToe(String studentNaam, String opleidingNaam) throws StudentAdminException {

        if (studentBestaatAl(studentNaam)){
            throw new StudentAdminException("Student met dezelfde naam staat al geregistreerd");
        }
        studentenLijst.add(new Regulier(studentNaam, getBestaandProgramma(opleidingNaam)));
    }


    /**
     * Voegt een scholer toe aan de lijst van studentenLijst.
     *
     * @param scholerNaam De naam van een nieuwe scholer.
     *
     * @param cppNaam De naam van de cpp.
     *
     * @throws StudentAdminException Als student met de naam al geregistreerd is of het programma geen instantie van Cpp is.
     */
    public void voegScholerToe(String scholerNaam, String cppNaam) throws StudentAdminException {

        if (studentBestaatAl(scholerNaam)){
            throw new StudentAdminException("Student met dezelfde naam staat al geregistreerd");
        }

        studentenLijst.add(new Scholer(scholerNaam, getBestaandProgramma(cppNaam)));
    }


    /**
     * Verhoogt het aantal studiepunten.
     *
     * @param naam Naam van de student.
     *
     * @param behaaldePunten Aantal studiepunten die een student gehaald heeft.
     *
     * @throws StudentAdminException Wordt gegooid als student onbekend of geen reguliere student is, of het aantal
     *         behaalde punten van de student negatief of groter dan het aantal punten van de opleiding zou worden.
     */
    public void verhoogAantalStudiepunten(String naam, double behaaldePunten) throws StudentAdminException {

        Student student = getBestaandeStudent(naam);

        if (!(student instanceof Regulier)){
            throw new StudentAdminException("Alleen reguliere studenten kunnen studiepunten verhogen");
        }

        ((Regulier)student).verhoogBehaaldeStudiepunten(behaaldePunten);
    }

    /**
     * Verhoogt het aantal modules met een.
     *
     * @param naam Naam van de student.
     *
     * @throws StudentAdminException Wordt gegooid als student onbekend of geen scholer is, of het aantal
     *         behaalde punten van de student negatief of groter dan het aantal punten van het CPP zou worden.
     */
    public void verhoogAantalModulesMetEen(String naam) throws StudentAdminException {

        Student student = getBestaandeStudent(naam);

        if (!(student instanceof Scholer)){
            throw new StudentAdminException("Alleen scholers kunnen modules verhogen");
        }

        ((Scholer)student).verhoogBehaaldeModulesMetEen();

    }




    /**
     * Geeft de gegevens van een student weer.
     *
     * @param naam De naam van de student.
     *
     * @return De gegevens van een student.
     *
     * @throws StudentAdminException Als student niet bekend is.
     */
    public String getStudentInfo(String naam) throws StudentAdminException {

        Student gevondenStudent = getBestaandeStudent(naam);

        return gevondenStudent.getStudentInfo();

    }


    /**
     * Geeft alle studentengegevens weer.
     *
     * @return Alle studentengegevens.
     */
    public String getAlleStudentenInfo(){

        String result = "";

        for (Student student: studentenLijst){
            result += student.getStudentInfo();
        }

        return result;
    }

    /**
     * Zoekt de namen van alle 'CPP'-programma's.
     *
     * @return Verzameling van namen van CPP programmaLijst.
     */
    public List<String> getAlleCppNamen(){

        List<String> namenLijst = new ArrayList<>();

        for (Programma programma : programmaLijst) {

            if (programma instanceof Cpp) {
                namenLijst.add(programma.getNaam());
            }
        }

        return namenLijst;
    }

    /**
     * Zoekt de namen van alle reguliere opleidingen.
     *
     * @return Verzameling van namen van opleiding programmaLijst.
     */
    public List<String> getAlleOpleidingNamen(){

        List<String> namenLijst = new ArrayList<>();

        for (Programma programma : programmaLijst) {

            if (programma instanceof Opleiding) {

                namenLijst.add(programma.getNaam());

            }

        }
        return namenLijst;

    }

}

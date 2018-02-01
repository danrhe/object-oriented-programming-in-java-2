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

            voegOpleidingToe("Wiskunde", 160);
            voegOpleidingToe("Informatica", 120);
            voegCppToe("CPP Softwarearchitect", 4);
            voegCppToe( "CPP Java", 6);
            voegCppToe("CPP System Ontwikkelaar", 3);

    }


    /**
     * Zoekt een student op basis van zijn naam.
     *
     * @param naam Naam van de student.
     *
     * @return De eerste, op naam matchende student of null.
     */
    private Student getBestaandeStudent(String naam) {

        for (Student student : studentenLijst){

            if(student.getNaam().equals(naam)){

                return student;

            }
        }

        return null;

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
     */
    private Programma getBestaandProgramma(String naam) {

        for (Programma programma : programmaLijst){

            if (programma.getNaam().equals(naam)){
                return programma;
            }
        }
        return null;
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
     */
    public void voegOpleidingToe(String naam, double studiepunten) {

        if (!(programmaBestaatAl(naam))){
            programmaLijst.add(new Opleiding(naam, studiepunten));
        }
    }

    /**
     * Voegt een nieuwe CPP toe aan de lijst van programmaLijst.
     *
     * @param naam Het nieuwe programma.
     *
     * @param modules Het aantal studiepunten.
     */
    public void voegCppToe(String naam, int modules) {

        if (!(programmaBestaatAl(naam))){
            programmaLijst.add(new Cpp(naam, modules));
        }
    }



    /**
     * Voegt een student toe aan de lijst van studentenLijst.
     *
     * @param studentNaam De naam van een nieuwe student.
     *
     * @param opleidingNaam De naam van de opleiding.
     */
    public void voegRegulierToe(String studentNaam, String opleidingNaam){

        Programma opleiding = getBestaandProgramma(opleidingNaam);
        if (!(studentBestaatAl(studentNaam)) && opleiding instanceof Opleiding){
            studentenLijst.add(new Regulier(studentNaam, opleiding));
        }
    }


    /**
     * Voegt een scholer toe aan de lijst van studentenLijst.
     *
     * @param scholerNaam De naam van een nieuwe scholer.
     *
     * @param cppNaam De naam van de cpp.
     */
    public void voegScholerToe(String scholerNaam, String cppNaam) {

        Programma cpp = getBestaandProgramma(cppNaam);
        if (!(studentBestaatAl(scholerNaam)) && cpp instanceof Cpp){
            studentenLijst.add(new Scholer(scholerNaam, getBestaandProgramma(cppNaam)));
        }
    }


    /**
     * Verhoogt het aantal studiepunten.
     *
     * @param naam Naam van de student.
     *
     * @param behaaldePunten Aantal studiepunten die een student gehaald heeft.
     */
    public void verhoogAantalStudiepunten(String naam, double behaaldePunten)  {

        Student student = getBestaandeStudent(naam);

        if (student instanceof Regulier){
            ((Regulier)student).verhoogBehaaldeStudiepunten(behaaldePunten);
        }
    }

    /**
     * Verhoogt het aantal modules.
     *
     * @param naam Naam van de student.
     *
     * @param behaaldeModules Aantal modules die een student gehaald heeft.
     */
    public void verhoogAantalModules(String naam, int behaaldeModules) {

        Student student = getBestaandeStudent(naam);

        if (student instanceof Scholer){
            ((Scholer)student).verhoogBehaaldeModules(behaaldeModules);
        }
    }




    /**
     * Geeft de gegevens van een student weer.
     *
     * @param naam De naam van de student.
     *
     * @return De gegevens van een student.
     *
     */
    public String getStudentInfo(String naam) {

        Student gevondenStudent = getBestaandeStudent(naam);

        if (gevondenStudent != null) {

            return gevondenStudent.getStudentInfo();
        }

        return "";

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

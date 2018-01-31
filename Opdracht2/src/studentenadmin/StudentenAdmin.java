package studentenadmin;


import java.util.ArrayList;
import java.util.List;

/**
 * Beheert alle studenten en studie programma's.
 */
public class StudentenAdmin {

    /**
     * Container voor alle studenten objecten.
     */
    private List<Student> studenten = new ArrayList<>();
    /**
     * Container voor alle studie programma objecten.
     */
    private List<Programma> programmas = new ArrayList<>();


    /**
     * Default constructor.
     */
    public StudentenAdmin(){}


    /**
     * Zoekt een student op basis van zijn naam.
     *
     * @param naam Naam van de student.
     *
     * @return De eerste, op naam matchende student of null.
     */
    private Student getStudent(String naam) throws StudentenAdminException{
        for (Student student : studenten){

            if(student.getNaam().equals(naam)){

                return student;

            }
        }

        /*
        Deze methode mag geen exception gooien omdat verschillende methoden hiervan gebruik maken. Bij het toevoegen
        van een nieuwe student moeten we controleren of een student met deze naam al in het systeem staat en bij het
        tonen van studenten gegevens van een student moeten we zijn gegevens ophalen.
         */
        return null;
    }

    /**
     * Zoekt een studieprogramma op basis van zijn naam.
     *
     * @param naam De naam van het programma.
     *
     * @return Een gegeven studieprogramma of null.
     */
    private Programma getProgramma(String naam) {

        for (Programma programma : programmas){

            if (programma.getNaam().equals(naam)){

                return programma;

            }
        }

        /*
        Deze methode mag ook geen exception gooien omdat we niet twee keer hetzelfde programma toe willen voegen, maar
          ook de namen van bestaande programma's op willen zoeken.
         */
        return null;

    }


    /**
     * Voegt een nieuwe opleiding toe aan de lijst van programmas.
     *
     * @param naam De nieuwe opleiding.
     *
     * @param studiepunten Het aantal studiepunten.
     *
     * @throws StudentenAdminException Als programma met dezelfde naam al bestaat of toevoegen van programma misgaat.
     */
    public void voegOpleidingToe(String naam, double studiepunten) throws StudentenAdminException{

        if (getProgramma(naam) != null){

            throw new StudentenAdminException("Programma met deze naam bestaat al");

        }

        programmas.add(new Opleiding(naam, studiepunten));

    }

    /**
     * Voegt een nieuwe CPP toe aan de lijst van programmas.
     *
     * @param naam Het nieuwe programma.
     *
     * @param modules Het aantal studiepunten.
     *
     * @throws StudentenAdminException Als programma met dezelfde naam al bestaat of toevoegen van programma misgaat.
     */
    public void voegCppToe(String naam, int modules) throws StudentenAdminException{

        if (getProgramma(naam) != null){

            throw new StudentenAdminException("Programma met deze naam bestaat al");

        }

        programmas.add(new Cpp(naam, modules));

    }



    /**
     * Voegt een student toe aan de lijst van studenten.
     *
     * @param studentNaam De naam van een nieuwe student.
     *
     * @param opleidingNaam De naam van de opleiding.
     *
     * @throws StudentenAdminException Als studentnaam geregistreerd is of een programma met de naam niet bestaat.
     */
    public void voegRegulierToe(String studentNaam, String opleidingNaam) throws StudentenAdminException{

        if (getStudent(studentNaam) != null){

            throw new StudentenAdminException("Student met dezelfde naam staat al geregistreerd");
        }

        studenten.add(new Regulier(studentNaam, getProgramma(opleidingNaam)));

    }


    /**
     * Voegt een scholer toe aan de lijst van studenten.
     *
     * @param scholerNaam De naam van een nieuwe scholer.
     *
     * @param cppNaam De naam van de cpp.
     *
     * @throws StudentenAdminException Als student met de naam al geregistreerd is of een programma met de naam niet bestaat.
     */
    public void voegScholerToe(String scholerNaam, String cppNaam) throws StudentenAdminException{

        if (getStudent(scholerNaam) != null){

                throw new StudentenAdminException("Student met dezelfde naam staat al geregistreerd");
            }

        studenten.add(new Scholer(scholerNaam, getProgramma(cppNaam)));

    }


    /**
     * Verhoogt het aantal studiepunten.
     *
     * @param naam Naam van de student.
     *
     * @param studiepunten Aantal studiepunten die een student gehaald heeft.
     *
     * @throws StudentenAdminException Wordt gegooid als student onbekend is of niet regulier is.
     */
    public void verhoogAantalStudiepunten(String naam, double studiepunten) throws StudentenAdminException, ClassCastException{

        Student student = getStudent(naam);

        if (student == null){
            throw new StudentenAdminException("Student naam is onbekend");
        }

        ((Regulier)student).verhoogBehaaldeStudiepunten(studiepunten);

    }

    /**
     * Verhoogt het aantal modules.
     *
     * @param naam Naam van de student.
     *
     * @param aantalModules Aantal modules die een student gehaald heeft.
     *
     * @throws StudentenAdminException Wordt gegooid als student onbekend is of geen scholer is.
     */
    public void verhoogAantalModules(String naam, double aantalModules) throws StudentenAdminException{

        Student student = getStudent(naam);

        if (student == null){
            throw new StudentenAdminException("Student naam is onbekend");
        }

        if (!(student instanceof Scholer)){
            throw new StudentenAdminException("Alleen scholers hebben modules");
        }

        ((Scholer)student).verhoogAantalModules(aantalModules);

    }




    /**
     * Geeft de gegevens van een student weer.
     *
     * @param naam De naam van de student.
     *
     * @return De gegevens van een student.
     */
    public String getStudentInfo(String naam) throws StudentenAdminException{

        Student gevondenStudent = getStudent(naam);

        if (gevondenStudent == null){

            throw new StudentenAdminException("Naam van de student is niet bekend");

        }

        return gevondenStudent.getStudentInfo();

    }


    /**
     * Geeft alle studentengegevens weer.
     *
     * @return Alle studentengegevens.
     */
    public String getAlleStudentenInfo(){

        String result = "";

        for (Student student: studenten){
            result += student.getStudentInfo();
        }

        return result;
    }

    /**
     * Zoekt de namen van alle 'CPP'-programma's.
     *
     * @return Verzameling van namen van CPP programmas.
     */
    public List<String> getAlleCppNamen(){

        List<String> namenLijst = new ArrayList<>();

        for (Programma programma : programmas) {
            if (programma instanceof Cpp) {

                namenLijst.add(programma.getNaam());

            }

        }

        return namenLijst;

    }

    /**
     * Zoekt de namen van alle reguliere opleidingen.
     *
     * @return Verzameling van namen van opleiding programmas.
     */
    public List<String> getAlleOpleidingNamen(){

        List<String> namenLijst = new ArrayList<>();

        for (Programma programma : programmas) {

            if (programma instanceof Opleiding) {

                namenLijst.add(programma.getNaam());

            }

        }
        return namenLijst;

    }

}

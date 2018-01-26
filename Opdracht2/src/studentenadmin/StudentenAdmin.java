package studentenadmin;

import java.lang.reflect.Constructor;
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
     *
     * @throws StudentenAdminException Wordt gegooid als naam null is of er geen student met de gespecificeerde
     * naam gevonden is.
     */
    private Student getStudent(String naam) throws StudentenAdminException{
/*
        if (naam == null){
            throw new StudentenAdminException("Het veld naam mag niet null zijn");
        }
*/
        for (Student student : studenten){

            if(student.getNaam().equals(naam)){

                return student;

            }
        }

        return null;

    }

    /**
     * Zoekt een studieprogramma op basis van zijn naam.
     *
     * @param naam De naam van het programma.
     *
     * @return Een gegeven studieprogramma of null.
     *
     * @throws StudentenAdminException Wordt gegooid als naam null is of er geen programma met de gespecificeerde
     * naam gevonden is.
     */
    private Programma getProgramma(String naam) throws StudentenAdminException{
/*
        if (naam == null){
            throw new StudentenAdminException("De naam van het programma is niet bekend");
        }
*/
        for (Programma programma : programmas){

            if (programma.getNaam().equals(naam)){

                return programma;

            }
        }

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

            throw new StudentenAdminException("Programma met deze naam bestaat al.");

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

            throw new StudentenAdminException("Programma met deze naam bestaat al.");

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

        for (Student bestaandeStudent : studenten){

            if (bestaandeStudent.getNaam().equals(studentNaam)){

                throw new StudentenAdminException("Student met dezelfde naam staat al geregistreerd");
            }
        }

        Programma programma = getProgramma(opleidingNaam);

        if (programma == null){

            throw new StudentenAdminException("Programma met deze naam bestaat niet.");

        }

        studenten.add(new Regulier(studentNaam, programma));


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

        for (Student bestaandescholer : studenten){

            if (bestaandescholer.getNaam().equals(scholerNaam)){

                throw new StudentenAdminException("Student met dezelfde naam staat al geregistreerd");
            }
        }

        Programma programma = getProgramma(cppNaam);

        if (programma == null){

            throw new StudentenAdminException("Programma met deze naam bestaat niet.");

        }

        studenten.add(new Scholer(scholerNaam, programma));


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
    public void verhoogAantalStudiepunten(String naam, double studiepunten) throws StudentenAdminException{

        Student student = getStudent(naam);

        if (student == null){
            throw new StudentenAdminException("Student naam is onbekend");
        }

        if (!(student instanceof Regulier)){
            throw new StudentenAdminException("Alleen reguliere studenten hebben studiepunten");
        }

        ((Regulier) student).verhoogBehaaldeStudiepunten(studiepunten);

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

        ((Scholer) student).verhoogAantalModules(aantalModules);

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

        List<String> namenLijst = new ArrayList<String>();

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

        List<String> namenLijst = new ArrayList<String>();

        for (Programma programma : programmas) {

            if (programma instanceof Opleiding) {

                namenLijst.add(programma.getNaam());

            }

        }
        return namenLijst;

    }

}

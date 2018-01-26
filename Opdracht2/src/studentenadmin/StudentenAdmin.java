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
     */
    private Student getStudent(String naam){

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
     * @return Een gegeven studieprogramma.
     */
    private Programma getProgramma(String naam){

        for (Programma programma : programmas){

            if (programma.getNaam().equals(naam)){

                return programma;

            }
        }

        return null;

    }


    /**
     * Voegt een nieuw programma toe aan de lijst van programmas.
     *
     * @param programmaKlasse De klasse van het nieuwe programma.
     *
     * @param naam Het nieuwe programma.
     *
     * @param hoevelheid Het nieuwe programma.
     *
     * @return De status van de operatie.
     */
    public boolean voegProgrammaToe(String programmaKlasse, String naam, double hoevelheid) throws StudentenAdminException{

        try{

            Constructor klasse = Class.forName(programmaKlasse).getConstructor(String.class, Double.TYPE);
            Programma programma = (Programma)klasse.newInstance(naam, hoevelheid);
            programmas.add(programma);


        } catch (Exception ex){

            throw new StudentenAdminException("Toevoegen van programma niet mogelijk (" + ex.getClass().getName() + ")");
        }


        return true;
    }



    /**
     * Voegt een student toe aan de lijst van studenten.
     *
     * @param studentKlasse De klasse van het nieuwe programma.
     *
     * @param studentNaam De naam van een nieuwe student.
     *
     * @param programmaNaam De naam van de opleiding.
     *
     * @return Succes van de operatie.
     */
    public boolean voegStudentToe(String studentKlasse, String studentNaam, String programmaNaam) throws StudentenAdminException{

        for (Student bestaandeStudent : studenten){
            if (bestaandeStudent.getNaam().equals(studentNaam)){
                throw new StudentenAdminException("Student met dezelfde naam staat al geregistreerd");
            }
        }

        if (studentNaam.length() < 2){

            throw new StudentenAdminException("De naam van een student moet minimaal 2 letters bevatten");
        }

        if (programmaNaam == null){
            throw new StudentenAdminException("De naam van het programma is niet bekend");
        }

        try {
            Constructor klasse = Class.forName(studentKlasse).getConstructor(String.class, Programma.class);
            Programma programma = getProgramma(programmaNaam);
            Student student = (Student)klasse.newInstance(studentNaam, programma);
            studenten.add(student);

        } catch (Exception ex){

            throw new StudentenAdminException("Toevoegen van student niet mogelijk. " + ex.getMessage());
        }

        return true;

    }




    /**
     * Verhoogt de hoeveelheid behaalde programmaonderdelen.
     *
     * @param naam Naam van de student.
     *
     * @param hoeveelheid Hoeveelheid waarmee de student vordering geboekt heeft.
     *
     * @param klassenNaam naam van de klasse van een student
     *
     * @return Succes van de operatie.
     */
    public boolean verhoogBehaaldeProgrammaOnderdelen(String naam, double hoeveelheid, String klassenNaam) throws StudentenAdminException{

        Student student = getStudent(naam);

        if (student == null){
            throw new StudentenAdminException("Student naam is onbekend");
        }
        if (hoeveelheid < 0.0){
            throw new StudentenAdminException("Aantal behaalde onderdelen moet groter dan 0 zijn");
        }
        if (!student.getProgramma().getClass().getName().equals(klassenNaam)){
            throw new StudentenAdminException("Alleen scholers hebben modules en reguliere studenten studiepunten");
        }

        return student.verhoogBehaaldProgrammaOnderdeel(hoeveelheid);

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

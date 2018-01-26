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
    public Programma getProgramma(String naam){

        for (Programma programma : programmas){

            if (programma.getNaam().equals(naam)){

                return programma;

            }
        }

        return null;

    }


    /**
     * Geeft de name van de klasse van een student weer.
     *
     * @param naam De naam van een student.
     *
     * @return de klassennaam van de gevonden student.
     */
    public String getStudentKlassenNaam(String naam){

        Student student = getStudent(naam);

        if(student != null) {

            return student.getClass().getName();
        }


        return null;
    }

    /**
     * Voegt een nieuw programma toe aan de lijst van programmas.
     *
     * @param programma Het nieuwe programma.
     *
     * @return De status van de operatie.
     */
    public boolean voegProgrammaToe(Programma programma){

        programmas.add(programma);
        return true;
    }



    /**
     * Voegt een student toe aan de lijst van studenten.
     *
     * @param student Een nieuwe student.
     *
     * @return Succes van de operatie.
     */
    public boolean voegStudentToe(Student student){

        // todo robuust maken van de applicatie in vorm van checks
        // if(student instanceof Regulier && student.getProgramma() instanceof Opleiding){}
        // if(student instanceof Scholer && student.getProgramma() instanceof Cpp){}

        studenten.add(student);
        return true;

    }


    /**
     * Verhoogt de hoeveelheid behaalde programmaonderdelen.
     *
     * @param naam Naam van de student.
     *
     * @param hoeveelheid Hoeveelheid waarmee de student vordering geboekt heeft.
     *
     * @return Succes van de operatie.
     */
    public boolean verhoogBehaaldeProgrammaOnderdelen(String naam, double hoeveelheid){

        Student student = getStudent(naam);

        if(student != null){

            return student.verhoogBehaaldProgrammaOnderdeel(hoeveelheid);

        }

        return false;
    }


    /**
     * Geeft de gegevens van een student weer.
     *
     * @param naam De naam van de student.
     *
     * @return De gegevens van een student.
     */
    public String getStudentInfo(String naam){

        Student gevondenStudent = getStudent(naam);

        if (gevondenStudent != null){

            return gevondenStudent.getStudentInfo();

        }

        return null;

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

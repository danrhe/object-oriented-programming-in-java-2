package studentenadmin;

import java.util.ArrayList;

/**
 * Deze klasse beheert alle studenten van de studentenadministratie
 */
public class StudentenAdmin {

    private ArrayList<Student> studenten = new ArrayList<>();


    /**
     * Zoekt een student op basis van zijn naam
     *
     * @param naam naam van de student
     *
     * @return het eerste, op naam matchende student
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
     * Voegt een nieuwe reguliere student aan de administratie toe
     *
     * @param naam naam van de student
     * @param opleiding de opleiding waarvoor de student ingeschreven staat
     *
     * @return succes van de operatie
     */
    public boolean maakNieuweRegulier(String naam, Opleiding opleiding){

        Student student = new Regulier(naam, opleiding);
        studenten.add(student);

        return true;
    }

    /**
     * Voegt een nieuwe scholer aan de administratie toe
     *
     * @param naam naam van de student
     * @param cpp professional program waarvoor student ingeschreven staat
     *
     * @return succes van de operatie
     */
    public boolean maakNieuweScholer(String naam, Cpp cpp){

        Student student = new Scholer(naam, cpp);
        studenten.add(student);

        return true;
    }


    /**
     * Verhoogt het aantal behaalde studiepunten van een student.
     *
     * @param naam naam van de student.
     * @param studiepunten studiepunten die de student heeft behaald.
     *
     * @return succes van de operatie.
     */
    public boolean verhoogBehaaldeStudiepuntenStudent(String naam, double studiepunten){

        Student student = getStudent(naam);

        if(student != null && student.getClass().getName().equals("studentenadmin.Regulier")){

            ((Regulier)student).setBehaaldeStudiepunten(((Regulier)student).getBehaaldeStudiepunten() + studiepunten);
            return true;

        }

        return false;
    }

    /**
     * Verhoogt het aantal behaalde cpp modules van een student met 1.
     *
     * @param naam naam van de student.
     *
     * @return succes van de operatie.
     */
    public boolean verhoogBehaaldeModuleStudentMetEen(String naam){

        Student student = getStudent(naam);

        if(student != null && student.getClass().getName().equals("studentenadmin.Scholer")){

            ((Scholer)student).setBehaaldeModules(((Scholer)student).getBehaaldeModules() + 1);
            return true;

        }

        return false;
    }

    /**
     * Geeft de gegevens van een student weer.
     *
     * @param naam de naam van de student
     *
     * @return de gegevens van een student
     */
    public String getStudentInfo(String naam){

        String result = "";

        Student gevondenStudent = getStudent(naam);

        if (gevondenStudent != null){

            result = gevondenStudent.toString();

        }

        return result;

    }


    /**
     * Geeft alle studentengegevens weer
     *
     * @return Alle studentengegevens
     */
    public String getAllStudentInfo(){

        String result = "";

        String naam;
        for (Student student: studenten){
            naam = student.getNaam();
            result += getStudentInfo(naam);
        }

        return result;
    }
}

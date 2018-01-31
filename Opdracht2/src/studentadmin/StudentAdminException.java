package studentadmin;


public class StudentAdminException extends Exception {

    /**
     * Gooit een checked exception van type StudentAdminException zonder tekst op.
     */
    StudentAdminException(){

        super();
    }
    /**
     * Gooit een checked exception van type StudentAdminException met tekst op.
     */
    StudentAdminException(String message){

        super(message);
    }
}

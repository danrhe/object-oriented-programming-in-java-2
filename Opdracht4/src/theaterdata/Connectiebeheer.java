package theaterdata;


import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Beheert de connectie met de database.
 * Bevat methoden voor openen en sluiten van connectie met database,
 * en voor opvragen van de connectie.
 * @author Open Universiteit
 */
public class Connectiebeheer {

  private static Connection con = null;


  /**
   * Maakt een connectie met de database en initialiseert
   * Klantbeheer en VoostellingBeheer.
   *
   * @throws TheaterException als de initialisatie mislukt.
   */
  public static void openDB() throws TheaterException {

    if (con == null) {
      try {
        // log errors
        DriverManager.setLogWriter(new PrintWriter(System.out));
        // get driver class
        Class.forName(DBConst.DRIVERNAAM);
        // make connection
        con = DriverManager.getConnection(DBConst.URL, DBConst.GEBRUIKERSNAAM, DBConst.WACHTWOORD);

        Klantbeheer.init(con);
        Voorstellingbeheer.init(con);

      } catch (ClassNotFoundException e) {
        throw new TheaterException("Could not find driver with name " + DBConst.DRIVERNAAM);
      } catch (SQLException e) {
        throw new TheaterException(e.getMessage());
      }
    }


  }


  /**
   * Sluit de connectie met de database
   */
  public static void closeDB(){
    if (con != null) {
      try {
        con.close();
      } catch (SQLException sluitError) {

      }


    }
  }

  public static void main(String[] args) throws TheaterException{

    openDB();
  }
}
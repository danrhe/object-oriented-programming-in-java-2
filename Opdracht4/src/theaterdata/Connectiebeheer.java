package theaterdata;


import theater.Theater;
import theatergui.TheaterFrame;

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
   * Private constructor voorkomt dat de klasse geinstantieerd wordt.
   */
  private Connectiebeheer (){}

  /**
   * Maakt een connectie met de database
   *
   * @throws TheaterException als de initialisatie mislukt.
   */
  public static void openDB() throws TheaterException {

    if (con == null) {
      try {
        // log errors
        //DriverManager.setLogWriter(new PrintWriter(System.out));
        // get driver class
        Class.forName(DBConst.DRIVERNAAM);
        // make connection
        con = DriverManager.getConnection(DBConst.URL, DBConst.GEBRUIKERSNAAM, DBConst.WACHTWOORD);

      } catch (ClassNotFoundException e) {
        throw new TheaterException("Fout bij het maken van een verbinding met database. Driver met opgegeven naam bestaat niet (" + DBConst.DRIVERNAAM + ")");
      } catch (SQLException e) {
        throw new TheaterException("Fout by het maken van een verbinding met de database. (Fout: " + e.getMessage() + ")");
      }
    }
  }


  /**
   * Geeft de database verbinding terug. Als deze nog niet geinitialiseerd is, probeert de methode de verbinding te openen
   *
   * @return De verbinding met de database.
   *
   * @throws TheaterException als het openen van de database verbinding mislukt.
   */
  public static Connection getConnection() throws TheaterException{
    if (con == null){
      openDB();
    }
    return con;
  }


  /**
   * Sluit de connectie met de database
   */
  public static void closeDB() throws TheaterException{
    System.out.println("Poging om verbinding met database te verbreken");
    if (con != null) {
      try {
        con.close();
        System.out.println("Verbinding database is verbroken");
      } catch (SQLException sluitError) {
        throw new TheaterException("Fout bij het verbreken van de verbinding");
      }


    }
  }

  public static void main(String[] args) throws TheaterException{

    openDB();
  }
}
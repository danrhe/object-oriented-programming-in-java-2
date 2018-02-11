package theaterdata;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import theater.Klant;
import theater.Voorstelling;

/**
 * Klasse die met voorstellingen beheert. Op elke datum is er maar ??n
 * voorstelling. Deze klasse moet gewijzigd worden zodat ArrayList vervangen
 * wordt door gebruik database.
 */
public class Voorstellingbeheer {

  private static PreparedStatement pVoorDatum = null;
  private static PreparedStatement pVoorNu = null;
  private static PreparedStatement pBezetGet = null;
  private static PreparedStatement pKlantGet = null;

  /**
   * Vult voorstellingbeheer met een aantal voorstellingen.
   */
  public static void init(Connection con) throws TheaterException{
    String sql = null;
    try {
      sql = "SELECT datum, naam FROM voorstelling where datum >NOW()";
      pVoorNu = con.prepareStatement(sql);
      sql = "SELECT datum, naam FROM voorstelling where datum = ?";
      pVoorDatum = con.prepareStatement(sql);
      sql = "SELECT voorstelling, rijnummer, stoelnummer, klant FROM bezetting where voorstelling = ?";
      pBezetGet = con.prepareStatement(sql);
      sql = "SELECT naam, telefoon FROM klant where klantnummer = ?";
      pKlantGet = con.prepareStatement(sql);
    } catch (SQLException e){

      throw new TheaterException("Fout bij het voorbereiden van voorstellingen queries");
    }

  }

  public static void slaBezettingOp() throws TheaterException{

  }

  /**
   * Levert alle data op waarop voorstellingen zijn (voor zover die data in de
   * toekomst liggen).
   *
   * @return lijst met data van voorstellingen
   */
  public static ArrayList<GregorianCalendar> geefVoorstellingsData() throws TheaterException{

    ArrayList<GregorianCalendar> data = new ArrayList<GregorianCalendar>();

    try {
      ResultSet res = pVoorNu.executeQuery();
      while(res.next()) {

        java.sql.Date sqlDatum = res.getDate("datum");

        GregorianCalendar datum = new GregorianCalendar();
        datum.setTimeInMillis(sqlDatum.getTime());

        data.add(datum);
      }

    } catch (SQLException e){

      throw new TheaterException("Fout bij het voorbereiden van voorstellingen queries");
    }

    return data;
  }

  /**
   * Zoekt een voorstelling op de gegeven datum.
   *
   * @param datum
   * @return een voorstelling op de gegeven datum of null wanneer die
   *         voorstelling er niet is.
   */
  public static Voorstelling geefVoorstelling(GregorianCalendar datum) throws TheaterException{

    Voorstelling voorstelling = null;
    ResultSet res = null;

    java.sql.Date sqlDatumZoek = new java.sql.Date(datum.getTimeInMillis());
    try {
      pVoorDatum.setDate(1, sqlDatumZoek);
      res = pVoorDatum.executeQuery();

      while(res.next()) {
        java.sql.Date sqlDatum = res.getDate("datum");
        GregorianCalendar datumGevonden = new GregorianCalendar();
        datumGevonden.setTimeInMillis(sqlDatum.getTime());

        String naam = res.getString("naam");

        voorstelling = new Voorstelling(naam, datumGevonden);
      }


      pBezetGet.setDate(1, sqlDatumZoek);
      res = pBezetGet.executeQuery();

      while(res.next()) {
        // reserveer opgeslagen plaatsen
        int rijnummer = res.getInt("rijnummer");
        int stoelnummer = res.getInt("stoelnummer");
        voorstelling.reserveer(rijnummer,stoelnummer);

        // haal klantgegevens op, maak nieuwe klant instantie en plaats op gereseerveerde stoelen
        int klantnummer = res.getInt("klant");
        pKlantGet.setInt(1, klantnummer);
        ResultSet genestRes = pKlantGet.executeQuery();

        while(genestRes.next()) {
          String naam = genestRes.getString("naam");
          String telefoon = genestRes.getString("telefoon");
          Klant klant = new Klant(klantnummer, naam, telefoon);
          voorstelling.plaatsKlant(rijnummer, stoelnummer,klant);
        }

      }

    } catch (SQLException e){
      throw new TheaterException("Fout bij het uitvoeren van de voorstelling zoek query. (Message: " + e.getMessage());
    }
      return voorstelling;

  }


}

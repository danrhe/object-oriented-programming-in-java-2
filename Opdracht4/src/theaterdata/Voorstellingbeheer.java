package theaterdata;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import theater.Voorstelling;

/**
 * Klasse die met voorstellingen beheert. Op elke datum is er maar ??n
 * voorstelling. Deze klasse moet gewijzigd worden zodat ArrayList vervangen
 * wordt door gebruik database.
 */
public class Voorstellingbeheer {

  private static PreparedStatement pVoorDatum = null;
  private static PreparedStatement pVoorNu = null;

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
    } catch (SQLException e){

      throw new TheaterException("Fout bij het voorbereiden van voorstellingen queries");
    }

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
        //String naam = res.getString("naam");
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

    java.sql.Date sqlDatumZoek = new java.sql.Date(datum.getTimeInMillis());
    try {
      pVoorDatum.setDate(1, sqlDatumZoek);
      ResultSet res = pVoorDatum.executeQuery();

      while(res.next()) {
        java.sql.Date sqlDatum = res.getDate("datum");
        GregorianCalendar datumGevonden = new GregorianCalendar();
        datumGevonden.setTimeInMillis(sqlDatum.getTime());

        String naam = res.getString("naam");

        return new Voorstelling(naam, datumGevonden);
      }

    } catch (SQLException e){
      throw new TheaterException("Fout bij het uitvoeren van de voorstelling zoek query");
    }


    return null;
  }


}

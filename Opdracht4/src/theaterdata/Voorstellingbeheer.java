package theaterdata;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import theater.Klant;
import theater.Voorstelling;

/**
 * Klasse die met voorstellingen beheert. Op elke datum is er maar ??n
 * voorstelling. Deze klasse moet gewijzigd worden zodat ArrayList vervangen
 * wordt door gebruik database.
 */
public class Voorstellingbeheer {

  private static PreparedStatement pGetVoorstellingDatum = null;
  private static PreparedStatement pGetVoorstellingen = null;
  private static PreparedStatement pGetBezetting = null;
  private static PreparedStatement pGetKlant = null;
  private static PreparedStatement pGetBezettingStoel = null;
  private static PreparedStatement pInsertBezetting = null;

  /**
   * Vult voorstellingbeheer met een aantal voorstellingen.
   */
  public static void init() throws TheaterException{

    Connection con = Connectiebeheer.getConnection();
    String sql = null;

    try {
      sql = "SELECT datum, naam FROM voorstelling WHERE datum >NOW()";
      pGetVoorstellingen = con.prepareStatement(sql);
      sql = "SELECT datum, naam FROM voorstelling WHERE datum = ?";
      pGetVoorstellingDatum = con.prepareStatement(sql);
      sql = "SELECT voorstelling, rijnummer, stoelnummer, klant FROM bezetting WHERE voorstelling = ?";
      pGetBezetting = con.prepareStatement(sql);
      sql = "SELECT naam, telefoon FROM klant WHERE klantnummer = ?";
      pGetKlant = con.prepareStatement(sql);
      sql = "INSERT INTO bezetting (voorstelling, rijnummer, stoelnummer, klant) VALUES (?, ?, ?, ?)";
      pInsertBezetting = con.prepareStatement(sql);
      sql = "SELECT resnummer FROM bezetting WHERE voorstelling = ? AND rijnummer = ? AND stoelnummer = ?";
      pGetBezettingStoel = con.prepareStatement(sql);

    } catch (SQLException e){

      throw new TheaterException("Een of meer database queries van de Voorstellingbeheer klasse bevat een fout.");
    }

  }

  /**
   * Levert alle data waarop voorstellingen zijn (voor zover die data in de toekomst liggen).
   *
   * @return lijst met data van voorstellingen
   */
  public static ArrayList<GregorianCalendar> geefVoorstellingsData() throws TheaterException{

    ArrayList<GregorianCalendar> data = new ArrayList<GregorianCalendar>();

    try {
      ResultSet res = pGetVoorstellingen.executeQuery();

      while(res.next()) {
        java.sql.Date sqlDatum = res.getDate("datum");
        GregorianCalendar datum = new GregorianCalendar();
        datum.setTimeInMillis(sqlDatum.getTime());
        data.add(datum);
      }

    } catch (SQLException e){
      throw new TheaterException("Database fout bij het ophalen van voorstellings data");

    } catch (NullPointerException e){
      throw new TheaterException("Geen toekomstige voorstellingen gevonden");
    }

    return data;
  }

  /**
   * Slaat de bezetting van een stoel voor een bepaalde rij en voorstelling op in de database.
   * Voorwaarde: De stoel van deze rij en voorstelling is niet al in de database opgenomen.
   *
   * @param voorstelling De datum van de voorstelling.
   * @param rij De rij van de stoel.
   * @param stoel Het stoelnummer.
   * @param klant De klant voor wie de reservering wordt gedaan.
   *
   * @throws TheaterException als een fout optreedt tijdens het ophalen of wegschrijven van de bezettinggegevens
   */
  public static void slaBezettingOp(GregorianCalendar voorstelling, int rij, int stoel, Klant klant) throws TheaterException{

    java.sql.Date sqlDatum = new java.sql.Date(voorstelling.getTimeInMillis());
    boolean recordDatabaseBestaat = false;

    try {
      pGetBezettingStoel.setDate(1, sqlDatum);
      pGetBezettingStoel.setInt(2, rij);
      pGetBezettingStoel.setInt(3, stoel);
      ResultSet res = pGetBezettingStoel.executeQuery();
      recordDatabaseBestaat = res.next();

    } catch (SQLException e){
      throw new TheaterException ("Fout bij het ophalen van bezetting gegevens .");
    }

    if(recordDatabaseBestaat == false) {
      try {
        pInsertBezetting.setDate(1, sqlDatum);
        pInsertBezetting.setInt(2, rij);
        pInsertBezetting.setInt(3, stoel);
        pInsertBezetting.setInt(4, klant.getKlantnummer());
        pInsertBezetting.executeUpdate();

      } catch (SQLException e) {
        throw new TheaterException("Fout bij het opslaan van nieuwe bezetting.");
      }
    }
  }

  /**
   * Zoekt in de database naar een voorstelling op een gegeven datum en vult deze met de opgeslagen bezetting.
   *
   * @param datum De datum waarop de voorstelling plaats vind.
   *
   * @return een voorstelling op de gegeven datum of null wanneer die voorstelling er niet is.
   */
  public static Voorstelling geefVoorstelling(GregorianCalendar datum) throws TheaterException{

    Voorstelling voorstelling = null;
    ResultSet res = null;
    java.sql.Date sqlDatumVoorstelling = new java.sql.Date(datum.getTimeInMillis());

    // zoeken van de voorstelling naam
    try {
      pGetVoorstellingDatum.setDate(1, sqlDatumVoorstelling);
      res = pGetVoorstellingDatum.executeQuery();

      if(res.next()) {
        String naamVoorstelling = res.getString("naam");
        voorstelling = new Voorstelling(naamVoorstelling, datum);

        // zoeken van de opgeslagen bezettingen
        pGetBezetting.setDate(1, sqlDatumVoorstelling);
        res = pGetBezetting.executeQuery();

        while(res.next()) {
          // reserveer de opgeslagen bezettingen
          int rijnummer = res.getInt("rijnummer");
          int stoelnummer = res.getInt("stoelnummer");
          voorstelling.reserveer(rijnummer,stoelnummer);

          // zoek klantgegevens op en plaats deze op alle gereseerveerde stoelen
          int klantnummer = res.getInt("klant");
          pGetKlant.setInt(1, klantnummer);
          ResultSet genestRes = pGetKlant.executeQuery();

          if(genestRes.next()) {
            String naam = genestRes.getString("naam");
            String telefoon = genestRes.getString("telefoon");
            Klant klant = new Klant(klantnummer, naam, telefoon);
            voorstelling.plaatsKlant(klant);
          } else {
              throw new TheaterException ("Geen klantgegevens gevonden die bij klant nummer passen");
          }
        }
      }
    } catch (SQLException e){
      throw new TheaterException("Fout bij het ophalen van de gegevens van een voorstelling. (Message: " + e.getMessage());
    }
      return voorstelling;

  }


  public static void main(String[] args) {
    String sdatum = "12-09-2018";
    SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy");
    GregorianCalendar datum = new GregorianCalendar();
    try {
      datum.setTime(fmt.parse(sdatum));

    } catch (ParseException exception){

    }
    try {
      Connectiebeheer.openDB();
      ArrayList<GregorianCalendar> data = geefVoorstellingsData();
      System.out.println(data.toString());

      Voorstelling voorstelling = geefVoorstelling(datum);
      System.out.println(voorstelling.toString());
      Connectiebeheer.closeDB();

    } catch (TheaterException e){
      e.getMessage();
    }
  }


}

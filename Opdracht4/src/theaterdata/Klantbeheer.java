package theaterdata;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import theater.Klant;

/**
 * Deze klasse die klanten beheert.
 * Deze klasse moet gewijzigd worden zodat ipv ArrayList database gebruikt wordt.
 */
public class Klantbeheer {  

  private static PreparedStatement pSelectMaxKlantnummer = null;
  private static PreparedStatement pZoekKlant = null;
  private static PreparedStatement pInsertKlant = null;

  /**
   * Initialiseert de klanten.
   */
  public static void init(Connection con) throws TheaterException{
    String sql;
    try {

      sql = "SELECT klantnummer, naam, telefoon FROM klant WHERE naam = ? AND telefoon = ?";
      pZoekKlant = con.prepareStatement(sql);

      sql = "SELECT MAX(klantnummer) FROM klant";
      pSelectMaxKlantnummer = con.prepareStatement(sql);

      sql = "INSERT INTO klant (klantnummer, naam, telefoon)  VALUES (?,?,?)";
      pInsertKlant = con.prepareStatement(sql);


    } catch (SQLException e){
      throw new TheaterException("Fout bij het voorbereiden van de klantbeheer query");
    }
   }
  
  /**
   * Genereert het volgende beschikbare klantnummer.
   * @return nieuw klantnummer
   */
  public static int getVolgendKlantNummer() throws TheaterException{

    int hoogsteKlantnummer = 0;

    try {
      ResultSet res = pSelectMaxKlantnummer.executeQuery();
      while (res.next()){
        hoogsteKlantnummer = res.getInt(1);
      }

    } catch (SQLException e){
      throw new TheaterException("Fout bij het ophalen van het hoogste klantnummer");
    }
    hoogsteKlantnummer++;

    return hoogsteKlantnummer;
  }
  
  /**
   * Geeft een klant met de gegeven naam en het gegeven telefoonnummer
   * Als de klant al in de lijst zat, wordt die teruggegeven; anders
   * wordt er een nieuwe klant gemaakt.
   * @param naam  naam van de klant
   * @param telefoon  telefoonnummer van de klant
   * @return  een klant me de ingevoerde naam en telefoon.
   */
  public static Klant geefKlant(String naam, String telefoon) throws TheaterException{
    Klant klant = zoekKlant(naam, telefoon);
    if (klant == null) {
      klant = nieuweKlant(naam, telefoon);
    }
    return klant;
  }
  
  /**
   * Zoekt klant met gegeven naam in de lijst met klanten.
   * @param naam naam van te zoeken klant
   * @param telefoon telefoonnummer van te zoeken klant
   * @return de klant of null wanneer klant niet is gevonden
   */
  private static Klant zoekKlant(String naam, String telefoon) throws TheaterException {

    try {
      pZoekKlant.setString(1, naam);
      pZoekKlant.setString(2, telefoon);
      ResultSet res = pZoekKlant.executeQuery();

      if(res.next()) {
        int klantnummer = res.getInt("klantnummer");
        String sqlNaam = res.getString("naam");
        String sqlTelefoon = res.getString("telefoon");
        return new Klant(klantnummer, sqlNaam, sqlTelefoon);
      }

      return null;

      } catch (SQLException ex) {
      throw new TheaterException("Fout bij het uitvoeren van de zoek klant query");
    }

  }
  
  /**
   * Voegt een nieuwe klant toe aan theater.
   * @param naam  naam van de nieuwe klant
   * @param telefoon telefoonnummer van de nieuwe klant
   */
  private static Klant nieuweKlant(String naam, String telefoon) throws TheaterException{
    int knr = getVolgendKlantNummer();
    Klant klant = new Klant(knr, naam, telefoon);

    try{
      pInsertKlant.setInt(1, knr);
      pInsertKlant.setString(2, naam);
      pInsertKlant.setString(3, telefoon);
      pInsertKlant.executeUpdate();
    }
    catch (SQLException e){
      throw new TheaterException("Fout bij het aanmaken nieuwe klant");
    }

    return klant;
  }
 
}

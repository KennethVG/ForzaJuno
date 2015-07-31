package be.intec.forzajuno.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.Date;


@DatabaseTable(tableName = "spelers")
public class Speler implements Serializable {

    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(canBeNull = false)
    private String voornaam;
    @DatabaseField(canBeNull = false)
    private String achternaam;
    @DatabaseField
    private Date geboortedatum;
    @DatabaseField
    private String rekeningNummer;
    @DatabaseField
    private String info;
    @DatabaseField
    private String adres;
    @DatabaseField
    private String gemeente;
    @DatabaseField
    private int postcode;
    @DatabaseField
    private String telefoonnummer;

    public Speler() {
        this(null, null, null);
    }

    public Speler(String voornaam, String achternaam, String info) {
        setAchternaam(achternaam);
        setVoornaam(voornaam);
        setInfo(info);
    }

    public Speler(String voornaam, String achternaam, Date geboortedatum, String rekeningNummer, String info, String adres, String gemeente, int postcode, String telefoonnummer) {
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.geboortedatum = geboortedatum;
        this.rekeningNummer = rekeningNummer;
        this.info = info;
        this.adres = adres;
        this.gemeente = gemeente;
        this.postcode = postcode;
        this.telefoonnummer = telefoonnummer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public Date getGeboortedatum() {
        return geboortedatum;
    }

    public void setGeboortedatum(Date geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    public String getRekeningNummer() {
        return rekeningNummer;
    }

    public void setRekeningNummer(String rekeningNummer) {
        this.rekeningNummer = rekeningNummer;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getGemeente() {
        return gemeente;
    }

    public void setGemeente(String gemeente) {
        this.gemeente = gemeente;
    }

    public int getPostcode() {
        return postcode;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    public String getTelefoonnummer() {
        return telefoonnummer;
    }

    public void setTelefoonnummer(String telefoonnummer) {
        this.telefoonnummer = telefoonnummer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Speler speler = (Speler) o;

        if (!voornaam.equals(speler.voornaam)) return false;
        return achternaam.equals(speler.achternaam);

    }

    @Override
    public int hashCode() {
        int result = voornaam.hashCode();
        result = 31 * result + achternaam.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Speler{" +
                "id=" + id +
                ", voornaam='" + voornaam + '\'' +
                ", achternaam='" + achternaam + '\'' +
                ", geboortedatum=" + geboortedatum +
                ", rekeningNummer='" + rekeningNummer + '\'' +
                ", info='" + info + '\'' +
                ", adres='" + adres + '\'' +
                ", gemeente='" + gemeente + '\'' +
                ", postcode=" + postcode +
                ", telefoonnummer='" + telefoonnummer + '\'' +
                '}';
    }
}




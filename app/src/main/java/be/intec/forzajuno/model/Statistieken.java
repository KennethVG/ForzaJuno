package be.intec.forzajuno.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "stats")
public class Statistieken {

    @DatabaseField (generatedId = true)
    private int _id;
    @DatabaseField (foreign = true)
    private Speler speler;
    @DatabaseField (canBeNull = false, defaultValue = "0")
    private int aantalGoals;
    @DatabaseField (canBeNull = false, defaultValue = "0")
    private int aantalAssists;
    @DatabaseField (canBeNull = false, defaultValue = "0")
    private int aantalKeerTruitjesGewassen;
    @DatabaseField
    private boolean heeftTruitjes;

    public Statistieken() {
        // Verplicht NO-ARG for ORMLITE!
    }

    public Statistieken(Speler speler, int aantalGoals, int aantalAssists, int aantalKeerTruitjesGewassen, boolean heeftTruitjes) {
        this.speler = speler;
        this.aantalGoals = aantalGoals;
        this.aantalAssists = aantalAssists;
        this.aantalKeerTruitjesGewassen = aantalKeerTruitjesGewassen;
        this.heeftTruitjes = heeftTruitjes;
    }

    public int getAantalGoals() {
        return aantalGoals;
    }

    public void setAantalGoals(int aantalGoals) {
        this.aantalGoals = aantalGoals;
    }

    public int getAantalAssists() {
        return aantalAssists;
    }

    public void setAantalAssists(int aantalAssists) {
        this.aantalAssists = aantalAssists;
    }

    public int getAantalKeerTruitjesGewassen() {
        return aantalKeerTruitjesGewassen;
    }

    public void setAantalKeerTruitjesGewassen(int aantalKeerTruitjesGewassen) {
        this.aantalKeerTruitjesGewassen = aantalKeerTruitjesGewassen;
    }

    public boolean isHeeftTruitjes() {
        return heeftTruitjes;
    }

    public void setHeeftTruitjes(boolean heeftTruitjes) {
        this.heeftTruitjes = heeftTruitjes;
    }

    public Speler getSpeler() {
        return speler;
    }

    public void setSpeler(Speler speler) {
        this.speler = speler;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }
}

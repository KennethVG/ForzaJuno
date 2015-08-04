package be.intec.forzajuno.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "stats")
public class Statistieken {

    @DatabaseField
    private int spelerId;
    @DatabaseField
    private int aantalGoals;
    @DatabaseField
    private int aantalAssists;
    @DatabaseField
    private int aantalKeerTruitjesGewassen;
    @DatabaseField
    private boolean heeftTruitjes;

    public Statistieken() {

    }

    public Statistieken(int spelerId, int aantalGoals, int aantalAssists, int aantalKeerTruitjesGewassen, boolean heeftTruitjes) {
        this.spelerId= spelerId;
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

    public int getSpelerId() {
        return spelerId;
    }

    public void setSpelerId(int spelerId) {
        this.spelerId = spelerId;
    }
}

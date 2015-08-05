package be.intec.forzajuno.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

@DatabaseTable(tableName = "kalender")
public class Kalender implements Serializable {

    @DatabaseField(generatedId = true)
    private int _id;
    @DatabaseField(canBeNull = false)
    private String date;
    @DatabaseField(canBeNull = false)
    private String match;
    @DatabaseField
    private String uitslag;
    @DatabaseField(foreign = true)
    private Statistieken stats;

    public Kalender(){
        // Verplicht NO-ARG for ORMLITE!
    }


    public Kalender(String date, String match) {
        this.date = date;
        this.match = match;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMatch() {
        return match;
    }

    public void setMatch(String match) {
        this.match = match;
    }

    public String getUitslag() {
        return uitslag;
    }

    public void setUitslag(String uitslag) {
        this.uitslag = uitslag;
    }

    public Statistieken getStats() {
        return stats;
    }

    public void setStats(Statistieken stats) {
        this.stats = stats;
    }
}

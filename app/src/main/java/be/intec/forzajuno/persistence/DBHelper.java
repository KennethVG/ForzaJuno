package be.intec.forzajuno.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import be.intec.forzajuno.model.Kalender;
import be.intec.forzajuno.model.Speler;
import be.intec.forzajuno.model.Statistieken;

public class DBHelper extends OrmLiteSqliteOpenHelper {

    private static final String DB_NAME = "forzajuno.db";
    private static final int DB_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Speler.class);
            TableUtils.createTable(connectionSource, Statistieken.class);
            TableUtils.createTable(connectionSource, Kalender.class);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Tabel kan niet worden aangemaakt!");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Speler.class, false);
            TableUtils.dropTable(connectionSource, Statistieken.class, false);
            TableUtils.dropTable(connectionSource, Kalender.class, false);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Tabel kan niet geüpdatet worden!");
        }

    }
}

package be.intec.forzajuno.persistence;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;
import java.util.StringTokenizer;

import be.intec.forzajuno.model.Speler;

public class SpelerDaoORMImpl implements SpelerDao {

    private DBHelper helper;
    private Context context;
    private Dao<Speler, Integer> spelerDao;

    public SpelerDaoORMImpl(Context context) {
        this.context = context;
        this.helper = getDBHelper();
    }

    // Implemented methods form Interface SpelerDao:
    @Override
    public int add(Speler speler) throws SQLException {
        for (Speler s : getAllSpelers()) {
            if (speler.equals(s)) {
                return 0;
            }
        }

        return getSpelerDao().create(speler);
    }

    @Override
    public int delete(Speler speler) throws SQLException {
        return getSpelerDao().delete(speler);
    }

    @Override
    public int update(Speler speler) throws SQLException {
        return getSpelerDao().update(speler);
    }

    @Override
    public List<Speler> getAllSpelers() throws SQLException {
        return getSpelerDao().queryForAll();
    }

    @Override
    public Speler getSpelerBijVolledigeNaam(String volledigeNaam) throws SQLException {

        String voornaam = getVoornaam(volledigeNaam);
        String achternaam = getAchternaam(volledigeNaam);

        List<Speler> mijnSpelers = getAllSpelers();
        for (Speler s : mijnSpelers) {
            System.out.println(s.getAchternaam());
            if (s.getVoornaam().equalsIgnoreCase(voornaam) && s.getAchternaam().equalsIgnoreCase(achternaam.trim())) {
                return s;
            }
        }
        return null;
    }


    // Helpermethodes:
    private DBHelper getDBHelper() {
        if (helper == null) {
            helper = OpenHelperManager.getHelper(context, DBHelper.class);
        }
        return helper;
    }

    private Dao<Speler, Integer> getSpelerDao() throws SQLException {
        if (spelerDao == null) {
            spelerDao = getDBHelper().getDao(Speler.class);
        }
        return spelerDao;
    }

    private String getVoornaam(String volledigeNaam) {
        StringTokenizer tok = new StringTokenizer(volledigeNaam);
        return tok.nextToken();
    }

    private String getAchternaam(String volledigeNaam) {
        StringTokenizer tok = new StringTokenizer(volledigeNaam);
        String lastname = "";

        tok.nextToken();
        while (tok.hasMoreTokens()) {
            lastname += tok.nextToken() + " ";
        }

        return lastname;
    }
}

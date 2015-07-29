package be.intec.forzajuno.persistence;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

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
        for (Speler s : getAllSpelers()){
            if(speler.equals(s)){
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
    public List<Speler> getAllSpelers() throws SQLException {
        return getSpelerDao().queryForAll();
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
}

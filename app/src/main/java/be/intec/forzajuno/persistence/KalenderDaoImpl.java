package be.intec.forzajuno.persistence;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

import be.intec.forzajuno.model.Kalender;

public class KalenderDaoImpl implements KalenderDao {


    private DBHelper helper;
    private Context context;
    private Dao<Kalender, Integer> kalenderDao;


    public KalenderDaoImpl(Context context) {
        this.context = context;
        this.helper = getDBHelper();
    }

    @Override
    public List<Kalender> getFullKalender() throws SQLException {
        return getKalenderDao().queryForAll();
    }

    @Override
    public int addKalender(Kalender kalender) throws SQLException {
        return getKalenderDao().create(kalender);
    }

    @Override
    public int updateKalender(Kalender kalender) throws SQLException {
        return getKalenderDao().update(kalender);
    }

    @Override
    public Kalender findKalenderById(int id) throws SQLException {
        return getKalenderDao().queryForId(id);
    }

    // Helpermethodes:
    private DBHelper getDBHelper() {
        if (helper == null) {
            helper = OpenHelperManager.getHelper(context, DBHelper.class);
        }
        return helper;
    }

    private Dao<Kalender, Integer> getKalenderDao() throws SQLException {
        if (kalenderDao == null) {
            kalenderDao = getDBHelper().getDao(Kalender.class);
        }
        return kalenderDao;
    }
}

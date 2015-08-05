package be.intec.forzajuno.persistence;

import java.sql.SQLException;
import java.util.List;

import be.intec.forzajuno.model.Kalender;

public interface KalenderDao {
    List<Kalender> getFullKalender() throws SQLException;

    int addKalender(Kalender kalender) throws SQLException;

    int updateKalender(Kalender kalender) throws SQLException;

    Kalender findKalenderById(int id) throws SQLException;

}

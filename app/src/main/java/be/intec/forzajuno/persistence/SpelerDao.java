package be.intec.forzajuno.persistence;

import java.sql.SQLException;
import java.util.List;

import be.intec.forzajuno.model.Speler;

public interface SpelerDao {

    int add(Speler speler) throws SQLException;

    int delete(Speler speler) throws SQLException;

    List<Speler> getAllSpelers() throws SQLException;

    Speler getSpelerBijVolledigeNaam(String volledigeNaam) throws SQLException;


}

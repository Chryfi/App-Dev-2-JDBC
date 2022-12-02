package application;

import datalayer.DataLayer;
import datalayer.Database;
import datalayer.entities.Kunde;

import java.sql.SQLException;
import java.util.List;

public class KundenManager {
    public static List<Kunde> getAllKunden() throws SQLException {
        return DataLayer.getDatabase().getKundenTable().getAll();
    }

    public static boolean insertKunde(Kunde kunde) throws SQLException {
        return DataLayer.getDatabase().getKundenTable().insert(kunde);
    }
}

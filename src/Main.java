import datalayer.DataLayer;
import datalayer.Database;
import datalayer.entities.Konto;
import datalayer.entities.Kunde;
import datalayer.tables.KontoTable;
import datalayer.tables.KundenTable;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        DataLayer.init();

        Database db = DataLayer.getDatabase();

        for (Kunde kunde : db.getKundenTable().getAll())
        {
            System.out.println(kunde);
        }
    }
}
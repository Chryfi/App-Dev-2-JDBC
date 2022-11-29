package datalayer.tables;

import datalayer.Database;
import datalayer.entities.Konto;
import datalayer.entities.Kunde;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KundenTable extends Table<Kunde> {

    public KundenTable(Database db, String name)
    {
        super(db, name);
    }

    @Override
    public boolean insert(Kunde kunde) throws SQLException {
        int kontoNr = this.db.getKontoTable().insertGetNr(kunde.getKonto());

        String values = '\'' + kunde.getName() + "'," + kontoNr;

        return this.db.execute("INSERT INTO " + this.tableName + " (name, konto_nr) VALUES (" + values + ")");
    }

    public List<Kunde> getAll() throws SQLException {
        ResultSet set = this.db.executeQuery("SELECT * FROM " + this.tableName);;

        List<Kunde> all = new ArrayList<>();

        while (set.next()) {
            Konto konto = this.db.getKontoTable().getKonto(set.getInt("konto_nr"));

            all.add(new Kunde(set.getInt("nr"), set.getString("name"), konto));
        }

        return all;
    }

    @Override
    public boolean set(Kunde entity) throws SQLException {
        return false;
    }
}

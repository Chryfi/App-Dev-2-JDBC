package datalayer.tables;

import datalayer.Database;
import datalayer.entities.Konto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class KontoTable extends Table<Konto> {

    public KontoTable(Database db, String name) {
        super(db, name);
    }

    @Override
    public boolean insert(Konto konto) throws SQLException {
        return this.db.execute("INSERT INTO " + this.tableName + " (saldo) VALUES (" + konto.getSaldo() + ")");
    }

    public int insertGetNr(Konto konto) throws SQLException {
        ResultSet set = this.db.executeQuery("INSERT INTO " + this.tableName + " (saldo) " +
                                             "VALUES (" + konto.getSaldo() + ") RETURNING " + this.tableName + ".nr");

        set.next();

        return set.getInt("nr");
    }

    public Konto getKonto(int nr) throws SQLException {
        ResultSet set = this.db.executeQuery("SELECT * FROM " + this.tableName + " WHERE nr=" + nr);

        return set.next() ? new Konto(set.getInt("nr"), set.getInt("saldo")) : null;
    }

    @Override
    public boolean set(Konto entity) throws SQLException {
        return false;
    }
}

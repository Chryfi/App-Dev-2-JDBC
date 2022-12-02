package datalayer.tables;

import datalayer.Database;
import datalayer.entities.Konto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class KontoTable extends EntityTable<Konto> {

    public KontoTable(Database db, String name) {
        super(db, name);
    }

    @Override
    public boolean insert(Konto konto) throws SQLException {
        PreparedStatement stmt = this.db.prepare("INSERT INTO " + this.name
                + " (saldo) VALUES (?) RETURNING " + this.name + ".nr");
        stmt.setDouble(1, konto.getSaldo());
        ResultSet set = stmt.executeQuery();

        if (!set.next()) return false;

        konto.setNr(set.getInt("nr"));

        return true;
    }

    public boolean insertWithKey(Konto konto) throws SQLException {
        PreparedStatement stmt = this.db.prepare("INSERT INTO " + this.name
                + " (nr, saldo) VALUES (?, ?)");
        stmt.setInt(1, konto.getNr());
        stmt.setDouble(2, konto.getSaldo());

        return stmt.executeUpdate() != 0;
    }

    public Konto getKonto(int nr) throws SQLException {
        PreparedStatement stmt = this.db.prepare("SELECT * FROM " + this.name + " WHERE nr=?");
        stmt.setInt(1, nr);
        ResultSet set = stmt.executeQuery();

        return set.next() ? new Konto(set.getInt("nr"), set.getInt("saldo")) : null;
    }
}

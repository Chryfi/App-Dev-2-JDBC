package datalayer.tables;

import datalayer.Database;
import datalayer.entities.Konto;
import datalayer.entities.Kunde;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KundenTable extends EntityTable<Kunde> {

    public KundenTable(Database db, String name) {
        super(db, name);
    }

    @Override
    public boolean insert(Kunde kunde) throws SQLException {
        if (kunde.getKonto() == null) {
            throw new SQLException("Konto needs to be defined!");
        }

        /* if konto doesnt exist in database, try to insert */
        if (this.db.getKontoTable().getKonto(kunde.getKonto().getNr()) == null) {
            if (!this.db.getKontoTable().insertWithKey(kunde.getKonto())) {
                throw new SQLException("Couldn't insert konto!");
            }
        }
        else if (this.getKundeWithKonto(kunde.getKonto().getNr()) != null) {
            throw new SQLException("Kunde with that konto already exists!");
        }

        PreparedStatement stmt = this.db.prepare("INSERT INTO " + this.name
                + " (name, adresse, konto_nr) VALUES (?,?,?) RETURNING " + this.name + ".nr");
        stmt.setString(1, kunde.getName());
        stmt.setString(2, kunde.getAddress());
        stmt.setInt(3, kunde.getKonto().getNr());
        ResultSet set = stmt.executeQuery();

        if (!set.next()) return false;

        kunde.setNr(set.getInt("nr"));

        this.db.getNewsletterAboTable().subscribe(kunde, kunde.getNewsletters());

        return true;
    }

    public Kunde getKundeWithKonto(int kontoNr) throws SQLException {
        PreparedStatement stmt = this.db.prepare("SELECT * FROM " + this.name + " WHERE konto_nr=?");
        stmt.setInt(1, kontoNr);
        ResultSet set = stmt.executeQuery();

        if (!set.next()) return null;

        Kunde kunde = new Kunde(set.getInt("nr"), set.getString("name"),
                set.getString("adresse"), null);

        this.setRelations(kunde, set.getInt("konto_nr"));

        return kunde;
    }

    public List<Kunde> getAll() throws SQLException {
        PreparedStatement stmt = this.db.prepare("SELECT * FROM " + this.name);
        ResultSet set = stmt.executeQuery();

        List<Kunde> all = new ArrayList<>();

        while (set.next()) {
            Kunde kunde = new Kunde(set.getInt("nr"), set.getString("name"),
                    set.getString("adresse"), null);

            this.setRelations(kunde, set.getInt("konto_nr"));
            all.add(kunde);
        }

        return all;
    }

    private void setRelations(Kunde kunde, int kontonr) throws SQLException {
        Konto konto = this.db.getKontoTable().getKonto(kontonr);
        kunde.setKonto(konto);

        kunde.setNewsletters(this.db.getNewsletterAboTable().getSubscriptions(kunde.getNr()));
    }
}

package datalayer.tables;

import datalayer.Database;
import datalayer.entities.Kunde;
import datalayer.entities.Newsletter;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class NewsletterAboTable extends Table {
    public NewsletterAboTable(Database db, String tableName) {
        super(db, tableName);
    }

    public boolean subscribe(Kunde kunde, Set<Newsletter> newsletters)  throws SQLException {
        PreparedStatement stmt = this.db.prepare("INSERT INTO " + this.name + " (kunden_nr, newsletter_titel) VALUES (?,?)");

        for (Newsletter newsletter : newsletters) {
            stmt.setInt(1, kunde.getNr());
            stmt.setString(2, newsletter.getTitle());
            stmt.executeUpdate();
        }

        return true;
    }

    public Set<Newsletter> getSubscriptions(int kundennr) throws SQLException {
        PreparedStatement stmt = this.db.prepare("SELECT * FROM " + this.name + " WHERE kunden_nr=?");
        stmt.setInt(1, kundennr);
        ResultSet set = stmt.executeQuery();

        Set<Newsletter> newsletters = new HashSet<>();

        while (set.next())
        {
            newsletters.add(this.db.getNewsletterTable().getNewsletter(set.getString("newsletter_titel")));
        }

        return newsletters;
    }
}

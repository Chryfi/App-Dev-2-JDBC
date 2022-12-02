package datalayer.tables;

import datalayer.Database;
import datalayer.entities.Newsletter;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NewsletterTable extends EntityTable<Newsletter> {
    public NewsletterTable(Database db, String tableName) {
        super(db, tableName);
    }

    @Override
    public boolean insert(Newsletter entity) throws SQLException {
        PreparedStatement stmt = this.db.prepare("INSERT INTO " + this.name + "(titel, beschreibung) VALUES (?, ?)");

        stmt.setString(1, entity.getTitle());
        stmt.setString(2, entity.getBeschreibung());

        return stmt.executeUpdate() != 0;
    }

    public Newsletter getNewsletter(String title) throws SQLException {
        PreparedStatement stmt = this.db.prepare("SELECT * FROM " + this.name + " WHERE titel=?");

        stmt.setString(1, title);
        ResultSet set = stmt.executeQuery();

        if (!set.next()) return null;

        return new Newsletter(set.getString("titel"), set.getString("beschreibung"));
    }
}

package datalayer;

import datalayer.tables.KontoTable;
import datalayer.tables.KundenTable;
import datalayer.tables.NewsletterAboTable;
import datalayer.tables.NewsletterTable;

import java.sql.*;

public class Database {
    /* connection related */
    private final String databaseName;
    private final String address;
    private final String service;
    private Connection connection;
    private String user;
    private String password;

    /* database tables */
    private final KundenTable kundenTable;
    private final KontoTable kontoTable;
    private final NewsletterTable newsletterTable;
    private final NewsletterAboTable newsletterAboTable;

    public Database(String service, String address) {
        this.databaseName = "test";
        this.service = service;
        this.address = address;

        this.kundenTable = new KundenTable(this, "kunde");
        this.kontoTable = new KontoTable(this, "konto");
        this.newsletterTable = new NewsletterTable(this, "newsletter");
        this.newsletterAboTable = new NewsletterAboTable(this, "kunde_newsletter_abo");
    }

    public KundenTable getKundenTable() {
        return this.kundenTable;
    }

    public KontoTable getKontoTable() {
        return this.kontoTable;
    }

    public NewsletterTable getNewsletterTable() {
        return newsletterTable;
    }

    public NewsletterAboTable getNewsletterAboTable() {
        return newsletterAboTable;
    }

    public String getDatabaseName() {
        return this.databaseName;
    }

    public String getAddress() {
        return this.address;
    }

    public String getService() {
        return this.service;
    }

    public boolean connect(String user, String password) throws SQLException {
        this.user = user;
        this.password = password;
        this.disconnect();

        this.connection = DriverManager.getConnection("jdbc:" + this.service + "://" + this.address + "/" + this.databaseName, user, password);

        return true;
    }

    public boolean isConnected() throws SQLException {
        if (this.connection == null) return false;

        return !this.connection.isClosed();
    }

    public void disconnect() throws SQLException {
        if (this.connection == null) return;

        this.connection.close();
        this.connection = null;
    }

    public PreparedStatement prepare(String query) throws SQLException {
        if (!this.isConnected()) {
            this.connect(this.user, this.password);
        }

        return this.connection.prepareStatement(query);
    }
}

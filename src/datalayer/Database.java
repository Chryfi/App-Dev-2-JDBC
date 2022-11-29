package datalayer;

import datalayer.tables.KontoTable;
import datalayer.tables.KundenTable;

import java.sql.*;

public class Database {
    /* connection related */
    private final String databaseName;
    private final String address;
    private final String service;
    private Connection connection;

    /* database tables */
    private final KundenTable kundenTable;
    private final KontoTable kontoTable;

    public Database(String service, String address) {
        this.databaseName = "test";
        this.service = service;
        this.address = address;

        this.kundenTable = new KundenTable(this, "kunden");
        this.kontoTable = new KontoTable(this, "konto");
    }

    public KundenTable getKundenTable() {
        return this.kundenTable;
    }

    public KontoTable getKontoTable() {
        return this.kontoTable;
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

    public ResultSet executeQuery(String query) throws SQLException {
        if (!this.isConnected()) {
            throw new SQLException("Database is not connected!");
        }

        return this.connection.createStatement().executeQuery(query);
    }

    public boolean execute(String query) throws SQLException {
        if (!this.isConnected()) {
            throw new SQLException("Database is not connected!");
        }

        return this.connection.createStatement().execute(query);
    }
}

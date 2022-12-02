package datalayer.tables;

import datalayer.Database;

public class Table {
    protected Database db;
    protected final String name;

    public Table(Database db, String name) {
        this.name = name;
        this.db = db;
    }

    public String getName() {
        return this.name;
    }

    public Database getDatabase() {
        return this.db;
    }
}

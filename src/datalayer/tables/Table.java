package datalayer.tables;

import datalayer.Database;

import java.sql.SQLException;
import java.util.List;

public abstract class Table<T> {
    protected Database db;
    protected final String tableName;

    protected Table(Database db, String tableName) {
        this.tableName = tableName;
        this.db = db;
    }

    public String getTableName() {
        return this.tableName;
    }

    public Database getDatabase() {
        return this.db;
    }

    public abstract boolean insert(T entity) throws SQLException;

    public abstract boolean set(T entity) throws SQLException;
}

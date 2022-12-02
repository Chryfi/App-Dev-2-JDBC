package datalayer.tables;

import datalayer.Database;

import java.sql.SQLException;

public abstract class EntityTable<T> extends Table {
    public EntityTable(Database db, String name) {
        super(db, name);
    }

    public abstract boolean insert(T entity) throws SQLException;
}

package datalayer;

import java.sql.SQLException;

public class DataLayer {
    private static Database database;

    public static void init() throws SQLException {
        database = new Database("postgresql", "localhost:5432");

        database.connect("test_user", "test");
    }

    public static Database getDatabase()
    {
        return database;
    }
}

import datalayer.DataLayer;
import datalayer.Database;
import datalayer.entities.Konto;
import datalayer.entities.Kunde;
import datalayer.entities.Newsletter;
import presentation.KundeInsert;
import presentation.KundenList;
import presentation.Menu;
import presentation.UIBase;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws SQLException {
        DataLayer.init();
        Database db = DataLayer.getDatabase();
        db.disconnect();

        /*Menu kundenMenu = new Menu("Kunden Menu");
        kundenMenu.addChild(new KundenList("Kunden list"));
        kundenMenu.addChild(new KundeInsert("Kunde insert"));

        Menu root = new Menu("Main Menu");
        root.addChild(kundenMenu);

        UIBase base = new UIBase(root);

        base.start();*/

        //db.getNewsletterTable().insert(new Newsletter("fisch", "test"));
        Set<Newsletter> newsletters = new HashSet<>();
        newsletters.add(new Newsletter("fisch2", "test"));
        db.getKundenTable().insert(new Kunde(123, "fisch", "fisch", new Konto(1123,34), newsletters));

        for (Kunde kunde : db.getKundenTable().getAll()) {
            System.out.println(kunde + "\n");
        }
    }
}

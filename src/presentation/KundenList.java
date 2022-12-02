package presentation;

import application.KundenManager;
import datalayer.DataLayer;
import datalayer.entities.Kunde;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KundenList extends Dialogue {
    private List<Kunde> kunden = new ArrayList<>();

    public KundenList(String title) {
        super(title);
    }

    public KundenList(Dialogue parent, String title) {
        super(parent, title);
    }

    @Override
    public void input(String input) {
        if (input.equalsIgnoreCase("refresh")) {
            try {
                this.kunden = KundenManager.getAllKunden();
            } catch (SQLException e) {
                System.out.println("An exception occurred while selecting the data from the database.");
            }
        } else {
            System.out.println("what?");
        }
    }

    @Override
    public void output() {
        String output = this.getTitle() + "\n";

        try {
            if (this.kunden.isEmpty()) {
                this.kunden = KundenManager.getAllKunden();
            }

            for (Kunde kunde : this.kunden) {
                output += "Nr. " + kunde.getNr() + ", Name " + kunde.getName() + ", Konto nr " + kunde.getKonto().getNr() + "\n";
            }
        } catch (SQLException e) {
            output += "An exception occurred while selecting the data from the database.";
        }

        System.out.println(output);
    }
}

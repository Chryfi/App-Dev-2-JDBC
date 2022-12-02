package presentation;

import application.KundenManager;
import datalayer.entities.Konto;
import datalayer.entities.Kunde;

import java.sql.SQLException;
import java.util.Scanner;

public class KundeInsert extends Dialogue {
    public KundeInsert(String title) {
        super(title);
    }

    public KundeInsert(Dialogue parent, String title) {
        super(parent, title);
    }

    @Override
    public void input(String input) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Type in the address:\n");
        String adress = scanner.nextLine();

        System.out.println("Type in the konto nr:\n");
        int nr;

        try {
            nr = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Konto nr has to be a number and not a string!");

            return;
        }

        System.out.println("Type in the konto saldo:\n");
        double saldo;

        try {
            saldo = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Saldo has to be a number and not a string!");

            return;
        }

        try {
            KundenManager.insertKunde(new Kunde(-1, input, adress, new Konto(nr, saldo)));
        } catch (SQLException e) {
            System.out.println("Couldn't insert Kunde");

            return;
        }

        System.out.println("Inserted Kunde");
    }

    @Override
    public void output() {
        System.out.println(this.getTitle() + "\nType in the name of the new Kunde:\n");
    }
}

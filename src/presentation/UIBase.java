package presentation;

import java.util.Arrays;
import java.util.Scanner;

public class UIBase {
    private Dialogue current;

    public UIBase(Dialogue root) {
        this.current = root;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        this.current.output();

        while (true) {
            String input = scanner.nextLine();

            //System.out.print("\033[H\033[2J"); doesnt work in IDE
            System.out.println("\n" + new String(new char[45]).replace('\0', '―') + "\n");
            System.out.flush();

            if (input.equalsIgnoreCase("quit")) {
                break;
            }

            if (input.equalsIgnoreCase("..")) {
                this.current = this.current.parent != null ? this.current.parent : this.current;
            } else if (this.current instanceof Menu) {
                Dialogue subItem = ((Menu) this.current).getSubItem(input);

                if (subItem == null) {
                    System.out.println("Wrong input!");
                } else {
                    this.current = subItem;
                }
            } else {
                this.current.input(input);
            }

            this.current.output();
        }
    }
}

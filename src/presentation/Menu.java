package presentation;

import java.util.ArrayList;
import java.util.List;

public class Menu extends Dialogue {
    private final List<Dialogue> items = new ArrayList<>();

    public Menu(List<Dialogue> items, String title) {
        super(title);

        for (Dialogue dialogue : items) {
            dialogue.parent = this;
        }

        this.items.addAll(items);
    }

    public Menu(String title) {
        super(title);
    }

    public void addChild(Dialogue child) {
        this.items.add(child);
        child.parent = this;
    }

    @Override
    public void output() {
        String output = this.getTitle() + " items:\n";

        for (int i = 0; i < this.items.size(); i++) {
            output += (i + 1) + ". " + this.items.get(i).title + "\n";
        }

        System.out.println(output);
    }

    public Dialogue getSubItem(String input) {
        try {
            int nr = Integer.parseInt(input);

            return nr <= this.items.size() && nr >= 1 ? this.items.get(nr - 1) : null;
        } catch (NumberFormatException e) {

        }

        for (Dialogue dialogue : this.items) {
            if (dialogue.getTitle().equalsIgnoreCase(input)) {
                return dialogue;
            }
        }

        return null;
    }

    @Override
    public void input(String input) { }
}

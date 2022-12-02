package presentation;

public abstract class Dialogue {
    protected Dialogue parent;
    protected final String title;

    public Dialogue(String title) {
        this.title = title;
    }

    public Dialogue(Dialogue parent, String title) {
        this.title = title;
        this.parent = parent;
    }

    public String getTitle() {
        return this.title;
    }

    public abstract void input(String input);
    public abstract void output();
}
